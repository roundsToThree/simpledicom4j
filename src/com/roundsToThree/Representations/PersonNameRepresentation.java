package com.roundsToThree.Representations;

import com.roundsToThree.Structures.PersonName;
import com.roundsToThree.Structures.ValueRepresentation;

import java.nio.charset.StandardCharsets;
import java.util.Arrays;

public class PersonNameRepresentation extends Representation {

    // Class specific variables
    public PersonName[] value;


    // Value Representation of this class
    public static final ValueRepresentation valueRepresentation = ValueRepresentation.VALUE_REPRESENTATION_PN;

    /*
    Note:
    For vet usage, familyName is the pet owner's family / organisation.
    givenName is maintained, middleName, prefix and suffix are not used.

    todo: implement this => '=' used to represent ideographic and phonetic representations
    I still dont understand it
     */

    // PersonRepresentation from raw data in VR_PN item
    public PersonNameRepresentation(byte[] data) {
        if (data == null)
            return;

        // todo: support alternate charsets that the DICOM spec supports
        String[] contents = new String(data, StandardCharsets.UTF_8).split("\\\\");
        value = new PersonName[contents.length];

        // Create a PersonName for each content
        for (int i = 0; i < contents.length; ++i) {
            // Force there to be 5 segments, Only the first segment is non optional and for a 3rd segment, the 2nd one must be given name.
            String[] segments = Arrays.copyOf(contents[i].trim().split("\\^"), 5);
            // Segment order
            // FamilyName, GivenName, MiddleName, Prefix, Suffix

            value[i] = new PersonName(
                    segments[3], // Prefix
                    segments[1], // GivenName
                    segments[2], // MiddleName
                    segments[0], // FamilyName
                    segments[4]  // Suffix
            );
        }
    }

    @Override
    public ValueRepresentation getValueRepresentation() {
        return valueRepresentation;
    }


}
