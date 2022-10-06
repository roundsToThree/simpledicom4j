package com.roundsToThree.Representations;

import com.roundsToThree.Structures.ValueRepresentation;

public class OtherDoubleRepresentation extends Representation {

    // Class specific variables
    public OtherDouble[] value;

    // The Value Representation of this class
    public static final ValueRepresentation valueRepresentation = ValueRepresentation.VALUE_REPRESENTATION_OD;

    // Converts an byte array of OD VR into an array of Other Doubles
    public OtherDoubleRepresentation(byte[] data) {
        if (data == null || data.length == 0)
            return;

        // Todo: confirm that this is actually how OD VR should be treated
        // Split every 8 bytes
        value = new OtherDouble[data.length / 8];

    }

    @Override
    public ValueRepresentation getValueRepresentation() {
        return valueRepresentation;
    }
}

