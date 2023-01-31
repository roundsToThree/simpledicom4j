package com.roundsToThree.Representations;

import com.roundsToThree.DataProcessing.ByteUtils;
import com.roundsToThree.Structures.OtherWord;
import com.roundsToThree.Structures.ValueRepresentation;

import java.util.Arrays;

public class OtherWordRepresentation extends Representation {

    // Class specific variables
    public OtherWord[] value;

    // The Value Representation of this class
    public static final ValueRepresentation valueRepresentation = ValueRepresentation.VALUE_REPRESENTATION_OW;

    // Converts an byte array of OW VR into an array of Other Words
    public OtherWordRepresentation(byte[] data) {
        if (data == null || data.length == 0)
            return;
        // Split every 2 bytes
        value = new OtherWord[data.length / 2];
        // Todo: confirm this is how VR OW works
        for (int i = 0; i < value.length; i++) {
            value[i] = new OtherWord(ByteUtils.wordFrom16Bit(Arrays.copyOfRange(data, 2 * i, 2 * (i + 1))));
        }

    }

    @Override
    public ValueRepresentation getValueRepresentation() {
        return valueRepresentation;
    }
}

