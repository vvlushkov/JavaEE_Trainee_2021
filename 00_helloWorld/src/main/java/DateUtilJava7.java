import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;

import java.time.*;
import java.text.SimpleDateFormat;
import java.util.*;

public class DateUtilJava7 implements com.nixsolutions.ppp.dates.DateUtilJava7 {
    private static final Logger LOG = LogManager
            .getLogger(DateUtilJava7.class.getName());


    /*
        Нет нормального джава дока
        Нет описания метода, собсна он и не доделан
        Использует библиотеки воьсмой джавы, не по заданию
        Реализацию не очень понял
        Возвращает нулл, а какое-то найденное значение на экран выводит
        Вроде бы это кол-во дней
     */
    /**
     * @param date1
     * @param date2
     * @return String
     */
    @Override
    public String between(Date date1, Date date2) {
        //not finished method
        long minutes = Duration.between(date1.toInstant(), date2.toInstant()).toDays();
        LOG.info(minutes);
        return null;
    }


    /*
        Нет нормального джава дока
        Нет никакого описания метода
        Реализацию сложно понять из-за отсутвствия описания
        Переменная с большой буквы
        Магические числа
        Реализация хорошая, в принципе
     */
    /**
     * @param year
     * @return int array
     */
    @Override
    public int[] daysInMonth(int year) {
        Calendar CurrentYear = Calendar.getInstance();
        CurrentYear.set(Calendar.YEAR, year);
        int[] monthList = new int[12];
        for (int i = 0; i < 12; i++) {
            CurrentYear.set(Calendar.MONTH, i);
            monthList[i] = CurrentYear.getActualMaximum(Calendar.DAY_OF_MONTH);
        }
        return monthList;
    }


 /*
        Нет нормального джава дока
        Нет никакого описания метода
        Реализацию сложно понять из-за отсутвствия описания
        Переменная с большой буквы
        Мелкая придирка: название перменной не в CamelCase формате
     */
    /**
     * @param month
     * @param year
     * @return Date array
     */
    @Override
    public Date[] mondays(int month, int year) {
        Calendar CurrentDate = Calendar.getInstance();
        ArrayList<Date> dayslist = new ArrayList<>();
        CurrentDate.set(Calendar.YEAR, year);
        CurrentDate.set(Calendar.MONTH, month);
        for (int i = 1; i < CurrentDate.getActualMaximum(Calendar.DAY_OF_MONTH); i++) {
            CurrentDate.set(year, month, i);
            if (CurrentDate.get(Calendar.DAY_OF_WEEK) == Calendar.MONDAY) {
                dayslist.add(CurrentDate.getTime());
            }
        }
        Date[] datesList = new Date[dayslist.size()];
        return dayslist.toArray(datesList);
    }

    /*
        Нет нормального джава дока
        Нет никакого описания метода
        Название перменной с большой буквы
        Строки > 80
     */
    /**
     * @param date
     * @return boolean value
     */
    @Override
    public boolean isFridays13(Date date) {
        Calendar CurrentDate = Calendar.getInstance();
        //the date format isn't working well
        //System.out.println(date);
        CurrentDate.setTime(date);
        int NeededDate = 13;
        if ((CurrentDate.get(Calendar.DAY_OF_WEEK) == Calendar.FRIDAY && CurrentDate.get(Calendar.DAY_OF_MONTH) == NeededDate)) {
            return true;
        } else {
            return false;
        }
    }


    /*
        Нет нормального джава дока
        Нет никакого описания метода
        Строки > 80
     */
    /**
     * @param date
     * @param language
     * @return String result
     */
    @Override
    public String formatFull(Date date, String language) {
        SimpleDateFormat dateFormat = new SimpleDateFormat("dd-MMMM-yy", Locale.forLanguageTag(language));
        return (dateFormat.format(date));
    }
}