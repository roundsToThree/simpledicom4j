package com.roundsToThree.Representations;

import com.roundsToThree.DataProcessing.StringUtils;
import com.roundsToThree.Structures.ValueRepresentation;

import java.nio.charset.StandardCharsets;

public class DecimalStringRepresentation extends Representation {

    // Class specific variables
    public double[] values = new double[0];

    // The Value Representation of this class
    private static final ValueRepresentation valueRepresentation = ValueRepresentation.VALUE_REPRESENTATION_DS;

    // Converts a byte array of VR_DS type to DecimalString
    public DecimalStringRepresentation(byte[] data) {
        // Up to 16 bytes long max
        // ^ The spec kinda lies here as '\' can be used as a delimiter to store multiple
        // values in a single DecimalString
        if (data == null || data.length == 0)
            return;

        // Iterate through every number
        String text = StringUtils.limitStringLength(new String(data, StandardCharsets.UTF_8).trim(), 16);
        String[] numbers = text.split("\\\\");
        values = new double[numbers.length];
        for (int i = 0; i < numbers.length; i++)
            values[i] = Double.parseDouble(numbers[i]);
    }

    @Override
    public ValueRepresentation getValueRepresentation() {
        return valueRepresentation;
    }

    @Override
    public String toString() {
        if (values == null || values.length == 0)
            return "N/A";
        if (values.length == 1)
            return Double.toString(values[0]);

        // Otherwise multiple values
        StringBuilder returnString = new StringBuilder("[");
        for (int i = 0; i < values.length; i++) {
            returnString.append(Double.toString(values[i]));
            if (i != values.length - 1)
                returnString.append(", ");
        }
        return returnString + "]";

    }


}
