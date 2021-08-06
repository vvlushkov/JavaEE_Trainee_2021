package writerNIO;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Arrays;
import java.util.List;

/**
 * Class with methods to to write info into file
 * using only java.nio technologies.
 *
 * @author Victor Lushkov
 * @version 1.0
 */
public class MyFileWriter {

    /**
     * This method write info into file using NIO methods.
     *
     * @param   fileName
     *          name of file with full path.
     * @param   str
     *          string that need to write into file.
     */
    public void writeToFile(String fileName, String str) {
        List<String> lines = Arrays.asList(str);
        Path file = null;
        try {
            file = Paths.get(fileName);
            Files.write(file, lines);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
