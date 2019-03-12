package com.pavser.image.processor.domain.exceptions;

public class CLIException extends Exception {
    public CLIException(Throwable cause) {
        super(cause);
    }

    public CLIException() {
        super();
    }

    public CLIException(String message) {
        super(message);
    }
}
