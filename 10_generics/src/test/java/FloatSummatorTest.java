import org.junit.Test;

import summators.FloatSummator;

import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class FloatSummatorTest {

    private static final Logger LOG = LogManager
            .getLogger(FloatSummatorTest.class.getName());

    FloatSummator flSum = new FloatSummator();

    @Test
    public void sum() {
        Map<String, List<Float>> map = new HashMap<>();
        List<Float> list = new ArrayList<>();
        List<Float> list1 = new ArrayList<>();
        list.add(1.3f);
        list.add(2.3f);
        list.add(3.3f);
        list1.add(1.0f);
        list1.add(2.0f);
        list1.add(3.3f);
        map.put("key1", list);
        map.put("key2", list1);
        Map<String, Double> map1 = flSum.sum(map);
        for (Map.Entry<String, Double> entry : map1.entrySet()) {
            LOG.info(entry.getKey() + " = " + entry.getValue());
        }
    }
}