package saver;

import org.junit.Test;

public class SaverImplTest {

    @Test
    public void save() {
        String file = "C:/Users/Asus/Desktop";
        String text = "MESSAGE:lalala ";

        SaverImpl saver = new SaverImpl();
        saver.save(text, file);
    }
}