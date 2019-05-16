package faceRcognation;

import com.amazonaws.services.rekognition.AmazonRekognition;
import com.amazonaws.services.rekognition.model.*;
import faceRcognation.ClientFactory;

import java.io.IOException;
import java.nio.ByteBuffer;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;

public class IndexFaces {

    public void indexFaces(String collName,String p) throws IOException {

        AmazonRekognition rekognition = ClientFactory.createClient();

        String collectionId = collName;
            String imageArg = p;
            Path path = Paths.get(imageArg);
            ByteBuffer byteBuffer;
                byte[] bytes = Files.readAllBytes(path);
                byteBuffer = ByteBuffer.wrap(bytes);


            IndexFacesRequest request = new IndexFacesRequest()
                    .withCollectionId(collectionId)
                    .withDetectionAttributes("ALL")
                    .withImage(new Image().withBytes(byteBuffer))
                    .withExternalImageId(path.getFileName().toString());
            IndexFacesResult result = rekognition.indexFaces(request);

            System.out.println("Indexed image '" + imageArg + "':");

            List<FaceRecord> faceRecords = result.getFaceRecords();
            for (FaceRecord rec : faceRecords) {
                FaceDetail faceDetail = rec.getFaceDetail();
                BoundingBox bb = faceDetail.getBoundingBox();
                System.out.println("Bounding box: left=" + bb.getLeft() +
                        "; top=" + bb.getTop() +
                        "; width=" + bb.getWidth() +
                        "; height=" + bb.getHeight());

                Face face = rec.getFace();
                System.out.println("Face-ID: " + face.getFaceId() +
                        "\nImage ID: " + face.getImageId() +
                        "\nExternal Image ID: " + face.getExternalImageId() +
                        "\nConfidence: " + face.getConfidence());

        }
    }
}