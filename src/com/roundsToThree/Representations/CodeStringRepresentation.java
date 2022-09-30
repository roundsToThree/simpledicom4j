package com.roundsToThree.Representations;

import com.roundsToThree.DataProcessing.StringUtils;
import com.roundsToThree.Structures.CodeString;
import com.roundsToThree.Structures.ValueRepresentation;

import java.nio.charset.StandardCharsets;

public class CodeStringRepresentation extends Representation {

    // Class specific variables
    public CodeString[] value;


    // The Value Representation of this class
    public static final ValueRepresentation valueRepresentation = ValueRepresentation.VALUE_REPRESENTATION_CS;

    // Convert byte array of format VR_CS into a CodeStringRepresentation
    public CodeStringRepresentation(byte[] data) {
        if (data == null || data.length == 0)
            return;

        // Convert the data to a string and split on \
        String[] values = new String(data, StandardCharsets.UTF_8).split("\\\\");

        value = new CodeString[values.length];
        // Push each value onto the array of values
        for (int i = 0; i < value.length; i++) {
            // Cap the string's length at 20 characters and trim whitespace twice
            value[i] = new CodeString(StringUtils.limitStringLength(values[i].trim(), 20).trim());
        }
    }

    @Override
    public ValueRepresentation getValueRepresentation() {
        return valueRepresentation;
    }

}
