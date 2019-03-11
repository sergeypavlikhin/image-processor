package com.pavser.image.processor.helper;

import com.pavser.image.processor.domain.exceptions.ImageProcessorException;
import org.springframework.stereotype.Component;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.net.URI;

@Component
public class BufferedImageHelper {

    public BufferedImage open(String inputImagePath) throws ImageProcessorException {
        try {
            URI uri = new URI(inputImagePath);
            File inputFile = new File(uri);
            return ImageIO.read(inputFile);
        } catch (Exception e) {
            throw new ImageProcessorException(e);
        }
    }


    public void save(String fileName, BufferedImage bufferedImage) throws ImageProcessorException {
        try {
            // prepare final file
            File outputFile = new File(fileName);
            if (outputFile.exists()) {
                outputFile.delete();
            }
            outputFile.createNewFile();

            // extract format name
            String formatName = fileName.substring(fileName.lastIndexOf(".") + 1);

            // write
            ImageIO.write(bufferedImage, formatName, outputFile);
        } catch (Exception e) {
            throw new ImageProcessorException(e);
        }

    }

}
