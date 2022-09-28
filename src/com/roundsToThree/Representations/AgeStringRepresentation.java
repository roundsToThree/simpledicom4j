package com.roundsToThree.Representations;

import com.roundsToThree.DataProcessing.ByteUtils;
import com.roundsToThree.Structures.AgeString;
import com.roundsToThree.Structures.ValueRepresentation;

import java.nio.charset.StandardCharsets;
import java.util.Arrays;

public class AgeStringRepresentation extends Representation {

    // Class specific variables
    // The age in days
    public AgeString[] value = {};

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

        // Split on '/' for multiple ages
        String[] ages = new String(data, StandardCharsets.UTF_8).split("\\\\");
        value = new AgeString[ages.length];

        // Iterate through each of the ages
        for (int i = 0; i < ages.length; i++) {
            // Calculate the age (without the scale)
            value[i] = new AgeString(Integer.parseInt(ages[i].substring(0, 3)));
            // Get the scale
            switch (ages[i].charAt(3)) {
                case 'D' -> {
                    // Ignore this case because age is already in days
                }
                case 'W' -> {
                    // Mulitply age by 7
                    value[i].age *= 7;
                }
                case 'M' -> {
                    // Multiply age by 30
                    value[i].age *= 30;
                }
                case 'Y' -> {
                    // Multiply age by 365
                    value[i].age *= 365;
                }
            }
        }
    }

    @Override
    public ValueRepresentation getValueRepresentation() {
        return valueRepresentation;
    }


    @Override
    public String toString() {
        // No value
        if (value == null || value.length == 0)
            return "N/A";

        // One value
        if (value.length == 1 && value[0] != null)
            return value[0].toString();

        // Multiple values
        StringBuilder returnStr = new StringBuilder("[");
        for (int i = 0; i < value.length; i++) {
            if (value[i] == null)
                continue;

            // Prepend a comma if it's not the first item in the array
            if (i != 0)
                returnStr.append(", ");

            returnStr.append(value[i].toString());
        }
        returnStr.append("]");
        return returnStr.toString();

    }

}
