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

    /** Field of string with information about date compare. */
    private String logInf;

    /** Field of result of date compare. */
    private String result;

    /**
     * Method to get {@code logInf}.
     *
     * @return value of field {@code logInf}.
     */
    public String getLogInf() {
        return logInf;
    }

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
            result = "INCORRECT";
        } else {
            result = "CORRECT";
        }
        assignLogInfValue(daysToCheck, daysInMonth, month, year, yearMonthObj);
        LOG.info(logInf);
        return daysInMonth;
    }

    private void assignLogInfValue(int daysToCheck, int daysInMonth,
                                int month, int year, YearMonth yearMonthObj) {
        logInf = "Actual date: " + daysToCheck + "."
                + month + "." + year + "\nThere are "
                + result + " number of days in " + yearMonthObj.getMonth()
                + " in " + yearMonthObj.getYear() + "!"
                + "\nThe largest number" + " of days in this month "
                + "in this year: " + daysInMonth
                + "\nComparison was made by "
                + getClass().getName() + " class.";
    }
}
