import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import org.junit.Test;
import stringParse.Finder;

import java.util.Arrays;

public class FinderTest {
    public static final Logger LOG = LogManager
            .getLogger(FinderTest.class.getName());

    Finder finder = new Finder();

    @Test
    public void dateFinderTest() {
        String booba = "nininin 1927 isfdjnj sds 29 February";
        int[] arr = finder.dateFinder(booba);
        LOG.info(Arrays.toString(arr));
    }

    @Test
    public void parseMonths() {
        String month = "Jan";
        LOG.info(finder.castMonths(month));
    }
}