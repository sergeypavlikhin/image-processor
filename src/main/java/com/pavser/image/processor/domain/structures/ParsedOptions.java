package com.pavser.image.processor.domain.structures;

import lombok.Data;

@Data
public class ParsedOptions {

    private final Integer width;
    private final Integer height;
    private final String inputFilePath;

}
