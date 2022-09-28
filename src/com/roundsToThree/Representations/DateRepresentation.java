package com.roundsToThree.Representations;

import com.roundsToThree.Structures.Date;
import com.roundsToThree.Structures.ValueRepresentation;

import java.nio.charset.StandardCharsets;

public class DateRepresentation extends Representation {
    // Class specific variables
    public Date[] value;

    // The Value Representation of this class
    private static final ValueRepresentation valueRepresentation = ValueRepresentation.VALUE_REPRESENTATION_DA;

    // Create DateRepresentation from VR_DA byte content
    public DateRepresentation(byte[] data) {
        // The DICOM spec. allows for padding on the ascii encoded bytes, these should be filtered out
        // Month and day appear to be mandatory unlike VR_DT
        if (data == null || data.length == 0)
            return;

        // Split the dates on the delimiter
        String[] dates = new String(data, StandardCharsets.UTF_8).split("\\\\");

        value = new Date[dates.length];

        for (int i = 0; i < dates.length; i++) {
            dates[i] = dates[i].trim();
            short year = Short.parseShort(dates[i].substring(0, 4));
            byte month = Byte.parseByte(dates[i].substring(4, 6));
            byte day = Byte.parseByte(dates[i].substring(6, 8));
            value[i] = new Date(year, month, day);
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
