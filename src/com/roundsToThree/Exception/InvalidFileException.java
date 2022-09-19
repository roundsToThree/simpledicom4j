package com.roundsToThree.Exception;

public class InvalidFileException extends RuntimeException {

    public InvalidFileException(String errorMessage, Throwable error) {
        super(errorMessage, error);
    }
}
