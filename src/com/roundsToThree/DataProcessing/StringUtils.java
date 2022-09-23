package com.roundsToThree.DataProcessing;

public class StringUtils {

    public static String noNull(String text) {
        if (text == null)
            return "";
        return text;
    }

    public static String limitStringLength(String input, int length) {
        // If the string is longer than the length, substring
        if (input.length() > length)
            return input.substring(0, length);
        return input;
    }

}
