package com.roundsToThree.Representations;

import com.roundsToThree.DataProcessing.ByteUtils;
import com.roundsToThree.Structures.AttributeTag;
import com.roundsToThree.Structures.ValueRepresentation;

import java.util.Arrays;

public class AttributeTagRepresentation extends Representation {

    // Class specific variables
    public AttributeTag[] value;

    // The Value Representation of this class
    public static final ValueRepresentation valueRepresentation = ValueRepresentation.VALUE_REPRESENTATION_AT;

    // Converts a byte array of VR_AT type to AttributeTag
    public AttributeTagRepresentation(byte[] data) {
        // data should be 4 bytes (2b each)
        // if its any more than 4, it should be treated as multiple attribute tags
        if (data == null)
            return;

        // Allocate memory for each attribute tag and import them
        value = new AttributeTag[data.length / 4];

        for (int i = 0; i < value.length; i++) {
            int elementNumber = (short) ByteUtils.intFrom16Bit(Arrays.copyOfRange(data, 4 * i, 4 * i + 2));
            int groupNumber = (short) ByteUtils.intFrom16Bit(Arrays.copyOfRange(data, 4 * i + 2, 4 * i + 4));
            value[i] = new AttributeTag(elementNumber, groupNumber);
        }
    }


    @Override
    public ValueRepresentation getValueRepresentation() {
        return valueRepresentation;
    }


}
