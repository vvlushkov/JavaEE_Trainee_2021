package stringParse;

import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import readerIO.MyFileReader;

import java.io.Externalizable;
import java.io.IOException;
import java.io.ObjectInput;
import java.io.ObjectOutput;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Class with methods to find certain info from string.
 *
 * @author Victor Lushkov
 * @version 1.0
 */
public class Finder implements Externalizable {
    /**
     * Field to use Logging
     */
    public static final Logger LOG = LogManager
            .getLogger(MyFileReader.class.getName());

    /** Field of found days of month in string. */
    private int foundDaysInMonth;
    /** Field of found month in string. */
    private int foundMonth;
    /** Field of found year in string. */
    private int foundYear;
    /** Array of date. */
    int[] dateArrays = new int[3];

    /**
     * @return  array with info about date:
     *          <ul>
     *              <li>[0] - year</li>
     *              <li>[1] - month</li>
     *              <li>[2] - day</li>
     *          </ul>
     */
    public int[] getDateArrays() {
        return dateArrays;
    }

    public int getFoundDaysInMonth() {
        return foundDaysInMonth;
    }

    public int getFoundMonth() {
        return foundMonth;
    }

    public int getFoundYear() {
        return foundYear;
    }

    /**
     * This method find date in string
     * and return array with info about this date.
     *
     * <p> If some info of date is missing, method displays a message about it.
     *
     * @param   str
     *          the string in which to find the date
     * @return  array with info about date:
     *          <ul>
     *              <li>[0] - year</li>
     *              <li>[1] - month</li>
     *              <li>[2] - day</li>
     *          </ul>
     */
    public int[] dateFinder(String str) {
        yearFind(str);
        dateArrays[0] = foundYear;

        daysOfMonthFind(str);
        dateArrays[2] = foundDaysInMonth;

        monthFind(str);
        dateArrays[1] = foundMonth;

        return dateArrays;
    }

    /**
     * This method find days in month in string
     * and set it to {@code foundDaysInMonth}
     *
     * @param   str
     *          string in which days is finding.
     */
    public void daysOfMonthFind(String str) {
        String datePattern = "[\\s][0-9]{1,2}[\\s]";
        Pattern myPattern = Pattern.compile(datePattern);
        Matcher myMatcher = myPattern.matcher(str);
        if (myMatcher.find()) {
            foundDaysInMonth = Integer.parseInt(str.substring(myMatcher.start(), myMatcher.end()).trim());
        } else {
            LOG.info("Num of days not found!");
        }
    }

    /**
     * This method find month in string
     * and set it to {@code foundMonth}
     *
     * @param   str
     *          string in which month is finding.
     */
    public void monthFind(String str) {
        String monthFind = "";
        String monthsPattern = "((?:Jan(?:uary)?|Feb(?:ruary)?|Mar(?:ch)?|Apr(?:il)?|May|Jun(?:e)" +
                "?|Jul(?:y)?|Aug(?:ust)?|Sep(?:tember)?|Oct(?:ober)?|(Nov|Dec)(?:ember)?))";
        Pattern myPattern = Pattern.compile(monthsPattern);
        Matcher myMatcher = myPattern.matcher(str);
        if (myMatcher.find()) {
            monthFind = str.substring(myMatcher.start(), myMatcher.end());
            foundMonth = castMonths(monthFind);
        } else {
            LOG.info("Month not found!");
        }
    }

    /**
     * This method find year in string
     * and set it to {@code foundMonth}
     *
     * @param   str
     *          string in which year is finding.
     */
    public void yearFind(String str) {
        String yearPattern = "\\d{4}";
        Pattern myPattern = Pattern.compile(yearPattern);
        Matcher myMatcher = myPattern.matcher(str);
        if (myMatcher.find()) {
            foundYear = Integer.parseInt(str.substring(myMatcher.start(), myMatcher.end()));
        } else {
            LOG.info("Year not found!");
        }
    }

    /**
     * This method cast name of month from string
     * to its number in calendar.
     *
     * @param   monthName
     *          full or abbreviated name of month
     * @return  number of month
     */
    public int castMonths(String monthName) {
        Date date = null;
        try {
            date = new SimpleDateFormat("MMMM", Locale.ENGLISH).parse(monthName);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        Calendar cal = Calendar.getInstance();
        cal.setTime(date);
        //in Calendar the account starts from zero
        return cal.get(Calendar.MONTH) + 1;
    }

    /**
     * Method to serialize ////.
     *
     * @param   out
     *          the stream to write the object to.
     * @throws  IOException
     *          Includes any I/O exceptions that may occur.
     */
    @Override
    public void writeExternal(ObjectOutput out) throws IOException {
        dateArrays[0] = 1;
        dateArrays[0] = 123;
        dateArrays[0] = 2;
        out.writeInt(dateArrays[0]);
        out.writeInt(dateArrays[1]);
        out.writeInt(dateArrays[2]);
    }

    /**
     * Method to deserialize data from {@code ObjectInput in} and write written
     * values into fields {@code name, email and zip}.
     *
     * @param   in
     *          the stream to read data from in order to restore the object.
     * @throws  IOException
     *          if I/O errors occur.
     */
    @Override
    public void readExternal(ObjectInput in) throws IOException {
        dateArrays[0] = in.read();
        dateArrays[1] = in.read();
        dateArrays[2] = in.read();
    }
}


