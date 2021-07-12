import org.junit.Test;

import converters.IntArrayToString;
import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;

public class IntArrayToStringTest {

    private static final Logger LOG = LogManager
            .getLogger(IntArrayToStringTest.class.getName());

    IntArrayToString intArrToStr = new IntArrayToString();

    @Test
    public void get() {
        Integer[] array = new Integer[] {1, 2, 3, 4};

        LOG.info(intArrToStr.get(array));
    }
}