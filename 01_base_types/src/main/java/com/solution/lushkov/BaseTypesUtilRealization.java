package com.solution.lushkov;

import com.nixsolutions.ppp.basetypes.BaseTypesUtil;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.util.Locale;


public class BaseTypesUtilRealization implements BaseTypesUtil {

    @Override
    public String toggleScientificNotation(String str) {
        if (str == null) {
            return null;
        }
        if (str.contains("e") || str.contains("E")) {
            return BigDecimal.valueOf(Double.parseDouble(str)).toPlainString();
        } else {
            return new DecimalFormat("0.0E0").format(Double.parseDouble(str));
        }
    }

    @Override
    public int[] sort(int[] array) {
        int n = array.length;
        int[] arrayToSort = array.clone();

        for (int i = 0; i < n-1; i++) {
            for (int j = 0; j < n - i - 1; j++)
                if (arrayToSort[j] > arrayToSort[j + 1]) {
                    int temp = arrayToSort[j];
                    arrayToSort[j] = arrayToSort[j + 1];
                    arrayToSort[j + 1] = temp;
                }
        }
        return arrayToSort;
    }

    @Override
    public float arithmeticMean(int[] array) {
        int sum = 0;
        for (int i : array){
            sum += i;
        }
        return (float)(sum / array.length);
    }

    @Override
    public String format(double n, String language) {
        if (language != null) {
            Locale locale = new Locale(language);
            NumberFormat numFormat =NumberFormat.getInstance(locale);
            return numFormat.format(n);
        }
        return  null;
    }

    @Override
    public String plus(String value1, String value2) {
        return (new BigDecimal(value1).add(new BigDecimal(value2))).toString();
    }

    @Override
    public String minus(String value1, String value2) {
        return (new BigDecimal(value1).subtract(new BigDecimal(value2)))
                .toString();
    }

    @Override
    public String mul(String value1, String value2) {
        return (new BigDecimal(value1).multiply(new BigDecimal(value2)))
                .toString();
    }

    @Override
    public String div(String value1, String value2) {
        return (new BigDecimal(value1).divide(new BigDecimal(value2), 5, RoundingMode.HALF_EVEN))
                .toString();
    }
}