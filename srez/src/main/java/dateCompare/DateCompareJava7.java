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
            result = "INCORRECT";
        } else {
            result = "CORRECT";
        }
        assignLogInfValue(daysToCheck, daysInMonth, month, year, calendar);
        LOG.info(logInf);
        return daysInMonth;
    }

    /**
     * Method assigns a value to {@code logInf}.
     *
     * @param   daysToCheck
     *          actual days in month.
     * @param   daysInMonth
     *          correct maximum days in month.
     * @param   month
     *          the month.
     * @param   year
     *          the year.
     * @param   calendar
     *          the object of {@code Calendar}.
     */
    private void assignLogInfValue(int daysToCheck, int daysInMonth,
                                   int month, int year, Calendar calendar) {
        logInf = "Actual date: " + daysToCheck + "."
                + month + "." + year + "\nThere are "
                + result + " number of days in "
                + calendar.getDisplayName(Calendar.MONTH,
                Calendar.LONG, Locale.ENGLISH) + " in "
                + calendar.get(Calendar.YEAR) + "!" + "\nThe largest number" +
                " of days in this month in this year: " + daysInMonth;
    }
}
