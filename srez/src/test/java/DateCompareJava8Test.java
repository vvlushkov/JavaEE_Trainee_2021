import dateCompare.DateCompareJava8;
import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import org.junit.Test;

public class DateCompareJava8Test {
    public static final Logger LOG = LogManager
            .getLogger(DateCompareJava8Test.class.getName());

    DateCompareJava8 date8 = new DateCompareJava8();

    @Test
    public void countDays8Test() {
        int year = 1997;
        int month = 2;
        int daysToCheck = 29;
        LOG.info(date8.getDaysInMonthJava8(year, month, daysToCheck));
    }
}