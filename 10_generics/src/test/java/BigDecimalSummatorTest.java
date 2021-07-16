import org.junit.Test;

import summators.BigDecimalSummator;

import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class BigDecimalSummatorTest {

    private static final Logger LOG = LogManager
            .getLogger(BigDecimalSummatorTest.class.getName());

    BigDecimalSummator bdSum = new BigDecimalSummator();

    @Test
    public void sum() {
        Map<String, List<BigDecimal>> map = new HashMap<>();
        List<BigDecimal> list = new ArrayList<>();
        List<BigDecimal> list1 = new ArrayList<>();
        list.add(BigDecimal.valueOf(1.3f));
        list.add(BigDecimal.valueOf(2.3f));
        list.add(BigDecimal.valueOf(3.3f));
        list1.add(BigDecimal.valueOf(1.0f));
        list1.add(BigDecimal.valueOf(2.0f));
        list1.add(BigDecimal.valueOf(3.3f));
        map.put("key1", list);
        map.put("key2", list1);
        Map<String, Double> map1 = bdSum.sum(map);
        for (Map.Entry<String, Double> entry : map1.entrySet()) {
            LOG.info(entry.getKey() + " = " + entry.getValue());
        }
    }
}