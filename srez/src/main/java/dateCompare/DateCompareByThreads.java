package dateCompare;

import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;

/**
 * Class extends {@code Thread} to Override run method.
 * Used to compare dates by Java7 and Java8 technologies in parallel threads.
 */
public class DateCompareByThreads extends Thread {

    /**
     * Field to use Logging.
     */
    public static final Logger LOG = LogManager
            .getLogger(DateCompareByThreads.class.getName());

    /** Field of class with comparing functions. */
    private final Class<?> clazz;
    /** Field of year. */
    private final int year;
    /** Field of month. */
    private final int month;
    /** Field of days in month to check. */
    private final int daysToCheck;
    /** Start message when thread is starting. */
    private final String startMessage;

    /**
     * Constructor.
     * Determines the values of the fields by the received parameters.
     *
     * @param   clazz
     *          the class with which the comparison is made.
     * @param   year
     *          the year.
     * @param   month
     *          the month.
     * @param   daysToCheck
     *          days on month.
     */
    public DateCompareByThreads(Class<?> clazz, int year, int month, int daysToCheck) {
        this.clazz = clazz;
        this.year = year;
        this.month = month;
        this.daysToCheck = daysToCheck;
        this.startMessage = "Start comparing dates by " + clazz.getName();
    }

    /**
     * Overridden {@code run thread} method.
     * Depending on the received class for comparing dates,
     * starts comparison by the corresponding function.
     */
    @Override
    public void run() {
        LOG.info("Thread " + getName() + " started");
        if (clazz.getSimpleName().equals("DateCompareJava7")) {
            DateCompareJava7 dateComp7 = new DateCompareJava7();
            LOG.info(startMessage);
            dateComp7.getDaysInMonthJava7(year, month, daysToCheck);
        } else {
            if (clazz.getSimpleName().equals("DateCompareJava8")) {
                DateCompareJava8 dateComp8 = new DateCompareJava8();
                LOG.info(startMessage);
                dateComp8.getDaysInMonthJava8(year, month, daysToCheck);
            } else {
                LOG.info("INCORRECT DATA!");
            }
        }
        LOG.info("Thread " + getName() + " finished.");
    }
}
