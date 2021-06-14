import org.apache.log4j.Logger;
import org.apache.log4j.LogManager;

import java.time.Instant;
import java.time.LocalDate;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.util.Arrays;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;

public class MainDZ {
    private static final Logger LOG = LogManager
            .getLogger(MainDZ.class.getName());

    public static void main(String[] args) {
        //тест для жабы7
        //1-ый метод
        LOG.info("Java 7");
        LOG.info("First method:");
        Calendar calendar1 = new GregorianCalendar(1977, Calendar.MAY, 23);
        Calendar calendar2 = new GregorianCalendar(1978, Calendar.JUNE, 25);
        Date date1 = calendar1.getTime();
        Date date2 = calendar2.getTime();
        LOG.info(date1);
        LOG.info(date2);
        DateUtilJava7 javaDate7 = new DateUtilJava7();
        LOG.info(javaDate7.between(date1, date2));
        //2-ой метод
        LOG.info("Second method:");
        LOG.info(Arrays.toString(javaDate7.daysInMonth(2026)));

        //3-ий метод
        LOG.info("Third method:");
        for (var mon : javaDate7.mondays(5, 2021)) {
            LOG.info(mon);
        }

        //4-ый метод
        LOG.info("Fourth method:");
        Calendar calendar = new GregorianCalendar(2019, Calendar.DECEMBER, 13);
        Date date13 = calendar.getTime();
        LOG.info(javaDate7.isFridays13(date13));
        //5-ый метод
        LOG.info("Fifth method:");
        LOG.info(javaDate7.formatFull(date13, "uk"));

        //тест для жабы8
        LOG.info("---------------------------------");
        DateUtilJava8 javaDate8 = new DateUtilJava8();
        //1-ый метод
        LOG.info("Java 8");
        LOG.info("First method:");
        LocalDate localDate1 = LocalDate.of(1977, 5, 23);
        LocalDate localDate2 = LocalDate.of(1978, 6, 25);
        LOG.info(localDate1);
        LOG.info(localDate2);
        LOG.info(javaDate8.between(localDate1, localDate2));
        //2-ый метод
        LOG.info("Second method:");
        Instant instant = Instant.now();
        javaDate8.mondays(instant);
        for (var mon : javaDate8.mondays(instant)) {
            LOG.info(mon);
        }
        //3-ый метод
        LOG.info("Third method:");
        LocalDate localDateFri13 = LocalDate.of(2019, 12, 13);
        LOG.info(javaDate8.isFridays13(localDateFri13));
        //4-ый метод
        LOG.info("Fourth method:");
        ZonedDateTime dateTime;
        dateTime = ZonedDateTime.ofInstant(instant, ZoneId.of("Europe/Kiev"));
        LOG.info(javaDate8.formatFullJava8(dateTime, "uk"));
    }
}