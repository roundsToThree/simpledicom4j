package com.roundsToThree.Representations;

import com.roundsToThree.DataProcessing.StringUtils;
import com.roundsToThree.Structures.ValueRepresentation;

import java.nio.charset.StandardCharsets;

public class ApplicationEntityRepresentation extends Representation {

    // Class specific variables
    public String value;

    // The Value Representation of this class
    private static final ValueRepresentation valueRepresentation = ValueRepresentation.VALUE_REPRESENTATION_TM;

    // Convert an array of bytes into a VR_AE representation
    public ApplicationEntityRepresentation(byte[] data) {
        // Convert the data to a string (and trim trailing/leading spaces
        value = new String(data, StandardCharsets.UTF_8).trim();

        // Cap the string's length at 16 characters
        value = StringUtils.limitStringLength(value, 16);
    }

    @Override
    public ValueRepresentation getValueRepresentation() {
        return valueRepresentation;
    }


    @Override
    public String toString() {
        return value;
    }
}
