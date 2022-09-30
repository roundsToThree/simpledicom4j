package com.roundsToThree.Representations;

import com.roundsToThree.Structures.ValueRepresentation;

import java.nio.charset.StandardCharsets;

public class TimeRepresentation extends Representation {

    // Class specific variables
    public byte hour = 0;
    public byte minute = 0;
    public byte second = 0;
    public long microseconds = 0;

    // The Value Representation of this class
    public static final ValueRepresentation valueRepresentation = ValueRepresentation.VALUE_REPRESENTATION_TM;


    // Format : HHMMSS.FFFFFF
    // Note: each value apart from hour may be excluded. If microseconds are excluded, there is no '.'
    // Convert byte array (of the format in VR_TM) into a TimeRepresentation class
    public TimeRepresentation(byte[] data) {
        // Values are stored in text form so convert the byte array to text
        String time = new String(data, StandardCharsets.UTF_8).trim();
        int timeLength = time.length();

        if (timeLength >= 2)
            hour = Byte.parseByte(time.substring(0, 2));
        if (timeLength >= 4)
            minute = Byte.parseByte(time.substring(2, 4));
        if (timeLength >= 6)
            second = Byte.parseByte(time.substring(4, 6));
        if (timeLength >= 13)
            microseconds = Long.parseLong(time.substring(7, 13));
    }

    @Override
    public ValueRepresentation getValueRepresentation() {
        return valueRepresentation;
    }

    @Override
    public String toString() {
        return String.format("%02d:%02d:%02d.%06d", hour, minute, second, microseconds);
    }
}
