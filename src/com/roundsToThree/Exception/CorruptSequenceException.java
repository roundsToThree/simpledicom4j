package com.roundsToThree.Exception;

public class CorruptSequenceException extends RuntimeException {
    public CorruptSequenceException(String errorMessage, Throwable error) {
        super(errorMessage, error);
    }

}
