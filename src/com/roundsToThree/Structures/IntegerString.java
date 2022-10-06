package com.roundsToThree.Structures;

public class IntegerString {
    public int value;

    public IntegerString(int value) {
        this.value = value;
    }

    @Override
    public String toString() {
        return Integer.toString(value);
    }

}
