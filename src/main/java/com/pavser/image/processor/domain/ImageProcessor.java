package com.pavser.image.processor.domain;

import com.pavser.image.processor.domain.exceptions.ImageProcessorException;
import com.pavser.image.processor.domain.structures.TransformArg;
import com.pavser.image.processor.helper.BufferedImageHelper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.awt.image.BufferedImage;
import java.util.Arrays;
import java.util.List;
import java.util.Random;

/**
 * Processes images
 */
@Component
public class ImageProcessor {

    private static final List<String> SUPPORTS_FORMATS = Arrays.asList("jpg", "jpeg", "png");
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
     * @return name of result file
     * @throws ImageProcessorException if something wrong
     */
    public String processImage(Integer width, Integer height, String sourceImagePath) throws ImageProcessorException {
        //prepare args
        BufferedImage inputFile = bufferedImageHelper.open(sourceImagePath);
        TransformArg arg = new TransformArg()
                .setWidth(width)
                .setHeight(height)
                .setColorCorrection(TransformArg.ColorCorrection.GRAYSCALE);

        //make transform
        BufferedImage outputFile = imageTransformer.transform(inputFile, arg);

        //save result
        String resultFileName = getResultFileName(sourceImagePath);
        return bufferedImageHelper.save(resultFileName, outputFile);
    }

    protected String getResultFileName(String original) {
        String onlyFileName = RESULT_PREFIX + System.currentTimeMillis();

        // extract format name
        String formatName = null;
        int dotIndex = original.lastIndexOf(".");
        if (dotIndex > 0) {
            formatName = original.substring(dotIndex + 1);
        }
        if (formatName == null || isNotValidFormatName(formatName)) {
            formatName = "jpg";
        }

        return onlyFileName + "." + formatName;
    }

    private boolean isNotValidFormatName(String formatName) {
        return !SUPPORTS_FORMATS.contains(formatName.toLowerCase());
    }

}
