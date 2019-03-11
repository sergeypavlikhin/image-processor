package com.pavser.image.processor.domain;

import com.pavser.image.processor.domain.structures.TransformArg;
import lombok.NonNull;
import org.springframework.stereotype.Component;

import java.awt.*;
import java.awt.image.BufferedImage;

@Component
public class ImageTransformer {

    /**
     *
     * @param input
     * @param arg
     * @return
     */
    public BufferedImage transform(@NonNull BufferedImage input, @NonNull TransformArg arg) {
        // create result
        BufferedImage outputImage = getNewBufferedImage(arg);

        // fill result
        Graphics2D g2d = outputImage.createGraphics();
        g2d.drawImage(input, 0, 0, arg.getWidth(), arg.getHeight(), null);
        g2d.dispose();

        return outputImage;
    }

    private BufferedImage getNewBufferedImage(TransformArg transformArg) {
        switch (transformArg.getColorCorrection()) {
            case GRAYSCALE: return new BufferedImage(
                    transformArg.getWidth(), transformArg.getHeight(), BufferedImage.TYPE_BYTE_GRAY);
        }
        throw new RuntimeException("Unknown color correction");
    }

}
