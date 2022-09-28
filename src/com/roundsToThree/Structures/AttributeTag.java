package com.roundsToThree.Structures;

import com.roundsToThree.DataProcessing.ByteUtils;
import com.roundsToThree.DataProcessing.StringUtils;

public class AttributeTag {
    public int elementNumber;
    public int groupNumber;

    public AttributeTag(int groupNumber, int elementNumber) {
        this.groupNumber = groupNumber;
        this.elementNumber = elementNumber;
    }

    @Override
    public String toString() {
        return String.format("(%04X,%04X)", elementNumber, groupNumber);
    }
}
