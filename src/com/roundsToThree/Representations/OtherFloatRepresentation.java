package com.roundsToThree.Representations;

import com.roundsToThree.DataProcessing.ByteUtils;
import com.roundsToThree.Structures.OtherFloat;
import com.roundsToThree.Structures.ValueRepresentation;

import java.util.Arrays;

public class OtherFloatRepresentation extends Representation {

    // Class specific variables
    public OtherFloat[] value;

    // The Value Representation of this class
    public static final ValueRepresentation valueRepresentation = ValueRepresentation.VALUE_REPRESENTATION_OF;

    // Converts an byte array of OF VR into an array of Other Floats
    public OtherFloatRepresentation(byte[] data) {
        if (data == null || data.length == 0)
            return;

        // Todo: confirm that this is actually how OF VR should be treated
        // Split every 4 bytes
        value = new OtherFloat[data.length / 4];

        for (int i = 0; i < data.length; i += 4)
            value[i / 4] = new OtherFloat(ByteUtils.floatFrom32Bit(Arrays.copyOfRange(data, i, i + 4)));

    }

    @Override
    public ValueRepresentation getValueRepresentation() {
        return valueRepresentation;
    }
}
