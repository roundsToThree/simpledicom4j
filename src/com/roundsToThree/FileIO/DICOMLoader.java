package com.roundsToThree.FileIO;

import com.roundsToThree.DataProcessing.ByteUtils;
import com.roundsToThree.Exception.CorruptSequenceException;
import com.roundsToThree.Exception.InvalidFileException;
import com.roundsToThree.Representations.*;
import com.roundsToThree.Structures.FileArchitecture;
import com.roundsToThree.Structures.ValueRepresentation;
import com.roundsToThree.sd4j;

import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.Arrays;
import java.util.HashMap;

public class DICOMLoader {

    public static void loadDICOMSliceFromFile(sd4j sd, File dicomFile) throws InvalidFileException, IOException {
        // Open the file
        FileInputStream read = new FileInputStream(dicomFile);
        BufferedInputStream buffered_reader = new BufferedInputStream(read);

        // Read the first 132 bytes to ensure it is a valid DICOM file
        try {
            if (!FileArchitecture.containsDICOMHeader(buffered_reader.readNBytes(132)))
                throw new InvalidFileException("File is either corrupt or not a DICOM file", null);
        } catch (Exception e) {
            throw new InvalidFileException("File is not a DICOM file", e);
        }

        // Create the hashmap
        sd.elements = new HashMap<>();
        // Load in the contents of the file
        processTags(sd.elements, buffered_reader, -1);
    }


    // Read tags given the ItemElement to nest them in, the binary reader and also how much to read
    // if length is -1, then keep reading until EOF, otherwise read up to length
    // returns how many bytes were read
    private static long processTags(HashMap<Integer, Representation> parent, BufferedInputStream reader, long length) {

        long bytesRead = 0;

        try {
            while (
                // Defined length
                    (length != -1 && bytesRead < length) ||
                            // Undefined length
                            (length == -1 && reader.available() > 0)
            ) {

                // Allocate an array to store the contents of the item tag in
                byte[] value;

                // Get the group number
                byte[] data = reader.readNBytes(2);
                bytesRead += 2;
                int groupNumber = ByteUtils.intFrom16Bit(data);

                // Get the element number
                data = reader.readNBytes(2);
                int elementNumber = ByteUtils.intFrom16Bit(data);


                // If reading has reached the end of a sequence item, this will break out of it
                if (
                        (groupNumber == 0xfffe && elementNumber == 0xe00d) ||
                                // Or if there is a weird exception with both tags = 0
                                (groupNumber == 0 && elementNumber == 0)
                )
                    return bytesRead;
                // Create a new tag to fill
//                DataElement tag = new DataElement(groupNumber, elementNumber);

            /*
            Next 4 bytes are either
             VR Only       (2b VR) (2b = 00 00)
             VR + Length   (2b VR) (2b uint16)
             Length only   (4b uint32)
             */
                byte[] tagStructure = reader.readNBytes(4);
                bytesRead += 4;

                int tagType = FileArchitecture.getTagType(tagStructure);
//                tag.valueRepresentation = ValueRepresentation.getRepresentationFromBytes(Arrays.copyOf(tagStructure, 2));
                ValueRepresentation valueRepresentation = ValueRepresentation.getRepresentationFromBytes(Arrays.copyOf(tagStructure, 2));
                int dataLength = -1;

                switch (tagType) {
                    case FileArchitecture.VR_ONLY -> {
                        // Next 4 bytes are then just the length
                        dataLength = (int) ByteUtils.longFrom32Bit(reader.readNBytes(4));
                        bytesRead += 4;

                    }
                    case FileArchitecture.VR_AND_LENGTH -> {
                        // Next 2 bytes are then just the length
                        dataLength = ByteUtils.intFrom16Bit(Arrays.copyOfRange(tagStructure, 2, 4));
                    }
                    case FileArchitecture.LENGTH_ONLY -> {
                        // tagStructure is implicit, should be handled when converting the value of the tag.
                        // Note here that dataLength should be typecast to long when used
                        valueRepresentation = ValueRepresentation.VALUE_REPRESENTATION_IMPLICIT;
                        dataLength = (int) ByteUtils.longFrom32Bit(tagStructure);
                    }
                }

                // If the data is a sequence, it must be treated differently
                if (valueRepresentation == ValueRepresentation.VALUE_REPRESENTATION_SQ) {
                    System.out.println("Decoding Sequence");
                    SequenceRepresentation sequence = new SequenceRepresentation();
                    bytesRead += processSequence(reader, sequence.elements, dataLength);

                    // Skip the normal route and directly continue the loop
                    parent.put((int) ((groupNumber << 16) | (elementNumber & 0xFFFF)), sequence);
                    continue;
                } else {
                    // Otherwise its a normal data tag so simply store the data

                    // Determine if length is explicitly defined
                    if (
                            dataLength == -1 &&
                                    (tagType == FileArchitecture.VR_ONLY || tagType == FileArchitecture.LENGTH_ONLY)
                    ) {
                        // Undefined length
                        // Read data until the end of the tag
                        // End of an undefined length tag is marked by the sequence
                        // 0x FEFF DDE0 0000 0000 (SEQUENCE_DELIMINATOR)
                        // Also convert the read data into ItemElements
                        value = ByteUtils.getBytesUntilDeliminator(reader, FileArchitecture.SEQUENCE_DELIMINATOR);
                        bytesRead += value.length + FileArchitecture.SEQUENCE_DELIMINATOR.length;
                    } else {
                        // Assume a determined length
                        // Convert the binary data into ItemElements
                        value = reader.readNBytes(dataLength);
                        bytesRead += dataLength;
                    }
                }


                // Compile tag and put in hashmap

                Representation tag = createRepresentationFromTag(valueRepresentation, value);

//                System.out.println(String.format("(%04X,%04X) Type: %s", groupNumber, elementNumber, valueRepresentation.toDetailedString()));


                // Push it to the hashmap (index is the groupNo. elemnetNo.)
                int ind = (int) ((groupNumber << 16) | (elementNumber & 0xFFFF));
                parent.put(ind, tag);

            }


        } catch (IOException e) {
            // todo: handle this exception from buffered reader
        }

        return bytesRead;
    }

    // Converts a value representation and contents of a tag into a formatted Representations.* class
    private static Representation createRepresentationFromTag(ValueRepresentation valueRepresentation, byte[] value) {
        switch (valueRepresentation.VRCode) {
            case ValueRepresentation.VR_AE -> {
                return new ApplicationEntityRepresentation(value);
            }
            case ValueRepresentation.VR_AS -> {
                return new AgeStringRepresentation(value);
            }
            case ValueRepresentation.VR_AT -> {
                return new AttributeTagRepresentation(value);
            }
            case ValueRepresentation.VR_CS -> {
                return new CodeStringRepresentation(value);
            }
            case ValueRepresentation.VR_DA -> {
                return new DateRepresentation(value);
            }
            case ValueRepresentation.VR_DS -> {
                return new DecimalStringRepresentation(value);
            }
            case ValueRepresentation.VR_DT -> {
                return new DateTimeRepresentation(value);
            }
            case ValueRepresentation.VR_FL -> {
                return new FloatSingleRepresentation(value);
            }
            case ValueRepresentation.VR_FD -> {
                return new FloatDoubleRepresentation(value);
            }
            case ValueRepresentation.VR_IS -> {
                return new IntegerStringRepresentation(value);
            }
            case ValueRepresentation.VR_LO -> {
                return new LongStringRepresentation(value);
            }
            case ValueRepresentation.VR_LT -> {
                return new LongTextRepresentation(value);
            }
            case ValueRepresentation.VR_OB -> {
                return new OtherByteRepresentation(value);
            }
            case ValueRepresentation.VR_OD -> {
                return new OtherDoubleRepresentation(value);
            }
            case ValueRepresentation.VR_OF -> {
                return new OtherFloatRepresentation(value);
            }
            case ValueRepresentation.VR_OL -> {
                return new OtherLongRepresentation(value);
            }
            case ValueRepresentation.VR_OV -> {
                return new OtherVeryLongRepresentation(value);
            }
            case ValueRepresentation.VR_OW -> {
                return new OtherWordRepresentation(value);
            }
            case ValueRepresentation.VR_PN -> {
                return new PersonNameRepresentation(value);
            }
            case ValueRepresentation.VR_SS -> {
                return new ShortStringRepresentation(value);
            }


            case ValueRepresentation.VR_TM -> {
                return new TimeRepresentation(value);
            }

            default -> {
                // No Value Representation implemented for this VR type yet
                Representation r = new Representation();
                r.value = value;
                return r;
            }
        }
    }

    // Used when recursing to process a SequenceRepresentation into its HashMap straight from the file stream
    private static long processSequence(BufferedInputStream reader, HashMap<Integer, Representation> elements, int dataLength) {
        long bytesRead = 0;
        try {
            while (
                    (dataLength == -1 && reader.available() > 0) ||
                            (dataLength != -1 && bytesRead < dataLength)
            ) {

                // Check if the start of the item is actually the end of a SEQUENCE
                byte[] itemHeader = reader.readNBytes(4);
                bytesRead += 4;

                if (Arrays.equals(itemHeader, FileArchitecture.SEQUENCE_DELIMINATOR)) {
                    // Clear remaining 4 bytes
                    reader.skipNBytes(4);
                    bytesRead += 4;
                    return bytesRead;
                }
                // Read the item header
                if (!Arrays.equals(itemHeader, FileArchitecture.SEQUENCE_ITEM_TAG))
                    throw new CorruptSequenceException(String.format("DICOM Sequence Tag does not contain an item header at index %d: %s", bytesRead, ByteUtils.byteArrayToHexString(itemHeader)), null);

                // Read the length of the item
                long length = ByteUtils.longFrom32Bit(reader.readNBytes(4));
                bytesRead += 4;
                if ((int) length == -1)
                    length = -1;

                bytesRead += processTags(elements, reader, length);

                // Read in the end of the item
                byte[] itemDelimiator = reader.readNBytes(4);
                // Since processTags terminates after reading the sequence item end, we expect the next 4 bytes to be 0s to signal the end of the item
                if (ByteUtils.longFrom32Bit(itemDelimiator) != 0)
                    throw new CorruptSequenceException(String.format("DICOM Sequence Tag does not terminate correctly at index %d: %s", bytesRead, ByteUtils.byteArrayToHexString(itemDelimiator)), null);
                bytesRead += 4;
            }
        } catch (IOException e) {
            // todo: handle exception
        }
        return bytesRead;
    }
}
