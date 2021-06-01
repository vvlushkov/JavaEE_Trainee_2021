package com.solution.lushkov;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.math.RoundingMode;
import java.util.Arrays;

/*
    @author Victor Lushkov
*/
public class Main {
    private static final Logger LOG = LogManager.getLogger(Main.class);

    public static void main(String[] args) {
        BaseTypesUtilRealization baseTypesVar = new BaseTypesUtilRealization();

        /*
            "toggleScientificNotation" method realization
         */
        String num1 = "2342352365252";
        String num2 = "25E12";
        LOG.info(baseTypesVar.toggleScientificNotation(num1));
        LOG.info(baseTypesVar.toggleScientificNotation(num2));
        System.out.println(baseTypesVar.toggleScientificNotation(num1));
        System.out.println(baseTypesVar.toggleScientificNotation(num2));

        /*
           "sort" method realization
         */
        int[] array1 = new int[] {1, 11, 34, 21, 89, 45, 7};
        LOG.info("Array: " + Arrays.toString(array1));
        System.out.println("Array: " + Arrays.toString(array1));
        LOG.info("\nSorted array: "
                + Arrays.toString(baseTypesVar.sort(array1)));
        System.out.println("Sorted array: "
                + Arrays.toString(baseTypesVar.sort(array1)));

        /*
            "arithmeticMean" method realization
         */
        int[] array2 = new int[] {1, 9, 8, 2};
        LOG.info("Array: " + Arrays.toString(array2));
        System.out.println("Array: " + Arrays.toString(array2));
        LOG.info("Arithmetical mean: " + baseTypesVar.arithmeticMean(array2));
        System.out.println("Arithmetical mean: " + baseTypesVar.arithmeticMean(array2));


        /*
            "format" method realization
         */
        double doubleNum1 = 34534532.5;
        System.out.println(baseTypesVar.format(doubleNum1, "ru"));
        System.out.println(baseTypesVar.format(doubleNum1, "en"));

        /*
            "plus" method realization
         */
        String numberPlus1 = "3325";
        String numberPlus2 = "33.25";
        System.out.println(baseTypesVar.plus(numberPlus1, numberPlus2));

        /*
            "minus" method realization
         */
        String numberMin1 = "3325";
        String numberMin2 = "33.25";
        System.out.println(baseTypesVar.minus(numberMin1, numberMin2));

        /*
            "mul" method realization
         */
        String numberMul1 = "35";
        String numberMul2 = "33.25";
        System.out.println(baseTypesVar.mul(numberMul1, numberMul2));

        /*
            "div" method realization
         */
        String numberDiv1 = "10456.78";
        String numberDiv2 = "5.5";
        System.out.println(baseTypesVar.div(numberDiv1, numberDiv2));

    }
}