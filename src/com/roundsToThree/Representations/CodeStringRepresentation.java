package com.roundsToThree.Representations;

import com.roundsToThree.DataProcessing.StringUtils;
import com.roundsToThree.Structures.CodeString;
import com.roundsToThree.Structures.ValueRepresentation;

import java.nio.charset.StandardCharsets;

public class CodeStringRepresentation extends Representation {

    // Class specific variables
    public CodeString[] value;


    // The Value Representation of this class
    private static final ValueRepresentation valueRepresentation = ValueRepresentation.VALUE_REPRESENTATION_CS;

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

    @Override
    public String toString() {
        // No value
        if (value == null || value.length == 0)
            return "N/A";
        // One value
        if (value.length == 1)
            return value[0].toString();

        // Multiple values
        StringBuilder returnStr = new StringBuilder("[");
        for (int i = 0; i < value.length; i++) {
            // Prepend a comma if it's not the first item in the array
            if (i != 0)
                returnStr.append(", ");

            returnStr.append(value[i].toString());
        }
        returnStr.append("]");
        return returnStr.toString();
    }
}
