package com.solution.lushkov;

import org.apache.log4j.Logger;
import org.apache.log4j.LogManager;
import java.util.Arrays;

/**
 * @author Victor Lushkov
 */
public class Main {
    /**
     * Constant for using logging output.
     */
    private static final Logger LOG = LogManager
            .getLogger(Main.class.getName());

    public static void main(String[] args) {
        String ip = "253.2.111.72";
        StringUtilsRealization tool = new StringUtilsRealization();
        //тест 1-го метода
        LOG.info(Arrays.toString(tool.ip2Bytes(ip)));

        //тест 2-го метода
        LOG.info(tool.convertIp(ip));

        //тест 3-го метода
        LOG.info(tool.alphabet());


        /*
        //тест 4-го метода
            String uri = "https://moodle.nixsolutions.com/course/view.php?id=33";
            String[] urlSchema = new String[8];
            String regex = "\\b(https?|ftp|file)://[-a-zA-Z0-9+&@#/%?=~_|!:,.;]*[-a-zA-Z0-9+&@#/%=~_|]";
            Pattern pattern = Pattern.compile(regex);
            Matcher matcher = pattern.matcher(uri);
            LOG.info(matcher.matches());
         */

        //тест 5-го метода
        String str = "baba,,home  hEEH ,sdf";
        LOG.info(tool.toCamelCase(str));

        //тест 6-го метода
        String str1 = "FabricioPizzaMaker";
        LOG.info(tool.fromCamelCase(str1));


    }
}
