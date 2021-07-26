import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import org.junit.Test;
import readerIO.MyFileReader;

public class MyFileReaderTest {
    public static final Logger LOG = LogManager
            .getLogger(MyFileReaderTest.class.getName());

    MyFileReader myFileReader = new MyFileReader();

    @Test
    public void readerTest() {
        String filePath = "/Users/Asus/Desktop/Studying/NIX SOLUTIONS STUDYING/srez/srez.txt";
        LOG.info(myFileReader.reader(filePath));
    }
}