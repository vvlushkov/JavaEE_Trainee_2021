package logger;

import com.nixsolutions.ppp.exceptions.OneMessageFileLogger;
import exception.MessageIsNotFormattedException;
import saver.SaverImpl;

import java.io.IOException;

/**
 * Class for logging message to file.
 *
 * @author Victor Lushkov
 * @version 1.0
 */
public class OneMessageFileLoggerImpl implements  OneMessageFileLogger {
    /** SaverImpl field. */
    SaverImpl saver;

    /** String path field. */
    String fileAbsolutePath;

    /**
     * Empty constructor with no params
     */
    public OneMessageFileLoggerImpl() { }

    /**
     * Constructor
     *
     * @param   newFileAbsolutePath
     *          the path to file
     * @param   newSaver
     *          SaverImpl object
     */
    public OneMessageFileLoggerImpl(String newFileAbsolutePath, SaverImpl newSaver) {
        saver = newSaver;
        fileAbsolutePath = newFileAbsolutePath;
    }

    /**
     * Method which write received message to the new file.
     *
     * @param   message
     *          Message which will be logging
     *          (must start with {@value #MESSAGE_STARTS_WITH}).
     * @throws  IOException
     *          If an error occurs while writing a message to the file
     *          or the file already exists.
     * @throws  MessageIsNotFormattedException
     *          If message don`t start with {@value #MESSAGE_STARTS_WITH}
     */
    @Override
    public void log(String message) throws Exception {
        if (!message.startsWith(MESSAGE_STARTS_WITH)) {
            throw new MessageIsNotFormattedException(message);
        }
        saver.save(message, fileAbsolutePath);
        if (saver.thrownException) {
            throw new IOException();
        }
    }
}
