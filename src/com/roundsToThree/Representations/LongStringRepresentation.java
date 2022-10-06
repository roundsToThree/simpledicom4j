package com.roundsToThree.Representations;

import com.roundsToThree.Structures.LongString;
import com.roundsToThree.Structures.ValueRepresentation;

import java.nio.charset.StandardCharsets;

public class LongStringRepresentation extends Representation {

    // Class specific variables
    public LongString[] value;

    // The Value Representation of this class
    public static final ValueRepresentation valueRepresentation = ValueRepresentation.VALUE_REPRESENTATION_LO;

    // Converts a byte array of VR_LO type to LongString
    public LongStringRepresentation(byte[] data) {
        if (data == null || data.length == 0)
            return;

        // Todo: use (0008,0005) instead of StandardCharsets UTF8
        // Split on // then trim whitespace
        String[] values = (new String(data, StandardCharsets.UTF_8)).split("\\\\");

        value = new LongString[values.length];
        // Copy the values across
        for (int i = 0; i < values.length; i++)
            value[i] = new LongString(values[i].trim());
    }

    @Override
    public ValueRepresentation getValueRepresentation() {
        return valueRepresentation;
    }
}

