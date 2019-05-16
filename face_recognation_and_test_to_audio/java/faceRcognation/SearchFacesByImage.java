package faceRcognation;

import java.io.IOException;
import java.nio.file.Path;
import com.amazonaws.services.rekognition.AmazonRekognition;
import com.amazonaws.services.rekognition.model.*;
import faceRcognation.ClientFactory;

import java.nio.ByteBuffer;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;

public class SearchFacesByImage {

    public void searchFacesByImage(String collName,String p) throws IOException {


        String collectionId = collName;
        String imageArg = p;

        Path path = Paths.get(imageArg);
        ByteBuffer byteBuffer;
        try {
            byte[] bytes = Files.readAllBytes(path);
            byteBuffer = ByteBuffer.wrap(bytes);
        } catch (IOException e) {
            System.err.println("Failed to read file '" + imageArg + "': " + e.getMessage());
            return;
        }

        SearchFacesByImageRequest request = new SearchFacesByImageRequest()
                .withCollectionId(collectionId)
                .withImage(new Image().withBytes(byteBuffer));

        AmazonRekognition rekognition = ClientFactory.createClient();
        SearchFacesByImageResult result = rekognition.searchFacesByImage(request);

        List<FaceMatch> faceMatches = result.getFaceMatches();
        String voice;
        if(faceMatches.size()==0) {
            voice="Sorry, no matched faces";
            System.out.println("voice is "+voice);
        }else{
            voice="";
        }
        for (FaceMatch match : faceMatches) {
            Float similarity = match.getSimilarity();
            Face face = match.getFace();
            if(face.getFaceId().equals("83742d2c-8ee1-4d47-a361-bf352504bd58")) voice="matched user name is mu lin Shi";
            else voice="mached face ID is "+face.getFaceId()+" External Image ID is "+face.getExternalImageId();
            System.out.println(voice);


//            System.out.println("MATCH:" +
//                    "\nSimilarity: " + similarity +
//                    "\nFace-ID: " + face.getFaceId() +
//                    "\nImage ID: " + face.getImageId() +
//                    "\nExternal Image ID: " + face.getExternalImageId() +
//                    "\nConfidence: " + face.getConfidence());
        }
        try {
            if(voice!=null) textToAudio.PollyDemo.runPolly(voice);
        } catch (Exception e) {
            e.printStackTrace();
        }

    }
}