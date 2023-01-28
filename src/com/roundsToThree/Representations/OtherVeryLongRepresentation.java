package com.roundsToThree.Representations;

import com.roundsToThree.DataProcessing.ByteUtils;
import com.roundsToThree.Structures.OtherVeryLong;
import com.roundsToThree.Structures.ValueRepresentation;

import java.util.Arrays;


public class OtherVeryLongRepresentation extends Representation {

    // Class specific variables
    public OtherVeryLong[] value;

    // The Value Representation of this class
    public static final ValueRepresentation valueRepresentation = ValueRepresentation.VALUE_REPRESENTATION_OV;

    // Converts an byte array of OL VR into an array of Other Longs
    public OtherVeryLongRepresentation(byte[] data) {
        if (data == null || data.length == 0)
            return;
        // Split every 8 bytes
        value = new OtherVeryLong[data.length / 8];
        // Todo: confirm this is how VR OV works
        for (int i = 0; i < value.length; i++) {
            value[i] = new OtherVeryLong(ByteUtils.longFrom64Bit(Arrays.copyOfRange(data, 8 * i, 8 * (i + 1))));
        }

    }

    @Override
    public ValueRepresentation getValueRepresentation() {
        return valueRepresentation;
    }
}
