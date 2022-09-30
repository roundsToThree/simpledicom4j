package com.roundsToThree.Representations;

import com.roundsToThree.Structures.ValueRepresentation;
import com.sun.jdi.Value;

import java.nio.charset.StandardCharsets;

public class Representation {
    public byte[] value;

    // Value Representation of this class
    private static final ValueRepresentation valueRepresentation = ValueRepresentation.VALUE_REPRESENTATION_UN;

    public Representation() {
    }

    @Override
    public String toString() {

        try {

            ValueRepresentation vr = (ValueRepresentation) this.getClass().getDeclaredField("valueRepresentation").get(this);

            if (vr != ValueRepresentation.VALUE_REPRESENTATION_UN) {
                Object[] val = (Object[]) (this.getClass().getDeclaredField("value").get(this));

                // No value
                if (val == null || val.length == 0)
                    return "N/A";

                // One value
                if (val.length == 1 && val[0] != null)
                    return val[0].toString();

                // Multiple values
                StringBuilder returnStr = new StringBuilder("[");
                for (int i = 0; i < val.length; i++) {
                    if (val[i] == null)
                        continue;

                    // Prepend a comma if it's not the first item in the array
                    if (i != 0)
                        returnStr.append(", ");

                    returnStr.append(val[i].toString());
                }
                returnStr.append("]");
                return returnStr.toString();

            }
        } catch (Exception e) {

            System.out.println("Error: " + e);
        }


        String returnString = "";
        if (value == null || value.length == 0)
            return "N/A";
        else {
            String str = (new String(value, StandardCharsets.UTF_8));
            returnString += str.substring(0, Math.min(str.length(), 32));
            if (returnString.length() == 32 && value.length > 32)
                returnString += "...";
        }

        return returnString;
    }

    public ValueRepresentation getValueRepresentation() {
        return valueRepresentation;
    }

}
