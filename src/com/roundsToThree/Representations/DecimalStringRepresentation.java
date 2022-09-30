package com.roundsToThree.Representations;

import com.roundsToThree.DataProcessing.StringUtils;
import com.roundsToThree.Structures.DecimalString;
import com.roundsToThree.Structures.ValueRepresentation;

import java.nio.charset.StandardCharsets;

public class DecimalStringRepresentation extends Representation {

    // Class specific variables
    public DecimalString[] value = new DecimalString[0];

    // The Value Representation of this class
    public static final ValueRepresentation valueRepresentation = ValueRepresentation.VALUE_REPRESENTATION_DS;

    // Converts a byte array of VR_DS type to DecimalString
    public DecimalStringRepresentation(byte[] data) {
        // Up to 16 bytes long max
        // ^ The spec kinda lies here as '\' can be used as a delimiter to store multiple
        // values in a single DecimalString
        if (data == null || data.length == 0)
            return;

        // Split on demlimiter
        String[] numbers = new String(data, StandardCharsets.UTF_8).split("\\\\");

        value = new DecimalString[numbers.length];
        for (int i = 0; i < numbers.length; i++)
            // Iterate through every number (limit at 16 chars and trim whitespace)
            value[i] = new DecimalString(Double.parseDouble(StringUtils.limitStringLength(numbers[i].trim(), 16).trim()));

    }

    @Override
    public ValueRepresentation getValueRepresentation() {
        return valueRepresentation;
    }


}
