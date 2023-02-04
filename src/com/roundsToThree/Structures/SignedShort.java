package com.roundsToThree.Structures;

public class SignedShort {
    short value;

    public SignedShort(short value) {
        this.value = value;
    }

    @Override
    public String toString() {
        return Short.toString(value);
    }
}
