package com.roundsToThree.Representations;

import com.roundsToThree.DataProcessing.ByteUtils;
import com.roundsToThree.Structures.ValueRepresentation;

public class FloatDoubleRepresentation extends Representation {

    // Class specific variables
    public double value = 0;

    // The Value Representation of this class
    private static final ValueRepresentation valueRepresentation = ValueRepresentation.VALUE_REPRESENTATION_FD;

    // Converts a byte array of VR_FD type to Float Double
    public FloatDoubleRepresentation(byte[] data) {
        // 4 byte packet
        if (data == null || data.length != 8)
            return;

        value = ByteUtils.doubleFrom64Bit(data);
    }

    @Override
    public ValueRepresentation getValueRepresentation() {
        return valueRepresentation;
    }

    @Override
    public String toString() {
        return Double.toString(value);
    }

}
