package com.lushkov.solution;

import com.nixsolutions.ppp.dates.DateUtilJava7;

import org.apache.log4j.Logger;
import org.apache.log4j.LogManager;

import java.util.Date;
import java.util.Calendar;
import java.util.GregorianCalendar;

public class DateUtilJava7Realization implements DateUtilJava7 {
    private static final Logger LOG = LogManager
            .getLogger(DateUtilJava7Realization.class.getName());
    //Неизменяемые переменные обозначающие кол-во милисекунд
    //в году, месяце и дне соответственно.
    private final static long MILLIS_IN_YEAR = 31536000000L;
    private final static long MILLIS_IN_MONTH = 2592000000L;
    private final static long MILLIS_IN_DAY = 86400000L;

    /**
     * МЕТОД РАБОТАЕТ, НО С ПОГРЕШНОСТЯМИ В НЕСКОЛЬКО ДНЕЙ!
     *
     * Метод возвращает строковое значение периода между датами в формате "[A years] [B months] [C days]" (скобки
     * показывают опциональность части и не должны отображаться в результирующей строке).
     * Множественное и единичное числа должны правильно обрабатываться.
     *
     * Примеры результата:
     *  2 days
     *  5 years 1 day
     *  1 year 2 months 19 days
     *
     * @param date1 - начальная дата
     * @param date2 - конечная дата
     * @return - возвращает строковое значение периода между датами
     */
    @Override
    public String between(Date date1, Date date2) {
        long millis;
        if (date1.compareTo(date2) == 0) {
            return "Dates are identical.";
        } else {
            //проверка, какая дата позднее
            if (date1.compareTo(date2) > 0) { //date1 > date2
                millis = date1.getTime() - date2.getTime();
                return dateCounter(millis);
            } else {
                if (date1.compareTo(date2) < 0) { //date1 < date2
                    millis = date2.getTime() - date1.getTime();
                    return dateCounter(millis);
                }
            }
        }
        return null;
    }
    //Этот метод считает кол-во лет, месяцев и дней из милисекунд,
    //присланных как параметр. Ипользуется методом выше.
    //Работает, но с погрешностями в несколько дней.
    private String dateCounter(long millis) {
        long years = 0;
        long months = 0;
        long days = 0;
        StringBuilder result = new StringBuilder("");
        //подсчет лет и запись их если кол-во больше нуля
        years = millis / MILLIS_IN_YEAR;
        if (years > 0) {
            result.append(years).append(" years ");
        }
        //подсчет месяцев и запись их если кол-во больше нуля
        months = (millis % MILLIS_IN_YEAR) / MILLIS_IN_MONTH;
        if (months > 0) {
            result.append(months).append(" months ");
        }
        //подсчет дней и запись их если кол-во больше нуля
        days = (millis % MILLIS_IN_MONTH) / MILLIS_IN_DAY;
        if (days > 0) {
            result.append(days).append(" days ");
        }
        LOG.info(result);
        return result.toString();
    }

    /**
     * Метод вычисляет количество дней в каждом месяце в указанном году.
     *
     * @param year - номер года
     * @return - массив содержащий количество дней в каждом месяце в указанном году
     */
    @Override
    public int[] daysInMonth(int year) {
        int[] monthsArray = new int[12];
        monthsArray[0] = 31; //Jan
        monthsArray[1] = 0; //Feb
        monthsArray[2] = 31; //Mar
        monthsArray[3] = 30; //Apr
        monthsArray[4] = 31; //May
        monthsArray[5] = 30; //Jun
        monthsArray[6] = 31; //Jul
        monthsArray[7] = 31; //Aug
        monthsArray[8] = 30; //Sep
        monthsArray[9] = 31; //Oct
        monthsArray[10] = 30; //Nov
        monthsArray[11] = 31; //Dec
        //проверка на високосность
        if ((year % 4 != 0) || ((year % 100 == 0) && (year % 400 != 0))) {
            monthsArray[1] = 28;
        } else {
            monthsArray[1] = 29;
        }
        return monthsArray;
    }

    /**
     * ПОЧЕМУ-ТО НЕПРАВИЛЬНО РАБОТАЕТ :(
     *
     * Метод возвращает массив дат которые выподают на понедельник в указанном годе и месяце.
     *
     * @param month - месяц (от 0 до 11)
     * @param year - год
     *
     * @return - массив дат где каждая дата является понедельником.
     */
    @Override
    public Date[] mondays(int month, int year) {
        int day = 1;
        //Конструктор календаря
        Calendar calendar = new GregorianCalendar(year, month, day);
        Date[] mondays = new Date[5];
        int k = 0;  //счетчик добавлений дат в массив
        for (int i = 0; i < 30; i++) {
            if (calendar.get(GregorianCalendar.DAY_OF_WEEK) ==  GregorianCalendar.MONDAY) {
                mondays[k] = calendar.getTime();
                k++;
            }
            //Смещение даты на один день
            calendar.add(GregorianCalendar.DAY_OF_MONTH, 1);
        }
        return mondays;
    }

    /**
     * Метод проверяет является ли указанная дата пятницей 13-го.
     * @param date - дата
     * @return - true если указанная дата является пятницей 13-го, иначе - false.
     */
    @Override
    public boolean isFridays13(Date date) {
        //записывает в объект текущее время и дату
        Calendar calendar = Calendar.getInstance();
        //выставляет переданное время
        calendar.setTime(date);
        //Проверка на 13 число и пятницу
        return (calendar.get(Calendar.DAY_OF_MONTH) == 13)
                && (calendar.get(Calendar.DAY_OF_WEEK) == Calendar.FRIDAY);
    }

    /**
     * НЕ СДЕЛАН
     *
     * Метод конвертирует дату в строку используя полный формат и локаль для указанного языка.
     * @param date - дата
     * @param language - двухбуквенный или трехбуквенный код языка соответсвующий ISO 639
     * @return - Дата в виде строки.
     */
    @Override
    public String formatFull(Date date, String language) {
        return null;
    }
}
