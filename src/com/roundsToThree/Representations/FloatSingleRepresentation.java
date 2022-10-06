package com.roundsToThree.Representations;

import com.roundsToThree.DataProcessing.ByteUtils;
import com.roundsToThree.Structures.FloatSingle;
import com.roundsToThree.Structures.ValueRepresentation;

import java.util.Arrays;

public class FloatSingleRepresentation extends Representation {

    // Class specific variables
    public FloatSingle[] value;

    // The Value Representation of this class
    public static final ValueRepresentation valueRepresentation = ValueRepresentation.VALUE_REPRESENTATION_FL;

    // Converts a byte array of VR_FL type to Float Single
    public FloatSingleRepresentation(byte[] data) {
        // 4 byte packet
        if (data == null || data.length == 0)
            return;

        value = new FloatSingle[data.length / 4];

        // Split on every 4 bytes
        for (int i = 0; i < data.length; i += 4)
            value[i] = new FloatSingle(ByteUtils.floatFrom32Bit(Arrays.copyOfRange(data, i, i + 4)));
    }

    @Override
    public ValueRepresentation getValueRepresentation() {
        return valueRepresentation;
    }

}
