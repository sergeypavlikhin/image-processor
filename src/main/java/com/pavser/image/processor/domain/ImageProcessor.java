package com.pavser.image.processor.domain;

import com.pavser.image.processor.domain.structures.TransformArg;
import com.pavser.image.processor.helper.BufferedImageHelper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.awt.image.BufferedImage;
import java.nio.file.Paths;

@Component
public class ImageProcessor {

    @Autowired
    private BufferedImageHelper bufferedImageHelper;

    @Autowired
    private ImageTransformer imageTransformer;

    /**
     *
     * @param width
     * @param height
     * @param inputImagePath
     * @throws ImageProcessorException
     */
    public void processImage(Integer width, Integer height, String inputImagePath) throws ImageProcessorException {
        //prepare args
        BufferedImage inputFile = bufferedImageHelper.open(inputImagePath);
        TransformArg arg = new TransformArg()
                .setWidth(width)
                .setHeight(height)
                .setColorCorrection(TransformArg.ColorCorrection.GRAYSCALE);

        //make transform
        BufferedImage outputFile = imageTransformer.transform(inputFile, arg);

        //save result
        bufferedImageHelper.save(getResultFileName(inputImagePath), outputFile);
    }

    private String getResultFileName(String inputImagePath) {
        return "result_" + Paths.get(inputImagePath).getFileName().toString();
    }


}
