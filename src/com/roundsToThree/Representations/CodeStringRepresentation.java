package com.roundsToThree.Representations;

import com.roundsToThree.DataProcessing.StringUtils;
import com.roundsToThree.Structures.ValueRepresentation;

import java.nio.charset.StandardCharsets;

public class CodeStringRepresentation extends Representation {

    // Class specific variables
    public String value;


    // The Value Representation of this class
    private static final ValueRepresentation valueRepresentation = ValueRepresentation.VALUE_REPRESENTATION_CS;

    // Convert byte array of format VR_CS into a CodeStringRepresentation
    public CodeStringRepresentation(byte[] data) {
        if (data == null || data.length == 0)
            return;

        // Convert the data to a string (and trim trailing/leading spaces
        value = new String(data, StandardCharsets.UTF_8).trim();

        // Cap the string's length at 20 characters
        value = StringUtils.limitStringLength(value, 20);
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
