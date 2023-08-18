import java.awt.image.BufferedImage;
import java.awt.image.RenderedImage;
import java.io.File;
import javax.imageio.*;
import java.io.*;

import java.awt.image.BufferedImage;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;

import javax.imageio.ImageIO;

public class ImageConverter {/**
     * https://www.codejava.net/java-se/graphics/convert-image-formats
     *
     * Converts an image to another format
     *
     * @param inputImagePath Path of the source image
     * @param outputImagePath Path of the destination image
     * @param formatName the format to be converted to, one of: jpeg, png,
     * bmp, wbmp, and gif
     * @return true if successful, false otherwise
     * @throws IOException if errors occur during writing
     */
    public static boolean convert(String inputImagePath, String outputImagePath, String formatName)
            throws IOException {

        FileInputStream inputStream = new FileInputStream(inputImagePath);

        String inputFileName = new File(inputImagePath).getName();

        String fullOutputPath = outputImagePath + File.separator + inputFileName + "_converted." + formatName;

        FileOutputStream outputStream = new FileOutputStream(fullOutputPath);


        // reads input image from file
        BufferedImage inputImage = ImageIO.read(inputStream);

        // writes to the output image in specified format
        boolean result = ImageIO.write(inputImage, formatName, outputStream);

        // needs to close the streams
        outputStream.close();
        inputStream.close();

        return result;
    }
}
