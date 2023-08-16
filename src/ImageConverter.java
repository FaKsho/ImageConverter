import java.awt.image.BufferedImage;
import java.io.File;
import javax.imageio.*;
import java.io.*;

public class ImageConverter {

    public static void convert(File inFile, String outPath){

        //png to jpg
        File inputFile = inFile;
        File outputFile = new File(outPath);

        try (InputStream is = new FileInputStream(inputFile)) {

            BufferedImage image = ImageIO.read(is);
            try (OutputStream os = new FileOutputStream(outputFile)) {
                ImageIO.write(image, "jpg", os);
            } catch (Exception exp) {
                exp.printStackTrace();
            }
        } catch (Exception exp) {
            exp.printStackTrace();
            System.out.println("Error de conversión");
        }
    }
}
