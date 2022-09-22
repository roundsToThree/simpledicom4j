package com.roundsToThree.Representations;

import com.roundsToThree.Structures.ValueRepresentation;

import java.nio.charset.StandardCharsets;

public class DateRepresentation extends Representation {
    // Class specific variables

    public short year = 1970;
    public byte month = 1;
    public byte day = 1;

    // The Value Representation of this class
    private static final ValueRepresentation valueRepresentation = ValueRepresentation.VALUE_REPRESENTATION_DA;

    // Create DateRepresentation from VR_DA byte content
    public DateRepresentation(byte[] value) {
        // The DICOM spec. allows for padding on the ascii encoded bytes, these should be filtered out
        // Month and day appear to be mandatory unlike VR_DT
        if (value == null || value.length < 8)
            return;

        String time = new String(value, StandardCharsets.UTF_8).trim();

        if (time.length() < 8)
            return;

        year = Short.parseShort(time.substring(0, 4));
        month = Byte.parseByte(time.substring(4, 6));
        day = Byte.parseByte(time.substring(6, 8));
    }

    @Override
    public ValueRepresentation getValueRepresentation() {
        return valueRepresentation;
    }

    @Override
    public String toString() {
        return String.format("%02d/%02d/%04d", day, month, year);
    }
}
