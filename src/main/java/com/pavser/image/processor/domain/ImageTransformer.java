package com.pavser.image.processor.domain;

import com.pavser.image.processor.domain.structures.TransformArg;
import lombok.NonNull;

import java.awt.image.BufferedImage;

/**
 * Transforms image
 */
public interface ImageTransformer {

    /**
     * Transforms input {@param image } according to {@param arg}
     *
     * @param input image which need to transform
     * @param arg argument to transformation
     * @return transformed image (wrapped by BufferedImage)
     */
    BufferedImage transform(@NonNull BufferedImage input, @NonNull TransformArg arg);
}
