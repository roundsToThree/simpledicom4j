package com.roundsToThree;

import com.roundsToThree.Exception.InvalidFileException;
import com.roundsToThree.FileIO.DICOMLoader;
import com.roundsToThree.FileIO.DICOMLoader2;
import com.roundsToThree.Representations.DateTimeRepresentation;
import com.roundsToThree.Representations.PersonRepresentation;
import com.roundsToThree.Representations.Representation;
import com.roundsToThree.Structures.DataElement;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;

public class sd4j {
    public HashMap<Integer, DataElement> elements;
    public HashMap<Integer, Representation> elements2;

    private sd4j() {
    }


    // Instance method
    // Creates an sd4j class from a DICOM slice file
    public static sd4j loadSlice(File sliceFile) {
        sd4j simpledicom = new sd4j();

        // Read the file
        try {
            DICOMLoader2.loadDICOMSliceFromFile(simpledicom, sliceFile);
        } catch (IOException e) {
            System.out.print("Failed to load DICOM file " + sliceFile.getAbsolutePath() + ", IO Exception: ");
            System.out.println(e.getMessage());
            e.printStackTrace();
        } catch (InvalidFileException e) {
            System.out.println("File is not a valid DICOM file!");
            e.printStackTrace();
        }

        return simpledicom;
    }

    // Return the elements given a group number
    public DataElement[] getElementsByGroupNumber(int groupNumber) {
        return null;
    }

    // Returns an element given an group and element number
    public DataElement getElementByTagNumber(int groupNumber, int elementNumber) {
        int ind = (int) ((groupNumber << 16) | (elementNumber & 0xFFFF));
        return elements.get(ind);
    }

    public PersonRepresentation getPatientName() {
        DataElement patientName = getElementByTagNumber(0x0010, 0x0010);
        if (patientName == null || patientName.items == null || patientName.items.size() == 0)
            return null;

        return new PersonRepresentation(patientName.items.get(0).value);
    }


    public DateTimeRepresentation getAquisitionDate() {
        return null;
    }

    public DateTimeRepresentation getAquisitionTime() {
        return null;
    }

    /*

        Gets the date and time the image acquisition started

    (0008,002A)
     */
    public DateTimeRepresentation getAquisitionDateTime() {
        return null;
    }

}
