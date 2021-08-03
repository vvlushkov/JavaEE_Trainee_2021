package saver;

import com.nixsolutions.ppp.exceptions.Saver;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

/**
 * Class to save string in file.
 *
 * @author Victor Lushkov
 * @version 1.0
 */
public class SaverImpl implements Saver {
    /**
     * Field which remember if exception is thrown.
     */
    public boolean thrownException;

    /**
     * Constructor that initializing field {@code thrownException}
     */
    public SaverImpl() {
        thrownException = false;
    }

    /**
     * A method for saving a string to a file.
     * If the file with the specified name does not exist, it is created;
     * otherwise, an exception is thrown.
     *
     * @param   text
     *          The string that need to write.
     * @param   fileAbsolutePath
     *          Absolute path to file in which will be written the string.
     */
    @Override
    public void save(String text, String fileAbsolutePath) {
        File file = new File(fileAbsolutePath);
        if (!file.exists()) {
            thrownException = true;
            return;
        }
        try (FileWriter fileWriter = new FileWriter(file)) {
            fileWriter.write(text);
        } catch (IOException e) {
            thrownException = true;
        }
    }
}
