package com.roundsToThree.Structures;

import com.roundsToThree.DataProcessing.ByteUtils;

import java.util.Arrays;

public class OtherByte {
    public byte[] value;

    public OtherByte(byte[] value) {
        this.value = value;
    }

    @Override
    public String toString() {
        return ByteUtils.byteArrayToHexString(Arrays.copyOfRange(value, 0, 64));
    }
}
