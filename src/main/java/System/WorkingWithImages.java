package System;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import javax.imageio.ImageIO;
import org.imgscalr.Scalr;

public class WorkingWithImages {

    /*
    can create a temp folder and use 
    File.delete() to delete the files after they are used
     */

    private static BufferedImage originalPoster;  //Original size is 1000x1500 
    private static BufferedImage resizedPoster250; //250 x 375
    private static BufferedImage resizedPoster100; //100 x 150

    public static void urlReader(String link) {
        URL imageUrl = null;

        try {
            imageUrl = new URL(link); //create a URL object from the link string
        } catch (MalformedURLException e) {
            e.printStackTrace();
        }

        try {
            originalPoster = ImageIO.read(imageUrl); //send the URL to BufferedImage object
        } catch (IOException e) {
            e.printStackTrace();
        }

        resizedPoster250 = resizeImage(originalPoster, 250, 375);
        resizedPoster100 = resizeImage(originalPoster, 100, 150);
    }

    public static void imageSaver(String posterPath, String imageName, String type) throws IOException {
        if (!(new File("src/main/resources/posters/" + imageName + "w100.jpg").exists()
                || new File("src/main/resources/posters/" + imageName + "w250.jpg").exists())) { //don't save if the file already exists

            String link = "https://image.tmdb.org/t/p/original" + posterPath;

            urlReader(link);
            //urlReader method call to get the images to be saved from the internet
            //urlReader will call resize inside itself to get the other two sizes we want

            switch (type) {
                case "jpg": {
                    ImageIO.write(resizedPoster100, "jpg", new File("src/main/resources/posters/" + imageName + "w100.jpg"));
                    ImageIO.write(resizedPoster250, "jpg", new File("src/main/resources/posters/" + imageName + "w250.jpg"));
                    break;
                }

                case "png": {
                    ImageIO.write(resizedPoster100, "png", new File("src/main/resources/posters/" + imageName + "w100.png"));
                    ImageIO.write(resizedPoster250, "png", new File("src/main/resources/posters/" + imageName + "w250.png"));
                    break;
                }

                default: {
                    System.out.println("No such type found, saving as jpg...");
                    ImageIO.write(resizedPoster100, "jpg", new File("src/main/resources/posters/" + imageName + "w100.jpg"));
                    ImageIO.write(resizedPoster250, "jpg", new File("src/main/resources/posters/" + imageName + "w250.jpg"));
                    break;
                }
            }

        }

    }

    public static BufferedImage resizeImage(BufferedImage image, int width, int height) {
        BufferedImage scaledImage = Scalr.resize(image, Scalr.Method.QUALITY, width, height);
        return scaledImage;
    }

}
