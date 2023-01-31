package com.roundsToThree.Exception;

public class InvalidRepresentation extends RuntimeException {
    public InvalidRepresentation(String errorMessage, Throwable error) {
        super(errorMessage, error);
    }
}
