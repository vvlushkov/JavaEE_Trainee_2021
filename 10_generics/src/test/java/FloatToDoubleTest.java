import converters.FloatToDouble;
import org.junit.Test;

import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;

public class FloatToDoubleTest {

    private static final Logger LOG = LogManager
            .getLogger(FloatToDoubleTest.class.getName());

    FloatToDouble floatToDouble = new FloatToDouble();

    @Test
    public void get() {
        Float num = 23.999f;
        LOG.info(floatToDouble.get(num));
    }
}