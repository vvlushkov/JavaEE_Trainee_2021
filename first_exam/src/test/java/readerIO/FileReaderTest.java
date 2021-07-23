package readerIO;

import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import org.junit.Test;

import static org.junit.Assert.*;

public class FileReaderTest {
    FileReader fileReader = new FileReader();

//    public static final Logger LOG = LogManager
//            .getLogger(FileReaderTest.class.getName());

    @Test
    public void readerTest() {
        String filePath = "/Users/Asus/Desktop/Studying/NIX SOLUTIONS STUDYING/srez/srez.txt";
        //LOG.info(fileReader.reader(filePath));
        System.out.println(fileReader.reader(filePath));
    }
}