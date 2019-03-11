package com.pavser.image.processor.domain;

import com.pavser.image.processor.domain.structures.TransformArg;
import org.springframework.stereotype.Component;

import java.awt.*;
import java.awt.image.BufferedImage;

/**
 * Default image transformer.
 * Just might resize image and make the image black and white
 */
@Component
public class DefaultImageTransformer implements ImageTransformer {

    @Override
    public BufferedImage transform(BufferedImage input, TransformArg arg) {
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
