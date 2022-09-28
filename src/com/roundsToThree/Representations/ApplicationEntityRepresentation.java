package com.roundsToThree.Representations;

import com.roundsToThree.DataProcessing.StringUtils;
import com.roundsToThree.Structures.ApplicationEntity;
import com.roundsToThree.Structures.ValueRepresentation;

import java.nio.charset.StandardCharsets;

public class ApplicationEntityRepresentation extends Representation {

    // Class specific variables
    public ApplicationEntity[] value;

    // The Value Representation of this class
    private static final ValueRepresentation valueRepresentation = ValueRepresentation.VALUE_REPRESENTATION_AE;

    // Convert an array of bytes into a VR_AE representation
    public ApplicationEntityRepresentation(byte[] data) {
        if (data == null || data.length == 0)
            return;

        // Convert the data to a string and split it on the list delimiter
        String[] rawValues = new String(data, StandardCharsets.UTF_8).split("\\\\");
        // Trim each raw value and exclude those that become "" (Just spaces)
        int numberOfValues = 0;
        for (int i = 0; i < rawValues.length; i++) {
            // Remove whitespaces, cap length at 16 bytes, then remove any remaining whitespace
            rawValues[i] = StringUtils.limitStringLength(rawValues[i].trim(), 16).trim();
            // If the value actually contains data, account for it
            if (!rawValues[i].equals(""))
                numberOfValues++;
        }

        // Copy the values to the class' array
        value = new ApplicationEntity[numberOfValues];
        int writeIndex = 0;
        for (String rawValue : rawValues)
            if (!rawValue.equals("")) {
                value[writeIndex] = new ApplicationEntity(rawValue);
                writeIndex++;
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
        if (value.length == 1 && value[0] != null)
            return value[0].toString();

        // Multiple values
        StringBuilder returnStr = new StringBuilder("[");
        for (int i = 0; i < value.length; i++) {
            if (value[i] == null)
                continue;

            // Prepend a comma if it's not the first item in the array
            if (i != 0)
                returnStr.append(", ");

            returnStr.append(value[i].toString());
        }
        returnStr.append("]");
        return returnStr.toString();
    }
}
