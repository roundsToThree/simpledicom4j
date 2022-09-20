package com.roundsToThree.Structures;

import java.nio.charset.StandardCharsets;

public class ValueRepresentation {
    // Class specific variables
    int valueRepresentation;

    ValueRepresentation(int VR_CODE) {
        valueRepresentation = VR_CODE;
    }


    // Returns the 2 letter string of the VR
    public String toString() {
        // Convert the representation into a byte array
        byte[] VR = new byte[2];
        VR[0] = (byte) (valueRepresentation & 0xFF);
        VR[1] = (byte) ((valueRepresentation >> 8) & 0xFF);
        return new String(VR, StandardCharsets.UTF_8);
    }


    // Returns the full name of the VR string (e.g. UL => "Unsigned Long")
    public String toDetailedString() {
        // Find the VR in the mapping and return the detailed string
        for (Object[] vrMapping : VR_MAPPINGS)
            if ((int) vrMapping[0] == valueRepresentation)
                return (String) vrMapping[2];

        // No match (Unexpected case)
        return "N/A";
    }

    public static ValueRepresentation getRepresentationFromBytes(byte[] vr) {
        return new ValueRepresentation(vr[0] + (vr[1] << 8));
    }


    final static int VR_AE = 'A' + ('E' << 8);
    final static int VR_AS = 'A' + ('S' << 8);
    final static int VR_AT = 'A' + ('T' << 8);
    final static int VR_CS = 'C' + ('S' << 8);
    final static int VR_DA = 'D' + ('A' << 8);
    final static int VR_DS = 'D' + ('S' << 8);
    final static int VR_DT = 'D' + ('T' << 8);
    final static int VR_FL = 'F' + ('L' << 8);
    final static int VR_FD = 'F' + ('D' << 8);
    final static int VR_IS = 'I' + ('S' << 8);
    final static int VR_LO = 'L' + ('O' << 8);
    final static int VR_LT = 'L' + ('T' << 8);
    final static int VR_OB = 'O' + ('B' << 8);
    final static int VR_OD = 'O' + ('D' << 8);
    final static int VR_OF = 'O' + ('F' << 8);
    final static int VR_OL = 'O' + ('L' << 8);
    final static int VR_OV = 'O' + ('V' << 8);
    final static int VR_OW = 'O' + ('W' << 8);
    final static int VR_PN = 'P' + ('N' << 8);
    final static int VR_SH = 'S' + ('H' << 8);
    final static int VR_SL = 'S' + ('L' << 8);
    final static int VR_SQ = 'S' + ('Q' << 8);
    final static int VR_SS = 'S' + ('S' << 8);
    final static int VR_ST = 'S' + ('T' << 8);
    final static int VR_SV = 'S' + ('V' << 8);
    final static int VR_TM = 'T' + ('M' << 8);
    final static int VR_UC = 'U' + ('C' << 8);
    final static int VR_UI = 'U' + ('I' << 8);
    final static int VR_UL = 'U' + ('L' << 8);
    final static int VR_UN = 'U' + ('N' << 8);
    final static int VR_UR = 'U' + ('R' << 8);
    final static int VR_US = 'U' + ('S' << 8);
    final static int VR_UT = 'U' + ('T' << 8);
    final static int VR_UV = 'U' + ('V' << 8);
    final static int VR_IMPLICIT = -1;

    /* Properties of each value representation
     inner arrray is structured as such:

     (int)     VR                  => The VR code
     (boolean) 16-bit data length  => a value that describes what structure the data element takes in the file
     => if true, the data element uses a VR/dataLength combined field (ref dicom.nema.org => PS3.5 Table 7/1-2)
     => if false, the data element contains a seperate 4 bytes for a 32-bit data length
     (String)  Detailed name       => the formal name of the specified Value Representation
     */
    final static Object[][] VR_MAPPINGS = {
            {VR_AE, true, "Application Entity"},
            {VR_AS, true, "Age String"},
            {VR_AT, true, "Attribute Tag"},
            {VR_CS, true, "Code String"},
            {VR_DA, true, "Date"},
            {VR_DS, true, "Decimal String"},
            {VR_DT, true, "Date Time"},
            {VR_FL, true, "Floating Point (Single)"},
            {VR_FD, true, "Floating Point (Double)"},
            {VR_IS, true, "Integer String"},
            {VR_LO, true, "Long String"},
            {VR_LT, true, "Long text"},
            {VR_OB, false, "Other Byte"},
            {VR_OD, false, "Other Double"},
            {VR_OF, false, "Other Float"},
            {VR_OL, false, "Other Long"},
            {VR_OV, false, "Other Very Long (64-bit)"},
            {VR_OW, false, "Other Word"},
            {VR_PN, true, "Person Name"},
            {VR_SH, true, "Short String"},
            {VR_SL, true, "Signed Long"},
            {VR_SQ, false, "Sequence of Items"},
            {VR_SS, true, "Signed Short"},
            {VR_ST, true, "Short Text"},
            {VR_SV, false, "Signed Very Long (64-bit)"},
            {VR_TM, true, "Time"},
            {VR_UC, false, "Unlimited Characters"},
            {VR_UI, true, "Unique Identifier (UID)"},
            {VR_UL, true, "Unsigned Long"},
            {VR_UN, false, "Unknown"},
            {VR_UR, false, "URI / URL"},
            {VR_US, true, "Unsigned Short"},
            {VR_UT, false, "Unlimited Text"},
            {VR_UV, false, "Unsigned Very Long (64-bit)"},
            {VR_IMPLICIT, false, "Implicit VR"}
    };

    public static final byte[][] valueRepresentations = {
            {'A', 'E'}, // Application Entity - Up to 16 Bytes - Ignore trailing/leading 0x20, disregard only 0x20 packets
            {'A', 'S'}, // Age String         - 4 Bytes        - AAAB A = (number) B = (D)ay, (W)eek, (M)onths, (Y)ears
            {'A', 'T'}, // Attribute Tag      - 4 Bytes        - Data Element tags in Little-Endian order (0018) = 18 00
            {'C', 'S'},
            {'D', 'A'},
            {'D', 'S'},
            {'D', 'T'},
            {'F', 'L'},
            {'F', 'D'},
            {'I', 'S'},
            {'L', 'O'},
            {'L', 'T'},
            {'O', 'B'},
            {'O', 'D'},
            {'O', 'F'},
            {'O', 'L'},
            {'O', 'V'},
            {'O', 'W'},
            {'P', 'N'},
            {'S', 'H'},
            {'S', 'L'},
            {'S', 'S'},
            {'S', 'Q'},
            {'S', 'T'},
            {'S', 'V'},
            {'T', 'M'},
            {'U', 'C'},
            {'U', 'I'},
            {'U', 'L'},
            {'U', 'N'},
            {'U', 'R'},
            {'U', 'S'},
            {'U', 'T'},
            {'U', 'V'}
    };
}


