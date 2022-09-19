package com.roundsToThree.Structures;

import java.nio.charset.StandardCharsets;

public class DataElement {

    // Class specific variables
    public ValueRepresentation valueRepresentation;
    // dataLength may be a 32bit uint so ensure it corrected for when used.
    public int dataLength;

    // todo: Needs refactoring
    public byte[] value;

    // Stored as shorts but returned as ints
    private final short groupNumber;
    private final short elementNumber;

    // Instance method
    // Casts ints to shorts internally for convenience
    public DataElement(int groupNumber, int elementNumber) {
        this.groupNumber = (short) groupNumber;
        this.elementNumber = (short) elementNumber;

    }

    // Returns the group number (with type conversion)
    int getGroupNumber() {
        // Turn unsigned "short" to an integer
        return groupNumber & 0x0000ffff;
    }

    // Returns the element number (with type conversion)
    int getElementNumber() {
        // Turn unsinged "short" to an integer
        return elementNumber & 0x0000ffff;
    }

    // Returns a string with the tag identifier in hex, its value representation
    public String getSummary() {
        String strc;
        if (value == null || value.length < 2)
            strc = "N/A";
        else {
            strc = (new String(value, StandardCharsets.UTF_8));
            strc = strc.substring(0, Math.min(strc.length(), 32));
            if (strc.length() == 32 && value.length > 32)
                strc += "...";
        }


        return "(" + String.format("%04x", groupNumber) + "," + String.format("%04x", elementNumber) + ") => " + strc;
    }
}