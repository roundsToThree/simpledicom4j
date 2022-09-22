package com.roundsToThree.Structures;

import com.roundsToThree.DataProcessing.ByteUtils;

public class FileArchitecture {
    // Contains constants used to describe packet delimiters
    // Also contians relevant tools


    // What a tag of undefined length is terminated with
    public static final byte[] SEQUENCE_DELIMINATOR = {(byte) 0xFE, (byte) 0xFF, (byte) 0xDD, (byte) 0xE0};
    // What an item of undefined length in a tag os terminated with
    public static final byte[] SEQUENCE_ITEM_DELIMINATOR = {(byte) 0xFE, (byte) 0xFF, (byte) 0x0D, (byte) 0xE0};

    // At the start of an item in a sequence (before the item's length)
    public static final byte[] SEQUENCE_ITEM_TAG = {(byte) 0xFE, (byte) 0xFF, (byte) 0x00, (byte) 0xE0};


    // Given the first 132 bytes of a file, determines whether it is actually a  DICOM file
    public static boolean containsDICOMHeader(byte[] data) {
      /*
      DICOM begins with 128 arbitrary bytes then "DICM"
       */
        if (data.length < 132)
            return false;

        // Check DICM
        return
                data[128] == 'D' &&
                        data[129] == 'I' &&
                        data[130] == 'C' &&
                        data[131] == 'M';
    }


    // The three types of tag formats used in encoding DICOMs
    public static final short VR_ONLY = 0;
    public static final short VR_AND_LENGTH = 1;
    public static final short LENGTH_ONLY = 2;


    // Returns the structure type of a tag
    public static int getTagType(byte[] data) {
  /*
   VR Only       (2b VR) (2b = 00 00)
   VR + Length   (2b VR) (2b uint16)
   Length only   (4b uint32)
   */

        // Get the first two bytes
        byte[] leadingBytes = new byte[2];
        System.arraycopy(data, 0, leadingBytes, 0, 2);
        int leadingVR = ByteUtils.intFrom16Bit(leadingBytes);

        // Check if the first two bytes are a known VR type
        if (ValueRepresentation.isValidVR(leadingBytes)) {
            // Check if the VR is of type
            // AE, AS, AT, CS, DA, DS, DT, FL, FD, IS, LO, LT,
            // PN, SH, SL, SS, ST, TM, UI, UL and US
            // These types use the VR_AND_LENGTH type.
            // See VR_MAPPINGS (specifically the middle boolean)
            for (Object[] VR : ValueRepresentation.VR_MAPPINGS)
                if (leadingVR == (int) (VR[0]) && (boolean) VR[1])
                    return VR_AND_LENGTH;

            // Otherwise it is still an explicit VR, see if its of structure 1
            if (data[2] == 0 && data[3] == 0)
                return VR_ONLY;

            // +++ Unexpected edge case if not returned yet +++
            // If the 32bit uint for structure 3 happened to be similar to a VR,
            // but didnt break in the above two checks, then we just assume LENGTH_ONLY
        }

        // Hence we can assume the only other combination might be LENGTH_ONLY
        return LENGTH_ONLY;
    }
}
