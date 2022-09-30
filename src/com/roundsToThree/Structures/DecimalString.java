package com.roundsToThree.Structures;

public class DecimalString {
    public double value;

    public DecimalString(double value) {
        this.value = value;
    }

    @Override
    public String toString() {
        return Double.toString(value);
    }
}
