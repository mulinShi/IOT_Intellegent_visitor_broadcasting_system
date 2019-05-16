package faceRcognation;

import java.io.IOException;
import java.util.Scanner;

public class Commander {

    public static void main(String [] args) throws IOException {
        while (true) {
            Scanner sc = new Scanner(System.in);
            System.out.println("please input command：");
            String com = sc.nextLine();
            switch (com) {
                case "detect-labels":
                    DetectLabels detectLabels = new DetectLabels();
                    detectLabels.detecLable("src/img/work.jpg");
                    break;
                case "detect-faces":
                    DetectFaces detectFaces = new DetectFaces();
                    detectFaces.detectFaces("src/img/work.jpg");
                    break;
                case "create-collection":
                    CreateCollection cc = new CreateCollection();
                    cc.createCollection("testColl");
                    break;
                case "list-collections":
                    ListCollections lc = new ListCollections();
                    lc.ListCollections();
                    break;
                case "delete-collection":
                    DeleteCollection dc = new DeleteCollection();
                    dc.deleteCollection("testColl");
                    break;
                case "describe-collection":
                    DescribeCollection descc = new DescribeCollection();
                    descc.describeCollection("mycoll");
                    break;
                case "index-faces":
                    IndexFaces indf = new IndexFaces();
                    indf.indexFaces("mycoll", "src/img/me1.jpg");
                    break;
                case "search-faces-by-image":
                    SearchFacesByImage sfbi = new SearchFacesByImage();
                    sfbi.searchFacesByImage("mycoll", "src/img/test2.jpg");
                    break;
                case "search-celebrity":
                    RecognizeCelebrities rc = new RecognizeCelebrities();
                    try {
                        rc.recognizeCelebrities("src/img/aobama.jpg");
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                    break;
                default:
                    System.err.println("Unknown argument: " + sc);
                    return;
            }
        }
    }
    }

