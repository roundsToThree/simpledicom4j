package com.roundsToThree.Structures;

import com.roundsToThree.DataProcessing.ByteUtils;

import java.nio.charset.StandardCharsets;

public class ValueRepresentation {
    // Class specific variables
    public int VRCode;

    private ValueRepresentation(int VR_CODE) {
        VRCode = VR_CODE;
    }


    // Returns the 2 letter string of the VR
    public String toString() {
        // Convert the representation into a byte array
        byte[] VR = new byte[2];
        VR[0] = (byte) (VRCode & 0xFF);
        VR[1] = (byte) ((VRCode >> 8) & 0xFF);
        return new String(VR, StandardCharsets.UTF_8);
    }


    // Returns the full name of the VR string (e.g. UL => "Unsigned Long")
    public String toDetailedString() {
        // Find the VR in the mapping and return the detailed string
        for (Object[] vrMapping : VR_MAPPINGS)
            if ((int) vrMapping[0] == VRCode)
                return (String) vrMapping[2];

        // No match (Unexpected case)
        return "N/A";
    }

    public static ValueRepresentation getRepresentationFromBytes(byte[] vr) {

        // Find the code from the byte sequence
        int vr_num = ByteUtils.intFrom16Bit(vr);

        // Find that number in the VR_MAPPINGS and get the class from it
        for (Object[] vrMapping : VR_MAPPINGS)
            if (vr_num == (int) vrMapping[0])
                return (ValueRepresentation) vrMapping[3];

        // If it doesnt exist, return null
        return null;
    }


    public final static int VR_AE = 'A' | ('E' << 8);
    public final static int VR_AS = 'A' | ('S' << 8);
    public final static int VR_AT = 'A' | ('T' << 8);
    public final static int VR_CS = 'C' | ('S' << 8);
    public final static int VR_DA = 'D' | ('A' << 8);
    public final static int VR_DS = 'D' | ('S' << 8);
    public final static int VR_DT = 'D' | ('T' << 8);
    public final static int VR_FL = 'F' | ('L' << 8);
    public final static int VR_FD = 'F' | ('D' << 8);
    public final static int VR_IS = 'I' | ('S' << 8);
    public final static int VR_LO = 'L' | ('O' << 8);
    public final static int VR_LT = 'L' | ('T' << 8);
    public final static int VR_OB = 'O' | ('B' << 8);
    public final static int VR_OD = 'O' | ('D' << 8);
    public final static int VR_OF = 'O' | ('F' << 8);
    public final static int VR_OL = 'O' | ('L' << 8);
    public final static int VR_OV = 'O' | ('V' << 8);
    public final static int VR_OW = 'O' | ('W' << 8);
    public final static int VR_PN = 'P' | ('N' << 8);
    public final static int VR_SH = 'S' | ('H' << 8);
    public final static int VR_SL = 'S' | ('L' << 8);
    public final static int VR_SQ = 'S' | ('Q' << 8);
    public final static int VR_SS = 'S' | ('S' << 8);
    public final static int VR_ST = 'S' | ('T' << 8);
    public final static int VR_SV = 'S' | ('V' << 8);
    public final static int VR_TM = 'T' | ('M' << 8);
    public final static int VR_UC = 'U' | ('C' << 8);
    public final static int VR_UI = 'U' | ('I' << 8);
    public final static int VR_UL = 'U' | ('L' << 8);
    public final static int VR_UN = 'U' | ('N' << 8);
    public final static int VR_UR = 'U' | ('R' << 8);
    public final static int VR_US = 'U' | ('S' << 8);
    public final static int VR_UT = 'U' | ('T' << 8);
    public final static int VR_UV = 'U' | ('V' << 8);
    public final static int VR_IMPLICIT = -1;

    public final static ValueRepresentation VALUE_REPRESENTATION_AE = new ValueRepresentation(VR_AE);
    public final static ValueRepresentation VALUE_REPRESENTATION_AS = new ValueRepresentation(VR_AS);
    public final static ValueRepresentation VALUE_REPRESENTATION_AT = new ValueRepresentation(VR_AT);
    public final static ValueRepresentation VALUE_REPRESENTATION_CS = new ValueRepresentation(VR_CS);
    public final static ValueRepresentation VALUE_REPRESENTATION_DA = new ValueRepresentation(VR_DA);
    public final static ValueRepresentation VALUE_REPRESENTATION_DS = new ValueRepresentation(VR_DS);
    public final static ValueRepresentation VALUE_REPRESENTATION_DT = new ValueRepresentation(VR_DT);
    public final static ValueRepresentation VALUE_REPRESENTATION_FL = new ValueRepresentation(VR_FL);
    public final static ValueRepresentation VALUE_REPRESENTATION_FD = new ValueRepresentation(VR_FD);
    public final static ValueRepresentation VALUE_REPRESENTATION_IS = new ValueRepresentation(VR_IS);
    public final static ValueRepresentation VALUE_REPRESENTATION_LO = new ValueRepresentation(VR_LO);
    public final static ValueRepresentation VALUE_REPRESENTATION_LT = new ValueRepresentation(VR_LT);
    public final static ValueRepresentation VALUE_REPRESENTATION_OB = new ValueRepresentation(VR_OB);
    public final static ValueRepresentation VALUE_REPRESENTATION_OD = new ValueRepresentation(VR_OD);
    public final static ValueRepresentation VALUE_REPRESENTATION_OF = new ValueRepresentation(VR_OF);
    public final static ValueRepresentation VALUE_REPRESENTATION_OL = new ValueRepresentation(VR_OL);
    public final static ValueRepresentation VALUE_REPRESENTATION_OV = new ValueRepresentation(VR_OV);
    public final static ValueRepresentation VALUE_REPRESENTATION_OW = new ValueRepresentation(VR_OW);
    public final static ValueRepresentation VALUE_REPRESENTATION_PN = new ValueRepresentation(VR_PN);
    public final static ValueRepresentation VALUE_REPRESENTATION_SH = new ValueRepresentation(VR_SH);
    public final static ValueRepresentation VALUE_REPRESENTATION_SL = new ValueRepresentation(VR_SL);
    public final static ValueRepresentation VALUE_REPRESENTATION_SQ = new ValueRepresentation(VR_SQ);
    public final static ValueRepresentation VALUE_REPRESENTATION_SS = new ValueRepresentation(VR_SS);
    public final static ValueRepresentation VALUE_REPRESENTATION_ST = new ValueRepresentation(VR_ST);
    public final static ValueRepresentation VALUE_REPRESENTATION_SV = new ValueRepresentation(VR_SV);
    public final static ValueRepresentation VALUE_REPRESENTATION_TM = new ValueRepresentation(VR_TM);
    public final static ValueRepresentation VALUE_REPRESENTATION_UC = new ValueRepresentation(VR_UC);
    public final static ValueRepresentation VALUE_REPRESENTATION_UI = new ValueRepresentation(VR_UI);
    public final static ValueRepresentation VALUE_REPRESENTATION_UL = new ValueRepresentation(VR_UL);
    public final static ValueRepresentation VALUE_REPRESENTATION_UN = new ValueRepresentation(VR_UN);
    public final static ValueRepresentation VALUE_REPRESENTATION_UR = new ValueRepresentation(VR_UR);
    public final static ValueRepresentation VALUE_REPRESENTATION_US = new ValueRepresentation(VR_US);
    public final static ValueRepresentation VALUE_REPRESENTATION_UT = new ValueRepresentation(VR_UT);
    public final static ValueRepresentation VALUE_REPRESENTATION_UV = new ValueRepresentation(VR_UV);
    public final static ValueRepresentation VALUE_REPRESENTATION_IMPLICIT = new ValueRepresentation(VR_IMPLICIT);

    /* Properties of each value representation
     inner arrray is structured as such:

     (int)     VR                  => The VR code
     (boolean) 16-bit data length  => a value that describes what structure the data element takes in the file
     => if true, the data element uses a VR/dataLength combined field (ref dicom.nema.org => PS3.5 Table 7/1-2)
     => if false, the data element contains a seperate 4 bytes for a 32-bit data length
     (String)  Detailed name       => the formal name of the specified Value Representation
     (ValueRepresentation)         => A Static ValueRepresentation class
     */
    final static Object[][] VR_MAPPINGS = {
            {VR_AE, true, "Application Entity", VALUE_REPRESENTATION_AE},
            {VR_AS, true, "Age String", VALUE_REPRESENTATION_AS},
            {VR_AT, true, "Attribute Tag", VALUE_REPRESENTATION_AT},
            {VR_CS, true, "Code String", VALUE_REPRESENTATION_CS},
            {VR_DA, true, "Date", VALUE_REPRESENTATION_DA},
            {VR_DS, true, "Decimal String", VALUE_REPRESENTATION_DS},
            {VR_DT, true, "Date Time", VALUE_REPRESENTATION_DT},
            {VR_FL, true, "Floating Point (Single)", VALUE_REPRESENTATION_FL},
            {VR_FD, true, "Floating Point (Double)", VALUE_REPRESENTATION_FD},
            {VR_IS, true, "Integer String", VALUE_REPRESENTATION_IS},
            {VR_LO, true, "Long String", VALUE_REPRESENTATION_LO},
            {VR_LT, true, "Long text", VALUE_REPRESENTATION_LT},
            {VR_OB, false, "Other Byte", VALUE_REPRESENTATION_OB},
            {VR_OD, false, "Other Double", VALUE_REPRESENTATION_OD},
            {VR_OF, false, "Other Float", VALUE_REPRESENTATION_OF},
            {VR_OL, false, "Other Long", VALUE_REPRESENTATION_OL},
            {VR_OV, false, "Other Very Long (64-bit)", VALUE_REPRESENTATION_OV},
            {VR_OW, false, "Other Word", VALUE_REPRESENTATION_OW},
            {VR_PN, true, "Person Name", VALUE_REPRESENTATION_PN},
            {VR_SH, true, "Short String", VALUE_REPRESENTATION_SH},
            {VR_SL, true, "Signed Long", VALUE_REPRESENTATION_SL},
            {VR_SQ, false, "Sequence of Items", VALUE_REPRESENTATION_SQ},
            {VR_SS, true, "Signed Short", VALUE_REPRESENTATION_SS},
            {VR_ST, true, "Short Text", VALUE_REPRESENTATION_ST},
            {VR_SV, false, "Signed Very Long (64-bit)", VALUE_REPRESENTATION_SV},
            {VR_TM, true, "Time", VALUE_REPRESENTATION_TM},
            {VR_UC, false, "Unlimited Characters", VALUE_REPRESENTATION_UC},
            {VR_UI, true, "Unique Identifier (UID)", VALUE_REPRESENTATION_UI},
            {VR_UL, true, "Unsigned Long", VALUE_REPRESENTATION_UL},
            {VR_UN, false, "Unknown", VALUE_REPRESENTATION_UN},
            {VR_UR, false, "URI / URL", VALUE_REPRESENTATION_UR},
            {VR_US, true, "Unsigned Short", VALUE_REPRESENTATION_US},
            {VR_UT, false, "Unlimited Text", VALUE_REPRESENTATION_UT},
            {VR_UV, false, "Unsigned Very Long (64-bit)", VALUE_REPRESENTATION_UV},
            {VR_IMPLICIT, false, "Implicit VR", VALUE_REPRESENTATION_IMPLICIT}
    };


    // Checks if a value representation (VR) exists in the list of know VRs
    public static boolean isValidVR(byte[] vr) {
        if (vr.length != 2)
            return false;
        // Convert character array to int for easier comparison
        int vr_num = ByteUtils.intFrom16Bit(vr);
        // Compare if its present in VR Mappings
        for (Object[] VR : VR_MAPPINGS)
            if (vr_num == (int) (VR[0]))
                return true;

        return false;
    }


}


