package serializableBean;

import com.nixsolutions.ppp.serializable.SerializableBean;
import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import java.io.IOException;
import java.io.ObjectInput;
import java.io.ObjectOutput;

/**
 * Class that implements bean {@code SerializableBean}.
 *
 * <p> All fields of the object are concatenated into a string like
 * "name;email;zip", and then the string is serialized into a stream.
 *
 * @author Victor Lushkov
 * @version 1.0
 * @see SerializableBean
 */
public class SerializableBeanImpl implements SerializableBean {
    /**
     * Field to use Logging.
     */
    public static final Logger LOG = LogManager
            .getLogger(SerializableBeanImpl.class.getName());

    /**
     * Default constructor that assigns base values
     * to fields {@code name, email, zip and concatenatedFields}.
     */
    public SerializableBeanImpl() {
        name = "constName";
        email = "constEmail";
        zip = 123;
        concatFields();
    }

    /** Field of name. */
    private String name;
    /** Field of email. */
    private String email;
    /** Field of zip. */
    private int zip;
    /** Field of concatenated fields. */
    private String concatenatedFields;

    /**
     * Method to get value of field {@code name}.
     *
     * @return  value of field {@code name}.
     */
    @Override
    public String getName() {
        return name;
    }

    /**
     * Method to set value of field {@code name}.
     */
    @Override
    public void setName(String name) {
        this.name = name;
    }

    /**
     * Method to get value of field {@code email}.
     *
     * @return  value of field {@code email}.
     */
    @Override
    public String getEmail() {
        return email;
    }

    /**
     * Method to set value of field {@code email}.
     */
    @Override
    public void setEmail(String email) {
        this.email = email;
    }

    /**
     * Method to get value of field {@code zip}.
     *
     * @return  value of field {@code zip}.
     */
    @Override
    public int getZip() {
        return zip;
    }

    /**
     * Method to set value of field {@code zip}.
     */
    @Override
    public void setZip(int zip) {
        this.zip = zip;
    }

    /**
     * Method to get value of field {@code concatenatedFields}.
     *
     * @return  value of field {@code concatenatedFields}.
     */
    public String getConcatenatedFields() {
        return concatenatedFields;
    }

    /**
     * Method to set value of field {@code concatenatedFields}.
     */
    public void setConcatenatedFields(String concatenatedFields) {
        this.concatenatedFields = concatenatedFields;
    }

    /**
     * Method to concat fields {@code name, email and zip}.
     */
    private void concatFields() {
        concatenatedFields = getName() + ";" +
                getEmail() + ";" +
                getZip();
    }

    /**
     * Method to serialize string {@code concatenatedFields}.
     *
     * @param   out
     *          the stream to write the object to.
     * @throws  IOException
     *          Includes any I/O exceptions that may occur.
     */
    @Override
    public void writeExternal(ObjectOutput out) throws IOException {
        out.writeObject(concatenatedFields);
    }

    /**
     * Method to deserialize data from {@code ObjectInput in} and write written
     * values into fields {@code name, email and zip}.
     *
     * @param   in
     *          the stream to read data from in order to restore the object.
     * @throws  IOException
     *          if I/O errors occur.
     * @throws  ClassNotFoundException
     *          If the class for an object being restored cannot be found.
     */
    @Override
    public void readExternal(ObjectInput in) throws IOException, ClassNotFoundException {
        String[] arr = ((String)in.readObject()).split(";");
        setName(arr[0]);
        setEmail(arr[1]);
        setZip(Integer.parseInt(arr[2]));
    }
}
