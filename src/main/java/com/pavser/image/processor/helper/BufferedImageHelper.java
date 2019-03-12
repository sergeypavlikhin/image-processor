package com.pavser.image.processor.helper;

import com.pavser.image.processor.domain.exceptions.ImageProcessorException;
import org.springframework.stereotype.Component;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.net.URL;

/**
 * Provide methods to work with BufferedImage
 */
@Component
public class BufferedImageHelper {

    /**
     * Open image by URL
     * @param inputImagePath URL
     * @return image wrapped by BufferedImage
     * @throws ImageProcessorException if couldn't open image
     */
    public BufferedImage open(String inputImagePath) throws ImageProcessorException {
        try {
            URL uri = new URL(inputImagePath);
            return ImageIO.read(uri);
        } catch (Exception e) {
            throw new ImageProcessorException(e);
        }
    }

    /**
     * Store {@param bufferedImage} with name as {@param fileName}
     * @param fileName name
     * @param bufferedImage image
     * @return final file name
     * @throws ImageProcessorException if couldn't save image
     */
    public String save(String fileName, BufferedImage bufferedImage) throws ImageProcessorException {
        try {
            //extract formatName
            String formatName = fileName.substring(fileName.lastIndexOf(".") + 1);
            // prepare final file
            File outputFile = new File(fileName);
            if (outputFile.exists()) {
                outputFile.delete();
            }
            outputFile.createNewFile();
            // write
            ImageIO.write(bufferedImage, formatName, outputFile);
            return fileName;
        } catch (Exception e) {
            throw new ImageProcessorException(e);
        }
    }
}
