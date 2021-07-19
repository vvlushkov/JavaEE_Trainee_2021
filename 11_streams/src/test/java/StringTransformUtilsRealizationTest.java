import com.solution.lushkov.StringTransformUtilsRealization;
import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import org.junit.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;

public class StringTransformUtilsRealizationTest {
    private static final Logger LOG = LogManager
            .getLogger(StringTransformUtilsRealizationTest.class.getName());

    StringTransformUtilsRealization testStr = new StringTransformUtilsRealization();

    @Test
    public void findDistinctToUpperCaseTest() {
        String[] strings = new String[] {"foo", "bar", "foo", "baz"};
        List<String> list = testStr.findDistinctToUpperCase(strings);
//        String[] array = new String[list.size()];
//        list.toArray(array);
//        LOG.info(Arrays.toString(array));
        LOG.info(list);
    }

    @Test
    public void countNegativeIntegersTest() {
        List<Integer> numbers = new ArrayList<>(Arrays
                .asList(1, 1, 0, 0, -1, -1, -23, -23, -233, 1));
        LOG.info(testStr.countNegativeIntegers(numbers));
    }

    @Test
    public void countWordsInListTest() {
        List<String> words = new ArrayList<>(Arrays
                .asList("foo is big ", "bar", "foo", "baz", "foo",
                        "foo is big ", "foo is big ", "foo is big"));
        String wordToCount = "foo is big ";
        LOG.info(testStr.countWordsInList(words, wordToCount));
    }

    @Test
    public void toDistinctList() {
        List<String[]> stringsArrays = new ArrayList<>();
        stringsArrays.add(new String[] {"foo", "bar", "baz"});
        stringsArrays.add(new String[] {"foo", "bar", "fuz"});

        List<String> list = testStr.toDistinctList(stringsArrays);
//        String[] array = new String[list.size()];
//        list.toArray(array);
//        LOG.info(Arrays.toString(array));
        LOG.info(list);
    }

    @Test
    public void isAllStringsLongerThenTest() {
        long numberOfCharacters = 3;
        List<String> strings = List.of("test", "tonystark", "diobrando");
        LOG.info(testStr.isAllStringsLongerThen(strings, numberOfCharacters));
    }

    @Test
    public void getMaxFiveNumbersTest() {
        int[] numbers = {1, 2, 3, 4, 9, 8, 7, 6, 5};

        List<Integer> list = testStr.getMaxFiveNumbers(numbers);
//        Integer[] array = new Integer[list.size()];
//        list.toArray(array);
//        LOG.info(Arrays.toString(array));
        LOG.info(list);
    }

    @Test
    public void getStringOfNumbersTest() {
        int[] numbers = {1, 53, 63, 23};
        //int[] numbers = null;

        LOG.info(testStr.getStringOfNumbers(numbers));
    }

    @Test
    public void getFirstCharactersAsStringTest() {
        List<String> strings = new ArrayList<>(Arrays
                .asList("serial", "number", "of", "turbo"));

        LOG.info(testStr.getFirstCharactersAsString(strings));
    }

    @Test
    public void groupByLength() {
        List<String> strings = new ArrayList<>(Arrays
                .asList("Irene", "Wendy", "Seulgi", "Joy", "Yeri", "Red", "Velvet"));
        //Map<Integer, List<String>> map = testStr.groupByLength(strings);
        LOG.info(testStr.groupByLength(strings));
    }
}