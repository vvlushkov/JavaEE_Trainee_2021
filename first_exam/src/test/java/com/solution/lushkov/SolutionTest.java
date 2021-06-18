package com.solution.lushkov;


import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.time.LocalDate;
import java.time.Month;

public class SolutionTest {
    private Solution solution = new Solution();

    @Test
    public void readerTest() {
        String filePath = "/Users/Asus/Desktop/srez/srez.txt";
        System.out.println(solution.reader(filePath));
    }

    @Test
    public void dateFinder() {
    }

    @Test
    public void daysCounterJava8() {
        int year = 1999;
        int month = 2;
        int day = 29;
        solution.daysCounterJava8(year, month, day);
    }
}