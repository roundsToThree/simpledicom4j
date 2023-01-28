package com.roundsToThree.Representations;

import com.roundsToThree.DataProcessing.ByteUtils;
import com.roundsToThree.Structures.OtherLong;
import com.roundsToThree.Structures.ValueRepresentation;

import java.util.Arrays;

public class OtherLongRepresentation extends Representation {

    // Class specific variables
    public OtherLong[] value;

    // The Value Representation of this class
    public static final ValueRepresentation valueRepresentation = ValueRepresentation.VALUE_REPRESENTATION_OL;

    // Converts an byte array of OL VR into an array of Other Longs
    public OtherLongRepresentation(byte[] data) {
        if (data == null || data.length == 0)
            return;

        // Todo: confirm that this is actually how OL VR should be treated
        // Split every 4 bytes
        value = new OtherLong[data.length / 4];
        for (int i = 0; i < value.length; i++) {
            value[i] = new OtherLong(ByteUtils.longFrom32Bit(Arrays.copyOfRange(data, 4 * i, 4 * (i + 1))));
        }

    }

    @Override
    public ValueRepresentation getValueRepresentation() {
        return valueRepresentation;
    }
}