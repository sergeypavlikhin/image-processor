package com.pavser.image.processor.domain;

import com.pavser.image.processor.domain.exceptions.ImageProcessorException;
import com.pavser.image.processor.domain.structures.TransformArg;
import com.pavser.image.processor.helper.BufferedImageHelper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.awt.image.BufferedImage;
import java.nio.file.Paths;

/**
 * Processes images
 */
@Component
public class ImageProcessor {

    protected static final String RESULT_PREFIX = "result_";

    @Autowired
    private BufferedImageHelper bufferedImageHelper;

    @Autowired
    private DefaultImageTransformer imageTransformer;

    /**
     * Resize image from {@param sourceImagePath} and make source black and white
     * @param width result width
     * @param height result height
     * @param sourceImagePath path to source image
     * @throws ImageProcessorException if something wrong
     */
    public void processImage(Integer width, Integer height, String sourceImagePath) throws ImageProcessorException {
        //prepare args
        BufferedImage inputFile = bufferedImageHelper.open(sourceImagePath);
        TransformArg arg = new TransformArg()
                .setWidth(width)
                .setHeight(height)
                .setColorCorrection(TransformArg.ColorCorrection.GRAYSCALE);

        //make transform
        BufferedImage outputFile = imageTransformer.transform(inputFile, arg);

        //save result
        bufferedImageHelper.save(getResultFileName(sourceImagePath), outputFile);
    }

    private String getResultFileName(String inputImagePath) {
        return RESULT_PREFIX + Paths.get(inputImagePath).getFileName().toString();
    }


}
