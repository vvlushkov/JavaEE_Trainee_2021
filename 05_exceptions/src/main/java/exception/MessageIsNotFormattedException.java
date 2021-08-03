package exception;

/**
 * Class of the exception when input message is not formatted.
 *
 * @author Victor Lushkov
 * @version 1.0
 */
public class MessageIsNotFormattedException extends  Exception {

    /**
     * Constructor of exception that call constructor
     * of Exception class with {@code message}.
     *
     * @param   message
     *          The message
     */
    public MessageIsNotFormattedException(String message) {
        super(message);
    }
}
