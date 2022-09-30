package com.roundsToThree.Representations;

import com.roundsToThree.Structures.ValueRepresentation;

import java.nio.charset.StandardCharsets;

public class LongStringRepresentation extends Representation {

    // Class specific variables
    public long value = 0;

    // The Value Representation of this class
    public static final ValueRepresentation valueRepresentation = ValueRepresentation.VALUE_REPRESENTATION_IS;

    // Converts a byte array of VR_LO type to LongString
    public LongStringRepresentation(byte[] data) {
        if (data == null || data.length == 0)
            return;

        // Parse the integer and trim spaces
        // Todo: use (0008,0005) instead of StandardCharsets UTF8
        value = Long.parseLong(new String(data, StandardCharsets.UTF_8).trim());
    }

    @Override
    public ValueRepresentation getValueRepresentation() {
        return valueRepresentation;
    }

    @Override
    public String toString() {
        return Long.toString(value);
    }

}

