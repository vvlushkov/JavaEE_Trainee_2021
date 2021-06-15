import java.time.*;
import java.time.format.DateTimeFormatter;
import java.time.format.FormatStyle;
import java.util.ArrayList;
import java.util.Locale;

public class DateUtilJava8 implements com.nixsolutions.ppp.dates.DateUtilJava8 {

    /*
        Кое-какой джава док
        Нет описания метода
        Пробелы до и после оператора
        Строки > 80
     */
    /**
     * @param date1 start
     * @param date2 finish
     * @return String format result
     */
    @Override
    public String between(LocalDate date1, LocalDate date2) {
        String dateToString=Period.between(date1,date2).toString();
        if (Period.between(date1,date2).getYears()>1)
            dateToString=dateToString.replace("Y"," years ");
        else
            dateToString=dateToString.replace("Y"," year ");
        if (Period.between(date1,date2).getMonths()>1)
            dateToString=dateToString.replace("M"," months ");
        else
            dateToString=dateToString.replace("M"," month ");
        if ((Period.between(date1,date2).getDays()>1)||(Period.between(date1,date2).getDays()==0))
            dateToString=dateToString.replace("D"," days ");
        else
            dateToString=dateToString.replace("D"," day ");

        return dateToString.replace("P","").replace("-"," ");
    }


     /*
        Кое-какой джава док
        Нет описания метода
        Мелкая придирка: название перменной не в CamelCase формате
     */
    /**
     * @param instant current date
     * @return monday's date array
     */
    @Override
    public LocalDate[] mondays(Instant instant) {
        ZoneId zoneDefault = ZoneId.of( "America/Edmonton" );
        LocalDate date = LocalDate.ofInstant( instant , zoneDefault );
        ArrayList<LocalDate> dayslist = new ArrayList<>();
        for (int i = 1; i < date.lengthOfMonth(); i++) {
            date = LocalDate.of(date.getYear(), date.getMonth(), i);
            if (date.getDayOfWeek().name() == "MONDAY") {
                dayslist.add(date);
            }
        }
        LocalDate[] datesList=new LocalDate[dayslist.size()];
        return dayslist.toArray(datesList);
    }

     /*
        Нет нормального джава док
        Нет описания метода
        Пробелы до и после оператора
        Строки > 80
     */
    /**
     * @param date
     * @return boolean result
     */
    @Override
    public boolean isFridays13(LocalDate date) {
        int NeededDate=13;
        if (date.getDayOfWeek().name().equals("FRIDAY") &&date.getDayOfMonth()==NeededDate) {
            return true;
        }
        else {
            return false;
        }
    }

    /*
        Нет нормального джава док
        Нет описания метода
        Строки > 80
     */
    /**
     *
     * @param date
     * @param language
     * @return String result
     */
    @Override
    public String formatFullJava8(ZonedDateTime date, String language) {
        DateTimeFormatter pattern = DateTimeFormatter.ofLocalizedDateTime(FormatStyle.FULL).withLocale(Locale.forLanguageTag(language));
        return (date.format(pattern));
    }
}