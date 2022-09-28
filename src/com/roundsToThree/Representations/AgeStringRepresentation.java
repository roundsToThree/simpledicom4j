package com.roundsToThree.Representations;

import com.roundsToThree.DataProcessing.ByteUtils;
import com.roundsToThree.Structures.ValueRepresentation;

import java.nio.charset.StandardCharsets;
import java.util.Arrays;

public class AgeStringRepresentation extends Representation {

    // Class specific variables
    // The age in days
    public int[] age = {};

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
        String[] ages = new String(data, StandardCharsets.UTF_8).split("////");
        age = new int[ages.length];

        // Iterate through each of the ages
        for (int i = 0; i < ages.length; i++) {
            // Calculate the age (without the scale)
            age[i] = Integer.parseInt(ages[i].substring(0, 3));
            // Get the scale
            switch (ages[i].charAt(3)) {
                case 'D' -> {
                    // Ignore this case because age is already in days
                }
                case 'W' -> {
                    // Mulitply age by 7
                    age[i] *= 7;
                }
                case 'M' -> {
                    // Multiply age by 30
                    age[i] *= 30;
                }
                case 'Y' -> {
                    // Multiply age by 365
                    age[i] *= 365;
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

        if (age == null || age.length == 0)
            return "N/A";

        String returnString = "";

        // Account for multiple ages
        for (int i = 0; i < age.length; i++) {
            // Account for different precisions
            if (age[i] < 14) {
                // If < 2wks, report age in days
                returnString += age[i] + " day" + (age[i] > 0 ? "s" : "");
            } else if (age[i] < 60) {
                // If < 2mo, report age in weeks
                returnString += age[i] / 7 + " week" + (age[i] / 7 > 0 ? "s" : "");
            } else if (age[i] < 730) {
                // if < 2yr, report age in months
                returnString += age[i] / 30 + " month" + (age[i] / 30 > 0 ? "s" : "");
            } else {
                // Otherwise report age in years
                returnString += age[i] / 365 + " year" + (age[i] / 365 > 0 ? "s" : "");
            }

            // If its not the last term, add a comma
            if (i != age.length - 1)
                returnString += ", ";
        }

        // Format the returned result to look like an array if there is more than 1 elements
        if (age.length > 1)
            returnString = "[" + returnString + "]";

        return returnString;

    }

}
