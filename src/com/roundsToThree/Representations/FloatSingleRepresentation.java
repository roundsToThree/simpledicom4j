package com.roundsToThree.Representations;

import com.roundsToThree.DataProcessing.ByteUtils;
import com.roundsToThree.Structures.ValueRepresentation;

public class FloatSingleRepresentation extends Representation {

    // Class specific variables
    public float value = 0;

    // The Value Representation of this class
    private static final ValueRepresentation valueRepresentation = ValueRepresentation.VALUE_REPRESENTATION_FL;

    // Converts a byte array of VR_FL type to DecimalString
    public FloatSingleRepresentation(byte[] data) {
        // 4 byte packet
        if (data == null || data.length != 4)
            return;

        value = ByteUtils.floatFrom32Bit(data);
    }

    @Override
    public ValueRepresentation getValueRepresentation() {
        return valueRepresentation;
    }

    @Override
    public String toString() {
        return Float.toString(value);
    }

}
