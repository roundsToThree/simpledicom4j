package com.roundsToThree.Representations;

import com.roundsToThree.Structures.ValueRepresentation;

import java.nio.charset.StandardCharsets;

public class IntegerStringRepresentation extends Representation {

    // Class specific variables
    public int value = 0;

    // The Value Representation of this class
    public static final ValueRepresentation valueRepresentation = ValueRepresentation.VALUE_REPRESENTATION_IS;

    // Converts a byte array of VR_IS type to IntegerString
    public IntegerStringRepresentation(byte[] data) {
        if (data == null || data.length == 0)
            return;

        // Parse the integer and trim spaces
        value = Integer.parseInt(new String(data, StandardCharsets.UTF_8).trim());
    }

    @Override
    public ValueRepresentation getValueRepresentation() {
        return valueRepresentation;
    }

    @Override
    public String toString() {
        return Integer.toString(value);
    }

}

