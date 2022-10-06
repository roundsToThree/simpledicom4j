package com.roundsToThree.Structures;

public class FloatSingle {
    public float value;

    public FloatSingle(float value) {
        this.value = value;
    }

    @Override
    public String toString() {
        return Float.toString(value);
    }
}
