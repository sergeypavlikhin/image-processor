package com.pavser.image.processor.domain;

public class ImageProcessorException extends Exception {

    public ImageProcessorException(String message) {
        super(message);
    }

    public ImageProcessorException(Throwable cause) {
        super(cause);
    }
}
