package stringInfoMaker;

import org.junit.Test;
import static org.junit.Assert.*;

public class StringMakerFromObjectTest {

    @Test
    public void stringMaker() {
        StringMakerFromObject strOb = new StringMakerFromObject();
        Tester test = new Tester();
        try {
            System.out.println(strOb.stringMaker("Info txt:", test));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}