package com.roundsToThree.Representations;

import com.roundsToThree.DataProcessing.ByteUtils;
import com.roundsToThree.Structures.SignedVeryLong;
import com.roundsToThree.Structures.ValueRepresentation;

import java.util.Arrays;

public class SignedVeryLongRepresentation extends Representation {

    // Class specific variables
    public SignedVeryLong[] value;

    // The Value Representation of this class
    public static final ValueRepresentation valueRepresentation = ValueRepresentation.VALUE_REPRESENTATION_SV;

    // Converts an byte array of OL VR into an array of Other Longs
    public SignedVeryLongRepresentation(byte[] data) {
        if (data == null || data.length == 0)
            return;
        // Split every 8 bytes
        value = new SignedVeryLong[data.length / 8];
        // Todo: confirm this is how VR OV works
        for (int i = 0; i < value.length; i++) {
            value[i] = new SignedVeryLong(ByteUtils.longFrom64Bit(Arrays.copyOfRange(data, 8 * i, 8 * (i + 1))));
        }

    }

    @Override
    public ValueRepresentation getValueRepresentation() {
        return valueRepresentation;
    }
}
