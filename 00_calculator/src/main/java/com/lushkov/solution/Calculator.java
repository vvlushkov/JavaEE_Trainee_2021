package com.lushkov.solution;

//Imported classes for using logging.
import org.apache.log4j.Logger;
import org.apache.log4j.LogManager;

//Imported classes for using BigDecimal type.
import java.math.BigDecimal;
import java.math.RoundingMode;

/**
 * @author Victor Lushkov
 * Calculator - class wchich contains 4 method for 4 operations:
 * addition(method "plus"), subtraction(method "minus"),
 * multiplication(method "multiply") and division(method "divide")
 */
public class Calculator {
    /**
     * Constant for using logging output.
     */
    private static final Logger LOG = LogManager
                                    .getLogger(Calculator.class.getName());
    /**
     * This method adds two Integer numbers,
     * output the info message and returns the result of addition.
     *
     * @param number1 - first number;
     * @param number2 - second number;
     * @return the result of addition in Double type.
     */
    public double plus(int number1, int number2) {
        LOG.info("number1 + number2 = " + (double)(number1 + number2));
        return (number1 + number2);
    }

    /**
     * This method subtract two Integer numbers,
     * output the info message and returns the result of subtraction.
     *
     * @param number1 - first number;
     * @param number2 - second number;
     * @return the result of subtraction in Double type.
     */
    public double minus(int number1, int number2) {
        LOG.info("number1 - number2 = " + (double)(number1 - number2));
        return (number1 - number2);
    }

    /**
     * This method divide two Integer numbers,
     * output the info message and returns the result of division.
     *
     * @param number1 - first number;
     * @param number2 - second number;
     * @return the result of division in String type,
     * but for dividing is uses BigDecimal type for greater accuracy.
     */
    public String divide(int number1, int number2) {
        String result = (new BigDecimal(number1).divide(new BigDecimal(number2),
                5, RoundingMode.HALF_EVEN)).toString();
        LOG.info("number1 / number2 = " + result);
        return result;
    }

    /**
     * This method multiply two Integer numbers,
     * output the info message and returns the result of multiplying.
     *
     * @param number1 - first number;
     * @param number2 - second number;
     * @return the result of multiplication in String type,
     * but for multiplying uses BigDecimal type for greater accuracy.
     */
    public String multiply(int number1, int number2) {
        String result = (new BigDecimal(number1).multiply(new BigDecimal(number2)))
                .toString();
        LOG.info("number1 * number2 = " + result);
        return result;
    }

}
