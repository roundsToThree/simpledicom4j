package com.roundsToThree.Representations;

import com.roundsToThree.DataProcessing.ByteUtils;
import com.roundsToThree.Structures.FloatDouble;
import com.roundsToThree.Structures.ValueRepresentation;

import java.util.Arrays;

public class FloatDoubleRepresentation extends Representation {

    // Class specific variables
    public FloatDouble[] value;

    // The Value Representation of this class
    public static final ValueRepresentation valueRepresentation = ValueRepresentation.VALUE_REPRESENTATION_FD;

    // Converts a byte array of VR_FD type to Float Double
    public FloatDoubleRepresentation(byte[] data) {
        if (data == null || data.length == 0)
            return;

        value = new FloatDouble[data.length / 8];

        // Split on every 8 bytes
        for (int i = 0; i < data.length; i += 8)
            value[i] = new FloatDouble(ByteUtils.doubleFrom64Bit(Arrays.copyOfRange(data, i, i + 8)));
    }

    @Override
    // todo: use similar approach as toString to merge these all into the parent superclass
    public ValueRepresentation getValueRepresentation() {
        return valueRepresentation;
    }

}
