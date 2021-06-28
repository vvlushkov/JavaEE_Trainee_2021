package com.lushkov.solution;

import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

public class CalculatorTest {
    private static final Logger LOG = LogManager
            .getLogger(CalculatorTest.class.getName());

    private Calculator calculator = new Calculator();

    @Before
    public void beforeTest() {
        calculator = new Calculator();
        LOG.info("before.message");
    }

    @After
    public void afterTest() {
        calculator = null;
        LOG.info("after.message\n");
    }

    @Test
    public void testPlus() {
        LOG.info("PlusTest:");
        double expected = 45.0;
        int num1 = 46;
        int num2 = -1;
        double delta = 0.0;
        assertEquals(expected, calculator.plus(num1, num2), delta);
    }

    @Test
    public void minus() {
        LOG.info("MinusTest:");
        double expected = 46.0;
        int num1 = 45;
        int num2 = -1;
        double delta = 0.0;
        assertEquals(expected, calculator.minus(num1, num2), delta);
    }

    @Test
    public void divide() {
        LOG.info("DivideTest:");
        String expected = "-45.00000";
        int num1 = 45;
        int num2 = -1;
        assertEquals(expected, calculator.divide(num1, num2));
    }

    @Test(expected = ArithmeticException.class)
    public void divisionWithException() {
        LOG.info("DivisionWithException Test:");
        LOG.info("If there is no result output it's ok");
        int num1 = 45;
        int num2 = 0;
        LOG.info(calculator.divide(num1, num2));
    }

    @Test
    public void multiply() {
        LOG.info("MultiplyTest:");
        String expected = "-45";
        int num1 = 45;
        int num2 = -1;
        assertEquals(expected, calculator.multiply(num1, num2));
    }
}