import com.roundsToThree.DataProcessing.ByteUtils;
import com.roundsToThree.DataProcessing.StringUtils;
import com.roundsToThree.Representations.*;
import com.roundsToThree.sd4j;

import java.io.BufferedWriter;
import java.io.ByteArrayInputStream;
import java.io.File;
import java.lang.reflect.Array;
import java.nio.Buffer;
import java.nio.ByteBuffer;
import java.nio.ByteOrder;
import java.util.Arrays;


public class TestSD4J {
    public static void main(String[] args) {
        // Load the DICOM slice
        try {
//            Thread.sleep(2000);
            System.out.println(Runtime.getRuntime().totalMemory() - Runtime.getRuntime().freeMemory());
            long s = System.currentTimeMillis();
//            FileInputStream read = new FileInputStream(new File("000"));
//            sd4j sd = sd4j.loadSlice(new File("0003.DCM"));
//            sd4j sd = sd4j.loadSlice(new File("I516"));
            sd4j sd = sd4j.loadSlice(new File("000"));
            System.out.println(System.currentTimeMillis() - s);
//            System.out.println(Long.toBinaryString(-21213));
//            ByteBuffer bytes = ByteBuffer.allocate(4).order(ByteOrder.LITTLE_ENDIAN).putInt(-21213);

//            byte[] t = bytes.array();

//            byte[] t = {(int)0b11110000, (int)0b01111001, 0b1, 0, 0, 0, 0, 0};
//            byte[] t = { 0, 0, 0, 0, 0, (byte)0b11110000,  (byte)0b01111001, 0b1};
//            Representation r = new OtherLongRepresentation(t);
//            System.out.println("test");
//            System.out.println(r);
//            System.out.println(ByteUtils.longFrom64Bit(t));

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


//            Thread.sleep(2000);
            System.out.println(System.currentTimeMillis() - s);
            System.out.println(Runtime.getRuntime().totalMemory() - Runtime.getRuntime().freeMemory());

        } catch (Exception e) {
            e.printStackTrace();
        }


    }
}