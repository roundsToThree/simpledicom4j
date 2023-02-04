package com.roundsToThree.Representations;

import com.roundsToThree.DataProcessing.ByteUtils;
import com.roundsToThree.Structures.SignedShort;
import com.roundsToThree.Structures.ValueRepresentation;

import java.util.Arrays;

public class SignedShortRepresentation extends Representation {

    // Class specific variables
    public SignedShort[] value;

    // The Value Representation of this class
    public static final ValueRepresentation valueRepresentation = ValueRepresentation.VALUE_REPRESENTATION_SS;

    // Converts an byte array of SS VR into an array of Signed Shorts
    public SignedShortRepresentation(byte[] data) {
        if (data == null || data.length == 0)
            return;
        // Split every 2 bytes
        value = new SignedShort[data.length / 2];
        // Todo: confirm this is how VR SS works
        for (int i = 0; i < value.length; i++) {
            value[i] = new SignedShort(ByteUtils.wordFrom16Bit(Arrays.copyOfRange(data, 2 * i, 2 * (i + 1))));
        }

    }

    @Override
    public ValueRepresentation getValueRepresentation() {
        return valueRepresentation;
    }
}