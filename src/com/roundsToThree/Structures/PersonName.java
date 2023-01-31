package com.roundsToThree.Structures;

public class PersonName {

    public String familyName;
    public String givenName;
    public String middleName;
    public String prefix;
    public String suffix;

    // Simple constructor
    public PersonName(
            String givenName,
            String familyName
    ) {
        this.givenName = givenName;
        this.familyName = familyName;
    }

    // Complete constructor
    public PersonName(
            String prefix,
            String givenName,
            String middleName,
            String familyName,
            String suffix
    ) {
        this.prefix = prefix;
        this.givenName = givenName;
        this.middleName = middleName;
        this.familyName = familyName;
        this.suffix = suffix;
    }

    @Override
    public String toString() {
        // todo: handle missing name
        String out = "";
        if (familyName != null)
            out += familyName + ", ";
        if (prefix != null)
            out += prefix + " ";
        if (givenName != null) {
            out += givenName;
            // only add space if there will be a middle name
            // too, this way the suffix wont have a space in
            // front of the comma
            if (middleName != null)
                out += " ";
        }
        if (middleName != null)
            out += middleName;
        if (suffix != null)
            out += ", " + suffix;

        return out;
    }
}
