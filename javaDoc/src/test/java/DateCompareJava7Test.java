import dateCompare.DateCompareJava7;
import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import org.junit.Test;

public class DateCompareJava7Test {
    public static final Logger LOG = LogManager
            .getLogger(DateCompareJava7Test.class.getName());

    DateCompareJava7 date7 = new DateCompareJava7();

    @Test
    public void countDays7Test() {
        int[] arr = {1997, 2, 27};
        int year = 1997;
        int month = 2;
        int daysToCheck = 27;
        //date7.countDays7(arr);
        LOG.info(date7.getDaysInMonthJava7(year, month, daysToCheck));
    }
}