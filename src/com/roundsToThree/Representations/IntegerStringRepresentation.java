package com.roundsToThree.Representations;

import com.roundsToThree.Structures.IntegerString;
import com.roundsToThree.Structures.ValueRepresentation;

import java.nio.charset.StandardCharsets;

public class IntegerStringRepresentation extends Representation {

    // Class specific variables
    public IntegerString[] value;

    // The Value Representation of this class
    public static final ValueRepresentation valueRepresentation = ValueRepresentation.VALUE_REPRESENTATION_IS;

    // Converts a byte array of VR_IS type to IntegerString
    public IntegerStringRepresentation(byte[] data) {
        if (data == null || data.length == 0)
            return;

        // Parse the integer and trim spaces then Split on the \ delimiter
        String[] numbers = (new String(data, StandardCharsets.UTF_8).trim()).split("\\\\");

        // Iterate through the numbers and
        value = new IntegerString[numbers.length];

        // Iterate over the numbers
        for (int i = 0; i < numbers.length; i++)
            value[i] = new IntegerString(Integer.parseInt(numbers[i].trim()));

    }

    @Override
    public ValueRepresentation getValueRepresentation() {
        return valueRepresentation;
    }

}

