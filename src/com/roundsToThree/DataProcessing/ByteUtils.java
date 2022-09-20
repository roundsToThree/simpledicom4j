package com.roundsToThree.DataProcessing;

import java.util.Arrays;

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


    // Returns the point in a byte array that another byte array exists
    // takes a source, array to check for, and an index to start at
    public static int indexOfByteSequence(byte[] data, byte[] sequence, int start) {
        int index = start;

        // Create a search buffer the size of the sequence
        int searchLength = sequence.length;
        byte[] search = new byte[searchLength];
        System.arraycopy(data, start, search, 0, searchLength);

        // While the search buffer is not equal to the sequence, check the next byte
        while (!Arrays.equals(search, sequence)) {
            // Shift the contents of the array and and another byte from the data to it
            System.arraycopy(search, 1, search, 0, searchLength - 1);
            search[searchLength - 1] = data[index + searchLength];

            index++;

            if (index + searchLength > data.length)
                return -1;
        }

        return index;
    }


}
