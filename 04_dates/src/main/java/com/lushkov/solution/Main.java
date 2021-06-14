package com.lushkov.solution;

import org.apache.log4j.Logger;
import org.apache.log4j.LogManager;

import java.time.Instant;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.temporal.ChronoField;
import java.util.Arrays;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;

public class Main {
    private static final Logger LOG = LogManager
            .getLogger(Main.class.getName());

    public static void main(String[] args) {
        //тест для жабы7

        //1-ый метод
        LOG.info("Java 7");
        LOG.info("First method:");
        Calendar calendar1 = new GregorianCalendar(1977, Calendar.MAY, 23);
        Calendar calendar2 = new GregorianCalendar(1978, Calendar.JUNE, 23);
        Date date1 = calendar1.getTime();
        Date date2 = calendar2.getTime();
        LOG.info(date1);
        LOG.info(date2);
        DateUtilJava7Realization javaDate7 = new DateUtilJava7Realization();
        LOG.info(javaDate7.between(date1, date2));
        //2-ой метод
        LOG.info("Second method:");
        LOG.info(Arrays.toString(javaDate7.daysInMonth(2016)));
        //3-ий метод
        LOG.info("Third method:");
        LOG.info(Arrays.toString(javaDate7.mondays(2016, Calendar.APRIL)));
        //4-ый метод
        LOG.info("Fourth method:");
        Calendar calendar = new GregorianCalendar(2019, Calendar.DECEMBER, 13);
        Date date13 = calendar.getTime();
        LOG.info(javaDate7.isFridays13(date13));
        //пятого нема

        //тест для жабы8
        //1-ый метод
        LOG.info("Java 8");
        LOG.info("First method:");
        LocalDate localDate1 = LocalDate.of(1977, 5, 13);
        LocalDate localDate2 = LocalDate.of(1975, 6, 23);
        LOG.info(localDate1);
        LOG.info(localDate2);
        DateUtilJava8Realization javaDate8 = new DateUtilJava8Realization();
        LOG.info(javaDate8.between(localDate1, localDate2));
        LOG.info("Second method:");
        Instant instant = Instant.now();
        //что-то далее
        LOG.info("Third method:");
        LocalDate localDateFri13 = LocalDate.of(2019, 12, 13);
        LOG.info(javaDate8.isFridays13(localDateFri13));

    }
}
