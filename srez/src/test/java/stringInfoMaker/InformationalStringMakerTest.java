package stringInfoMaker;

import org.junit.Test;

public class InformationalStringMakerTest {

    @Test
    public void stringMaker() {
        InformationalStringMaker strOb = new InformationalStringMaker();
        Tester test = new Tester();
        try {
            System.out.println(strOb.infoStringAboutObj("Info txt:", test));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}