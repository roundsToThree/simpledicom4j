package com.roundsToThree.DataProcessing;

import java.io.BufferedInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.nio.ByteBuffer;
import java.nio.ByteOrder;
import java.util.Arrays;
import java.util.HexFormat;

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

    // Takes a char array and converts it to a number:
    // E.g: '1','0'  to 10

    public static long convertCharactersToLong(byte[] data) {
        if (data == null || data.length == 0)
            return 0;
        long returnVal = 0;
        byte sign = ((data[0] == (byte) '-') ? (byte) -1 : 1);
        long scale = 1;
        for (int i = data.length - 1; i >= (sign == -1 ? 1 : 0); i--) {
            returnVal += sign * (data[i] - '0') * scale;
            scale *= 10;
        }

        return returnVal;
    }

    public static String byteArrayToHexString(byte[] data) {
        return HexFormat.of().formatHex(data);
    }

    public static byte[] getBytesUntilDeliminator(BufferedInputStream buffered_reader, byte[] sequenceDeliminator) {
        // Create a buffer to fill and check
        int searchLength = sequenceDeliminator.length;
        byte[] search = new byte[searchLength];

        try {
            ByteArrayOutputStream baos = new ByteArrayOutputStream();
            while (buffered_reader.available() > 0) {

                byte b = (byte) buffered_reader.read();
                baos.write(b);

                // Shift the search array to the left
                System.arraycopy(search, 1, search, 0, searchLength - 1);
                search[searchLength - 1] = b;

                if (Arrays.equals(search, sequenceDeliminator))
                    return baos.toByteArray();

            }
        } catch (IOException e) {
            return null;
        }
        return null;
    }

    // Get an IEE 754 Float from a sequence of 4 bytes
    public static float floatFrom32Bit(byte[] data) {
        // Method from https://stackoverflow.com/a/13469763
        return ByteBuffer.wrap(data).order(ByteOrder.LITTLE_ENDIAN).getFloat();
    }

}
