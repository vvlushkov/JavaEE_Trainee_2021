import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import writerNIO.MyFileWriter;
import org.junit.Test;

public class MyFileWriterTest {
    public static final Logger LOG = LogManager
            .getLogger(MyFileWriterTest.class.getName());

    MyFileWriter myFileWriter = new MyFileWriter();

    @Test
    public void writeToFile() {
        String fileName = "C:/Users/Asus/Desktop/Studying/NIX SOLUTIONS STUDYING/srez/result.txt";
        String str = "sdsdsdd";
        myFileWriter.writeToFile(fileName, str);
    }
}