package com.pavser.image.processor.domain.structures;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

@Data
@Accessors(chain = true)
@AllArgsConstructor
@NoArgsConstructor
public class ParsedOptions {
    private Integer width;
    private Integer height;
    private  String inputFilePath;
}

