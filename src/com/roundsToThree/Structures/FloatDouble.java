package com.roundsToThree.Structures;

public class FloatDouble {
    public double value;

    public FloatDouble(double value) {
        this.value = value;
    }

    @Override
    public String toString() {
        return Double.toString(value);
    }
}
