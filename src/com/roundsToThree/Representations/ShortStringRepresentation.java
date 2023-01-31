package com.roundsToThree.Representations;

import com.roundsToThree.Structures.ShortString;
import com.roundsToThree.Structures.ValueRepresentation;

import java.nio.charset.StandardCharsets;

public class ShortStringRepresentation extends Representation {

    // Class specific variables
    public ShortString[] value;

    // The Value Representation of this class
    public static final ValueRepresentation valueRepresentation = ValueRepresentation.VALUE_REPRESENTATION_SS;

    // Converts an byte array of SS VR into an array of Short Strings
    // todo: confirm this is how short strings are treated
    public ShortStringRepresentation(byte[] data) {
        if (data == null || data.length == 0)
            return;

        String[] strings = new String(data, StandardCharsets.UTF_8).split("\\\\");
        value = new ShortString[strings.length];

        for (int i = 0; i < value.length; ++i)
            value[i] = new ShortString(strings[i]);
    }

    @Override
    public ValueRepresentation getValueRepresentation() {
        return valueRepresentation;
    }
}