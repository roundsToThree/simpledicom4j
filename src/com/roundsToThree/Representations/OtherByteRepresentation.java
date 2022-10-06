package com.roundsToThree.Representations;

import com.roundsToThree.Structures.OtherByte;
import com.roundsToThree.Structures.ValueRepresentation;

public class OtherByteRepresentation extends Representation {

    // Class specific variables
    // Array is used here even though the OB VR only allows for one value
    public OtherByte[] value;

    // The Value Representation of this class
    public static final ValueRepresentation valueRepresentation = ValueRepresentation.VALUE_REPRESENTATION_OB;

    // Copies a byte array into the OtherByte container
    public OtherByteRepresentation(byte[] data) {
        if (data == null || data.length == 0)
            return;
        value = new OtherByte[1];
        value[0] = new OtherByte(data);
    }

    @Override
    public ValueRepresentation getValueRepresentation() {
        return valueRepresentation;
    }
}

