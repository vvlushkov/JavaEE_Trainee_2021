package dateCompare;

import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import java.time.YearMonth;

/**
 * Class with methods to compare dates using Java8+
 *
 * @author Victor Lushkov
 * @version 1.0
 */
public class DateCompareJava8 {
    /**
     * Field to use Logging
     */
    public static final Logger LOG = LogManager
            .getLogger(DateCompareJava8.class.getName());

    /**
     * This method count max num of days in the given month in the given year
     * and return {@code daysInMonth}. Also, if param {@code daysToCheck} is
     * bigger than maximum, method displays a message of error on the screen.
     *
     * <p> Method using only Java8+ methods to work with date.
     *
     * @param   year
     *          the year
     * @param   month
     *          the month
     * @param   daysToCheck
     *          amount of days with which will compare
     *          real maximum of days in given month
     * @return  max num of days in the given month in the given year
     */
    public int getDaysInMonthJava8(int year, int month, int daysToCheck) {
        YearMonth yearMonthObj = YearMonth.of(year, month);
        int daysInMonth = yearMonthObj.lengthOfMonth();
        if (daysToCheck > daysInMonth) {
            LOG.info("Incorrect number of days in " + yearMonthObj.getMonth()
                    + " in " + yearMonthObj.getYear());
            LOG.info("The largest number in this year: " + daysInMonth);
        }
        return daysInMonth;
    }
}
