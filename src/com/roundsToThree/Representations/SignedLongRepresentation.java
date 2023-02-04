package com.roundsToThree.Representations;

import com.roundsToThree.DataProcessing.ByteUtils;
import com.roundsToThree.Structures.SignedLong;
import com.roundsToThree.Structures.ValueRepresentation;

import java.util.Arrays;

public class SignedLongRepresentation extends Representation {

    // Class specific variables
    public SignedLong[] value;

    // The Value Representation of this class
    public static final ValueRepresentation valueRepresentation = ValueRepresentation.VALUE_REPRESENTATION_SL;

    // Converts an byte array of SL VR into an array of Signed Longs
    public SignedLongRepresentation(byte[] data) {
        if (data == null || data.length == 0)
            return;

        // Todo: confirm that this is actually how OL SL should be treated
        // Split every 4 bytes
        value = new SignedLong[data.length / 4];
        for (int i = 0; i < value.length; i++) {
            value[i] = new SignedLong(ByteUtils.longFrom32Bit(Arrays.copyOfRange(data, 4 * i, 4 * (i + 1))));
        }

    }

    @Override
    public ValueRepresentation getValueRepresentation() {
        return valueRepresentation;
    }
}