package stringParse;

import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import readerIO.MyFileReader;

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
public class Finder {
    /**
     * Field to use Logging
     */
    public static final Logger LOG = LogManager
            .getLogger(MyFileReader.class.getName());

    /**
     * This method find date in string
     * and return array with info about this date.
     *
     * <p> If some info of date is missing, method displays a message about it.
     *
     * @param   str
     *          the string in which to find the date
     * @return  array with info about date:
     *          [0] - year;
     *          [1] - month
     *          [2] - day
     */
    public int[] dateFinder(String str) {
        int[] dataArrays = new int[3];
        String monthFind = "";

        String yearPattern = "\\d{4}";
        Pattern myPattern = Pattern.compile(yearPattern);
        Matcher myMatcher = myPattern.matcher(str);
        if (myMatcher.find()) {
            dataArrays[0] = Integer.parseInt(str.substring(myMatcher.start(), myMatcher.end()));
        } else {
            LOG.info("Year not found!");
        }

        String monthsPattern = "((?:Jan(?:uary)?|Feb(?:ruary)?|Mar(?:ch)?|Apr(?:il)?|May|Jun(?:e)" +
                "?|Jul(?:y)?|Aug(?:ust)?|Sep(?:tember)?|Oct(?:ober)?|(Nov|Dec)(?:ember)?))";
        myPattern = Pattern.compile(monthsPattern);
        myMatcher = myPattern.matcher(str);
        if (myMatcher.find()) {
            monthFind = str.substring(myMatcher.start(), myMatcher.end());
            dataArrays[1] = castMonths(monthFind);
        } else {
            LOG.info("Month not found!");
        }

        String datePattern = "[\\s][0-9]{1,2}[\\s]";
        myPattern = Pattern.compile(datePattern);
        myMatcher = myPattern.matcher(str);
        if (myMatcher.find()) {
            dataArrays[2] = Integer.parseInt(str.substring(myMatcher.start(), myMatcher.end()).trim());
        } else {
            LOG.info("Num of days not found!");
        }
        return dataArrays;
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
}


