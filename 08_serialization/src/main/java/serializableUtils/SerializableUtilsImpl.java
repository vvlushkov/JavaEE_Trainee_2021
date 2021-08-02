package serializableUtils;

import com.nixsolutions.ppp.serializable.SerializableUtils;
import java.io.IOException;
import java.io.InputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.OutputStream;
import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;

/**
 * Class with utilities to serialize objects.
 *
 * @author Victor Lushkov
 * @version 1.0
 */
public class SerializableUtilsImpl implements SerializableUtils {
    /**
     * Field to use Logging.
     */
    public static final Logger LOG = LogManager
            .getLogger(SerializableUtilsImpl.class.getName());

    /**
     * Serializing the object
     *
     * @param   out
     *          the stream to which need write the object.
     * @param   obj
     *          serializing object.
     * @throws  NullPointerException
     *          if out or obj == null.
     * @throws  RuntimeException
     *          in case of various errors.
     */
    @Override
    public void serialize(OutputStream out, Object obj) {
        try {
            if (out == null || obj == null) {
                throw new NullPointerException();
            }
            try (ObjectOutputStream objOut = new ObjectOutputStream(out)) {
                objOut.writeObject(obj);
            } catch (IOException e) {
                LOG.info(e.getMessage());
            }
        } catch (RuntimeException e) {
            LOG.info(e.getMessage());
        }
    }

    /**
     * Deserializing the object.
     *
     * @param   in
     *          the stream from which need to read and deserialize the object.
     * @param   clazz
     *          the class of the object that need to read and deserialize.
     * @return  deserialized object.
     * @throws  NullPointerException
     *          if in == null.
     * @throws  RuntimeException
     *          in case of various errors.
     */
    @Override
    public <T> T deserialize(InputStream in, Class<T> clazz) {
        Object obj = new Object();
        try {
            //clazz.cast(obj);
            if (in == null) {
                throw new NullPointerException();
            }
            try (ObjectInputStream objOut = new ObjectInputStream(in)) {
                obj = objOut.readObject();
            } catch (IOException e) {
                LOG.info(e.getMessage());
            } catch (ClassNotFoundException e) {
                e.printStackTrace();
            }
        } catch (RuntimeException e) {
            LOG.info(e.getMessage());
        }
        return (T) obj;
    }
}