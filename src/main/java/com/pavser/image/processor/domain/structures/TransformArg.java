package com.pavser.image.processor.domain.structures;

import lombok.Data;
import lombok.experimental.Accessors;

@Data
@Accessors(chain = true)
public class TransformArg {

    public enum ColorCorrection {
        GRAYSCALE
    }

    /**
     * Desired width
     */
    private Integer width;

    /**
     * Desired height
     */
    private Integer height;

    /**
     * Desired color correction (what need to change)
     */
    private ColorCorrection colorCorrection;

}
