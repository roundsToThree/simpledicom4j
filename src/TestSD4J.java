import com.roundsToThree.Representations.AgeStringRepresentation;
import com.roundsToThree.Representations.SequenceRepresentation;
import com.roundsToThree.sd4j;

import java.io.File;


public class TestSD4J {
    public static void main(String[] args) {

        // Load the DICOM slice
        try {
//            FileInputStream read = new FileInputStream(new File("000"));
            sd4j sd = sd4j.loadSlice(new File("0003.DCM"));
//            System.out.println(sd.getPatientName());
//            System.out.println(new DateTimeRepresentation(sd.getElementByTagNumber(0x0008, 0x002a).items.get(0).value));
//            byte[] test = {'1','9','5','3','0','8','2','7','1','1','1','3','0','0','.','1','7','3','8','4','2'};
//            System.out.println(new DateTimeRepresentation(test));
//            ArrayList<ItemElement> elm = sd.getElementByTagNumber(0x0008,0x2112).items;
//
//            ArrayList<ItemElement> elm = sd.getElementByTagNumber(0x0028, 0x6100).items;
//            byte[] bb = elm.get(0).value;
//            for (int i = 0; i < bb.length; i += 4) {
//                System.out.format("%x%x%x%x %c%c%c%c\n", bb[i], bb[i + 1], bb[i + 2], bb[i + 3], bb[i], bb[i + 1], bb[i + 2], bb[i + 3]);
//            }
            SequenceRepresentation sq = new SequenceRepresentation();
            sq.elements = sd.elements;
            System.out.println(sq);
            
        } catch (Exception e) {
            e.printStackTrace();
        }


    }
}