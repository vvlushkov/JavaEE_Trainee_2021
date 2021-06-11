package com.lushkov.solution;

import com.nixsolutions.ppp.dates.DateUtilJava8;

import java.time.*;
import java.time.temporal.ChronoField;

public class DateUtilJava8Realization implements DateUtilJava8 {
    /**
     * Метод возвращает строковое значение периода между датами в формате "[A years] [B months] [C days]" (скобки
     * показывают опциональность части и не должны отображаться в результирующей строке).
     * Множественное и единичное число должно правильно обрабатываться.
     *
     * Примеры результата:
     *  2 days
     *  5 years 1 day
     *  1 year 2 months 19 days
     *
     * @param date1 - начальная дата
     * @param date2 - конечная дата
     * @return - строковое значение периода между датами
     */
    @Override
    public String between(LocalDate date1, LocalDate date2) {
        if (date1.compareTo(date2) == 0) {
            return "Dates are identical.";
        } else {
            //проверка, какая дата позднее
            if (date1.compareTo(date2) < 0) { //date1 > date2
                return dateCounter(date1, date2);
            } else {
                if (date1.compareTo(date2) > 0) { //date1 < date2
                    return dateCounter(date2, date1);
                }
            }
        }
        return  null;
    }

    /**
     * Этот метод считает кол-во лет, месяцев и дней из милисекунд,
     * присланных как параметр. Ипользуется методом выше.
     * Работает, но с погрешностями в несколько дней.
     *
     * @param date1 - первая дата
     * @param date2 - вторая дата
     * @return - возвращает строку с информацие о разнице между датами
     */
    private static String dateCounter (LocalDate date1, LocalDate date2) {
        StringBuilder str = new StringBuilder();
        //подсчет лет и запись их если кол-во больше нуля
        if (Period.between(date1, date2).getYears() > 0) {
            str.append(Period.between(date1, date2).getYears()).append(" years ");
        }
        //подсчет месяцев и запись их если кол-во больше нуля
        if (Period.between(date1, date2).getMonths() > 0) {
            str.append(Period.between(date1, date2).getMonths()).append(" months ");
        }
        //подсчет дней и запись их если кол-во больше нуля
        if (Period.between(date1, date2).getDays() > 0) {
            str.append(Period.between(date1, date2).getDays()).append(" days ");
        }
        return str.toString();
    }

    /**
     * НЕ СДЕЛАН
     *
     * Метод возвращает массив дат которые выподают на понедельник в указанном годе и месяце.
     *
     * @param instant - текущая дата
     * @return - массив дат где каждая дата является понедельником.
     */
    @Override
    public LocalDate[] mondays(Instant instant) {
        //счетчик добавлений дат в массив
        int k = 0;
        LocalDate[] mondaysArray = new LocalDate[5];
        ZonedDateTime inst = instant.atZone(ZoneId.of("Europe/Kiev"));
        /*
        for (int i = 0; i < 31; i++) {
            if (inst.getDayOfWeek() == DayOfWeek.MONDAY) {
            }
        }

        if (inst.getDayOfWeek() == DayOfWeek.MONDAY) {

        }
         */

        return new LocalDate[0];
    }

    /**
     * Метод проверяет является ли указанная дата пятницей 13-го.
     * @param date - дата
     * @return - true если указанная дата является пятницей 13-го, иначе - false.
     */
    @Override
    public boolean isFridays13(LocalDate date) {
        //проверка на 13 число и пятницу и вівод булевого значения
        return ("FRIDAY".equals(date.getDayOfWeek().toString())
                                && date.getDayOfMonth() == 13);
    }

    /**
     * НЕ СДЕЛАН
     *
     * Метод конвертирует дату в строку используя полный формат и локаль для указанного языка
     * @param date - дата
     * @param language - двухбуквенный или трехбуквенный код языка соответсвующий ISO 639
     * @return - строка, содержащая форматированную дату
     */
    @Override
    public String formatFullJava8(ZonedDateTime date, String language) {
        return null;
    }
}
