package dateCompare;

import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;

import java.util.Calendar;
import java.util.Locale;

/**
 * Class with methods to compare dates using Java7-
 *
 * @author Victor Lushkov
 * @version 1.0
 */
public class DateCompareJava7 {
    /**
     * Field to use Logging
     */
    public static final Logger LOG = LogManager
            .getLogger(DateCompareJava7.class.getName());

    /**
     * This method count max num of days in the given month in the given year
     * and return {@code daysInMonth}. Also, if param {@code daysToCheck} is
     * bigger than maximum, method displays a message of error on the screen.
     *
     * <p> Method using only Java7- methods to work with date.
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
    public int getDaysInMonthJava7(int year, int month, int daysToCheck) {
        Calendar calendar = Calendar.getInstance();
        calendar.set(Calendar.YEAR, year);
        calendar.set(Calendar.MONTH, month - 1);
        int daysInMonth = calendar.getActualMaximum(Calendar.DATE);
        if (daysToCheck > daysInMonth) {
            LOG.info("Incorrect number of days in "
                    + calendar.getDisplayName(Calendar.MONTH,
                                                Calendar.LONG,
                                                Locale.ENGLISH)
                    + " in " + calendar.get(Calendar.YEAR) + "!");
            LOG.info("The largest number in this year: " + daysInMonth);
        }
        return daysInMonth;
    }
}
