package com.roundsToThree.Representations;

import com.roundsToThree.DataProcessing.ByteUtils;
import com.roundsToThree.Structures.ValueRepresentation;

import java.util.Arrays;

public class AttributeTagRepresentation extends Representation {

    // Class specific variables
    public short elementNumber = 0;
    public short groupNumber = 0;

    // The Value Representation of this class
    private static final ValueRepresentation valueRepresentation = ValueRepresentation.VALUE_REPRESENTATION_AT;

    // Converts a byte array of VR_AT type to AttributeTag
    public AttributeTagRepresentation(byte[] data) {
        // data should be 4 bytes (2b each)
        if (data == null || data.length != 4)
            return;
        elementNumber = (short) ByteUtils.intFrom16Bit(Arrays.copyOfRange(data, 0, 2));
        groupNumber = (short) ByteUtils.intFrom16Bit(Arrays.copyOfRange(data, 2, 4));
    }

    @Override
    public ValueRepresentation getValueRepresentation() {
        return valueRepresentation;
    }

    @Override
    public String toString() {
        return String.format("(%04x, %04x)", elementNumber, groupNumber);
    }


}
