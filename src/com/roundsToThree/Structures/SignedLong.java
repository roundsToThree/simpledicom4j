package com.roundsToThree.Structures;

public class SignedLong {
    long value;

    public SignedLong(long value) {
        this.value = value;
    }

    @Override
    public String toString() {
        return Long.toString(value);
    }

}
