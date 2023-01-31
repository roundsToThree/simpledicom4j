package com.roundsToThree.Structures;

import com.roundsToThree.Exception.InvalidRepresentation;

public class ShortString {
    String value;

    public ShortString(String value) {
        // Check Validity
        checkValidity(value);

        this.value = value;
    }

    @Override
    public String toString() {
        return value;
    }


    // Todo: Consider moving these checks into a common class and having static variables for each representation that
    //       define the parameters
    // Also todo: consider making this function in all other representations
    // Also consider whether this should throw exceptiosn or just an error message / false
    public boolean checkValidity(String value) {
        // Ensure length
        if (value.length() > 16)
            throw new InvalidRepresentation("Contents of representation exceed max length (16 for Short String)", null);
        if (value.contains("\\"))
            throw new InvalidRepresentation("Contents of representation cannot contain a backslash", null);

        return true;
    }
}
