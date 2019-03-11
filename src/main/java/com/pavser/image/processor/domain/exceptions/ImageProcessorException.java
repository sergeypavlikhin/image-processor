package com.pavser.image.processor.domain.exceptions;

/**
 * Main application exception
 */
public class ImageProcessorException extends Exception {

    public ImageProcessorException() {
        super();
    }

    public ImageProcessorException(String message) {
        super(message);
    }

    public ImageProcessorException(Throwable cause) {
        super(cause);
    }
}
