package com.roundsToThree.Representations;

import com.roundsToThree.DataProcessing.ByteUtils;
import com.roundsToThree.Structures.ValueRepresentation;

import java.util.Arrays;

public class AgeStringRepresentation extends Representation {

    // Class specific variables
    public short age = 0;
    public byte ageScale = NOT_SPECIFIED;

    // The Value Representation of this class
    private static final ValueRepresentation valueRepresentation = ValueRepresentation.VALUE_REPRESENTATION_AS;

    public static final byte NOT_SPECIFIED = -1;
    public static final byte DAYS = 'D';
    public static final byte WEEKS = 'W';
    public static final byte MONTHS = 'M';
    public static final byte YEARS = 'Y';


    // Convert an array of bytes into a VR_AS representation
    public AgeStringRepresentation(byte[] data) {
        // Specification stores them as
        // nnnX where X is D (days) W (weeks) M (months) or Y (years)

        if (data == null || data.length != 4)
            return;

        // Calculate the age
        age = (short) ByteUtils.convertCharactersToLong(Arrays.copyOf(data, 3));

        // Get the scale
        switch (data[3]) {
            case 'D' -> {
                ageScale = DAYS;
            }
            case 'W' -> {
                ageScale = WEEKS;
            }
            case 'M' -> {
                ageScale = MONTHS;
            }
            case 'Y' -> {
                ageScale = YEARS;
            }
        }
    }

    @Override
    public ValueRepresentation getValueRepresentation() {
        return valueRepresentation;
    }


    @Override
    public String toString() {
        // Create an english suffix
        String suffix = "N/A";
        switch (ageScale) {
            case DAYS -> {
                suffix = "Day";
            }
            case WEEKS -> {
                suffix = "Week";
            }
            case MONTHS -> {
                suffix = "Month";
            }
            case YEARS -> {
                suffix = "Year";
            }
        }

        // This breaks down for N/A but lets ignore that
        if (age > 1)
            suffix += "s";

        return String.format("%d %s", age, suffix);
    }

}
