package com.roundsToThree.Representations;

import com.roundsToThree.DataProcessing.StringUtils;
import com.roundsToThree.Structures.ShortText;
import com.roundsToThree.Structures.ValueRepresentation;

import java.nio.charset.StandardCharsets;

public class ShortTextRepresentation extends Representation {

    // Class specific variables
    // Array is used here even though the ST VR only allows for one value
    public ShortText[] value;

    // The Value Representation of this class
    public static final ValueRepresentation valueRepresentation = ValueRepresentation.VALUE_REPRESENTATION_ST;

    // Converts a byte array of VR_ST type to ShortText
    public ShortTextRepresentation(byte[] data) {
        if (data == null || data.length == 0)
            return;
        value = new ShortText[1];
        // Trim trailing spaces only as leading spaces are deemed "significant"
        value[0] = new ShortText(StringUtils.trimTrailing(new String(data, StandardCharsets.UTF_8)));
    }

    @Override
    public ValueRepresentation getValueRepresentation() {
        return valueRepresentation;
    }
}

