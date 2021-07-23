package readerIO;

import com.solution.lushkov.Main;
import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;

import java.io.BufferedReader;
import java.io.IOException;

public class FileReader {
    public static final Logger LOG = LogManager
            .getLogger(FileReader.class.getName());

    /**
     * This method read info from file and write it to the string using IO methods.
     *
     * @param fileName - path to file that need to read
     * @return - string with read info
     */
    public String reader(String fileName) {

        //FileInputStream fileInputStream = null;
        BufferedReader reader = null;
        StringBuilder stringBuilder = null;
        try {
            reader = new BufferedReader(new java.io.FileReader(fileName));
            stringBuilder = new StringBuilder();
            String line = null;
            while ((line = reader.readLine()) != null) {
                stringBuilder.append(line);
            }
        } catch (IOException e) {
            System.out.println(e.getMessage());
        } finally {
            try {
                reader.close();
            } catch (IOException e) {
                System.out.println(e.getMessage());
            }
        }
        return stringBuilder.toString();
    }
}
