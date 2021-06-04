package com.lushkov.solution;

//Imported classes for using logging.
import org.apache.log4j.Logger;
import org.apache.log4j.LogManager;

/**
 * @author Victor Lushkov
 * This class realize Calculator class.
 */
public class Main {
    /**
     * Constant for using logging output.
     */
    private static final Logger LOG = LogManager
                                    .getLogger(Main.class.getName());

    /**
     * This method Calculator class realization.
     * @param args - standard argument for "main" method.
     */
    public static void main(String[] args) {
        /**
         * Create object of Calculator class.
         */
        Calculator calculator = new Calculator();

        /**
         * Integer variables for using in Calculator methods.
         */
        int number1 = 127;
        int number2 = 324;

        /**
         * Show results of calculating.
         */
        LOG.info("\t\tResults of HomeWork:");
        LOG.info("\t\tResult: " + calculator.plus(number1, number2));
        LOG.info("\t\tResult: " + calculator.minus(number1, number2));
        LOG.info("\t\tResult: " + calculator.divide(number1,number2));
        LOG.info("\t\tResult: " + calculator.multiply(number1, number2));
    }
}
