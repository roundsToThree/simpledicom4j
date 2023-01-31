package com.roundsToThree.Structures;

public class OtherWord {
    short value;

    public OtherWord(short value) {
        this.value = value;
    }

    @Override
    public String toString() {
        return Short.toString(value);
    }
}
