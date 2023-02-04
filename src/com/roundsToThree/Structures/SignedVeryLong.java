package com.roundsToThree.Structures;

public class SignedVeryLong {
    long value;

    public SignedVeryLong(long value) {
        this.value = value;
    }

    @Override
    public String toString() {
        return Long.toString(value);
    }
}

