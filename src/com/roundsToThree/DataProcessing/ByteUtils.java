package com.roundsToThree.DataProcessing;

// Aids in the conversion of byte arrays
public class ByteUtils {

    public static int intFrom16Bit(byte[] arr) {
        return
                ((arr[0] & 0xFF)) |
                        ((arr[1] & 0xFF) << 8);
    }

    public static long longFrom32Bit(byte[] arr) {
        return
                ((arr[0] & 0xFFL)) |
                        ((arr[1] & 0xFFL) << 8) |
                        ((arr[2] & 0xFFL) << 16) |
                        ((arr[3] & 0xFFL) << 24);
    }

}
