package com.roundsToThree.Representations;

import com.roundsToThree.DataProcessing.StringUtils;
import com.roundsToThree.Structures.LongText;
import com.roundsToThree.Structures.ValueRepresentation;

import java.nio.charset.StandardCharsets;

public class LongTextRepresentation extends Representation {

    // Class specific variables
    // Array is used here even though the LT VR only allows for one value
    public LongText[] value;

    // The Value Representation of this class
    public static final ValueRepresentation valueRepresentation = ValueRepresentation.VALUE_REPRESENTATION_LT;

    // Converts a byte array of VR_LT type to LongText
    public LongTextRepresentation(byte[] data) {
        if (data == null || data.length == 0)
            return;
        value = new LongText[1];
        // Trim trailing spaces only as leading spaces are deemed "significant"
        value[0] = new LongText(StringUtils.trimTrailing(new String(data, StandardCharsets.UTF_8)));
    }

    @Override
    public ValueRepresentation getValueRepresentation() {
        return valueRepresentation;
    }
}

