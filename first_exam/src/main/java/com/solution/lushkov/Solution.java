package com.solution.lushkov;

import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;

import java.io.*;
import java.time.LocalDate;
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Solution {
    public static final Logger LOG = LogManager
            .getLogger(Solution.class.getName());
    /**
     * This method read info from file and write it to the string.
     *
     * @param fileName - path to file that need to read
     * @return - string with read info
     */
    public String reader(String fileName) {
        //FileInputStream fileInputStream = null;
        BufferedReader reader = null;
        StringBuilder stringBuilder = null;
        try {
            reader = new BufferedReader(new FileReader(fileName));
            stringBuilder = new StringBuilder();
            String line = null;
            while ((line = reader.readLine()) != null) {
                stringBuilder.append(line);
            }
        } catch (IOException e) {
            LOG.info(e.getMessage());
        } finally {
            try {
                reader.close();
            } catch (IOException e) {
                LOG.info(e.getMessage());
            }
        }
        return stringBuilder.toString();
    }

    /**
     * This method find date in string.
     *
     * @param str - string in which needed to find date
     */
    public void dateFinder(String str) {
        Pattern pattern1 = null;
        Matcher matcher1 = null;



    }

    /**
     * This method compares number of days in month from string
     * with real number of days in this month.
     * @param year - found year.
     * @param month - found month.
     * @param day - found day.
     */
    public void daysCounterJava8(int year, int month, int day) {
        LocalDate date = LocalDate.of(year, month, day);


    }
}
