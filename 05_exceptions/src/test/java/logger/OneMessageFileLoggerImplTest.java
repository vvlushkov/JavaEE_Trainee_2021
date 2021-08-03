package logger;

import org.junit.Test;
import saver.SaverImpl;

import java.io.IOException;

import static org.junit.Assert.*;

public class OneMessageFileLoggerImplTest {

    @Test
    public void log() {
        String file = "C:/Users/Asus/Desktop/rte.txt";
        String text = "MESSAGE: lalala ";

        SaverImpl saver = new SaverImpl();
        saver.save(text, file);

        OneMessageFileLoggerImpl oneMes = new OneMessageFileLoggerImpl(file, saver);
        try {
            oneMes.log(text);
        } catch (Exception e) {
            e.printStackTrace();
        }

    }
}