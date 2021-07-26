package readerIO;

import java.io.BufferedReader;
import java.io.IOException;

/**
 * Class with methods to read info from file using only java.io technologies.
 *
 * @author Victor Lushkov
 * @version 1.0
 */
public class MyFileReader {

    /**
     * This method read info from file
     * and write it to the string using IO methods.
     *
     * @param   fileName
     *          path to file that need to read
     * @return  string with read info
     */
    public String reader(String fileName) {
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