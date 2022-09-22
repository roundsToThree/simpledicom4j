package com.roundsToThree.FileIO;

import com.roundsToThree.DataProcessing.ByteUtils;
import com.roundsToThree.Exception.InvalidFileException;
import com.roundsToThree.Structures.DataElement;
import com.roundsToThree.Structures.ItemElement;
import com.roundsToThree.Structures.ValueRepresentation;
import com.roundsToThree.sd4j;

import java.io.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;


public class DICOMLoader {

    public static void loadDICOMSliceFromFile(sd4j sd, File dicomFile) throws InvalidFileException, IOException {
        // Open the file
        FileInputStream read = new FileInputStream(dicomFile);
        BufferedInputStream buffered_reader = new BufferedInputStream(read);

        // Read the first 132 bytes to ensure it is a valid DICOM file
        try {
            if (!containsDICOMHeader(buffered_reader.readNBytes(132)))
                throw new InvalidFileException("File is either corrupt or not a DICOM file", null);
        } catch (Exception e) {
            throw new InvalidFileException("File is not a DICOM file", e);
        }

        // Read rest of file
        HashMap<Integer, DataElement> tags = new HashMap<>();
        // Start from byte 132
        while (buffered_reader.available() > 0) {

            // Get the group number
            byte[] data = buffered_reader.readNBytes(2);
            int groupNumber = ByteUtils.intFrom16Bit(data);
            // Get the element number
            data = buffered_reader.readNBytes(2);
            int elementNumber = ByteUtils.intFrom16Bit(data);

            // Create a new tag to fill
            DataElement tag = new DataElement(groupNumber, elementNumber);

            /*
            Next 4 bytes are either
             VR Only       (2b VR) (2b = 00 00)
             VR + Length   (2b VR) (2b uint16)
             Length only   (4b uint32)
             */
            byte[] tagStructure = buffered_reader.readNBytes(4);
            int tagType = getTagType(tagStructure);
            switch (tagType) {
                case VR_ONLY -> {
//                    System.out.println("Tag Type: VR_ONLY");
                    tag.valueRepresentation = ValueRepresentation.getRepresentationFromBytes(Arrays.copyOf(tagStructure, 2));

                    // Next 4 bytes are then just the length
                    tag.dataLength = (int) ByteUtils.longFrom32Bit(buffered_reader.readNBytes(4));
                }
                case VR_AND_LENGTH -> {
//                    System.out.println("Tag Type: VR_AND_LENGTH");
                    tag.valueRepresentation = ValueRepresentation.getRepresentationFromBytes(Arrays.copyOf(tagStructure, 2));
                    // Next 2 bytes are then just the length
                    tag.dataLength = ByteUtils.intFrom16Bit(Arrays.copyOfRange(tagStructure, 2, 4));
                }
                case LENGTH_ONLY -> {
//                    System.out.println("Tag Type: LENGTH_ONLY");

                    // tagStructure is implicit, should be handled when converting the value of the tag.
                    // Note here that dataLength should be typecast to long when used
                    tag.dataLength = (int) ByteUtils.longFrom32Bit(tagStructure);
                }
            }

            //println(str(char((int)tag.valueRepresentation[0])) + str(char((int)tag.valueRepresentation[1])));
            byte[] value;
            // Determine if length is explicitly defined
            if (tag.dataLength == -1 && (tagType == VR_ONLY || tagType == LENGTH_ONLY)) {
                // Undefined length
                // Read data until the end of the tag
                // End of an undefined length tag is marked by the sequence
                // 0x FEFF DDE0 0000 0000 (SEQUENCE_DELIMINATOR)
                // Also convert the read data into ItemElements
                tag.items = ItemElement.itemElementsFromDataElement(tag.valueRepresentation, getBytesUntilDeliminator(buffered_reader, SEQUENCE_DELIMINATOR));
            } else {
                // Assume a determined length
                // Convert the binary data into ItemElements
                tag.items = ItemElement.itemElementsFromDataElement(tag.valueRepresentation, buffered_reader.readNBytes(tag.dataLength));
            }
            System.out.println(tag.getSummary());

            // Push it to the hashmap (index is the groupNo. elemnetNo.)
            int ind = (int) ((groupNumber << 16) | (elementNumber & 0xFFFF));
            tags.put(ind, tag);
        }

        sd.elements = tags;
        buffered_reader.close();
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

    // Given the first 132 bytes of a file, determines whether it is actually a  DICOM file
    static boolean containsDICOMHeader(byte[] data) {
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


    // Value representations specific to the second structure of the DICOM tag specification
// See dicom.nema.org => PS3.5, Table 7.1-2
    static final byte[][] structureTwoExplicitVRs = {
            {'A', 'E'},
            {'A', 'S'},
            {'A', 'T'},
            {'C', 'S'},
            {'D', 'A'},
            {'D', 'S'},
            {'D', 'T'},
            {'F', 'L'},
            {'F', 'D'},
            {'I', 'S'},
            {'L', 'O'},
            {'L', 'T'},
            {'P', 'N'},
            {'S', 'H'},
            {'S', 'L'},
            {'S', 'S'},
            {'S', 'T'},
            {'T', 'M'},
            {'U', 'I'},
            {'U', 'L'},
            {'U', 'S'}
    };

    // What a tag of undefined length is terminated with
    static final byte[] SEQUENCE_DELIMINATOR = {(byte) 0xFE, (byte) 0xFF, (byte) 0xDD, (byte) 0xE0, 0x00, 0x00, 0x00, 0x00};
    // What an item of undefined length in a tag os terminated with
    public static final byte[] ITEM_DELIMINATOR = {(byte) 0xFE, (byte) 0xFF, (byte) 0x0D, (byte) 0xE0, 0x00, 0x00, 0x00, 0x00};


    // The three types of tag formats
    static final short VR_ONLY = 0;
    static final short VR_AND_LENGTH = 1;
    static final short LENGTH_ONLY = 2;

    // Returns the structure type of a tag
    static int getTagType(byte[] data) {
  /*
   VR Only       (2b VR) (2b = 00 00)
   VR + Length   (2b VR) (2b uint16)
   Length only   (4b uint32)
   */

        // Get the first two bytes
        byte[] leadingBytes = new byte[2];
        System.arraycopy(data, 0, leadingBytes, 0, 2);

        // Check if the first two bytes are a known VR type
        if (isValidVR(leadingBytes)) {
            // Check if the VR is of type
            // AE, AS, AT, CS, DA, DS, DT, FL, FD, IS, LO, LT,
            // PN, SH, SL, SS, ST, TM, UI, UL and US
            // These types use the VR_AND_LENGTH type.
            for (byte[] structureTwoExplicitVR : structureTwoExplicitVRs)
                if (Arrays.equals(leadingBytes, structureTwoExplicitVR))
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

    // Checks if a value representation (VR) exists in the list of know VRs
    static boolean isValidVR(byte[] vr) {
        if (vr.length != 2)
            return false;

//        for (byte[] valueRepresentation : ValueRepresentation.valueRepresentations)
//            if (Arrays.equals(vr, valueRepresentation))
//                return true;

        return false;
    }


}
