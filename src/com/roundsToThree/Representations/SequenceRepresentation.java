package com.roundsToThree.Representations;

import com.roundsToThree.Structures.ValueRepresentation;

import java.util.HashMap;

public class SequenceRepresentation extends Representation {

    public HashMap<Integer, Representation> elements = new HashMap<>();
    ;

    // Value Representation of this class
    public static final ValueRepresentation valueRepresentation = ValueRepresentation.VALUE_REPRESENTATION_SQ;

    public SequenceRepresentation() {

    }

    @Override
    public ValueRepresentation getValueRepresentation() {
        return valueRepresentation;
    }

    // A special to string method for listing in a tree like structure
    @Override
    public String toString() {
        StringBuilder returnString = new StringBuilder();
        if (elements == null || elements.size() == 0)
            return "No Items";

        for (HashMap.Entry<Integer, Representation> entry : elements.entrySet()) {
            long key = entry.getKey();
            long elementNumber = key & 0xFFFF;
            long groupNumber = (key >> 16) & 0xFFFF;
            Representation representation = entry.getValue();

            // Convert key into hex
            returnString.append(String.format("(%04X,%04X) \n\t|--> %s\n\t|--> %s\n", groupNumber, elementNumber, representation.getValueRepresentation().toDetailedString(), representation.toString()).replaceAll("\n", "\n\t\t"));
        }
        return returnString.toString();
    }
}
