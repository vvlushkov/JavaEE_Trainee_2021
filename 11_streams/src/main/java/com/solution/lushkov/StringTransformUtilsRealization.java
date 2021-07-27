package com.solution.lushkov;

import com.nixsolutions.ppp.streamoptional.StringTransformUtils;

import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;

import java.util.*;
import java.util.stream.Collectors;

/**
 * @author  Victor Lushkov
 */
public class StringTransformUtilsRealization implements StringTransformUtils {
    private static final Logger LOG = LogManager
            .getLogger(StringTransformUtilsRealization.class.getName());

    /**
     * Функция ищет уникальные строки из {@code strings},
     * капитализирует их(превращает все буквы в большие)
     * и возвращает в виде списка
     * Пример:
     * strings = ["foo", "bar", "foo", "baz"];
     * Вернет:
     * ["FOO", "BAR", "BAZ"]
     *
     * @param strings массив строк
     * @return Список строк
     */
    @Override
    public List<String> findDistinctToUpperCase(String[] strings) {
        List<String> list;
        list = Arrays.asList(strings);

        return list.stream()
                .distinct()
                .map(String::toUpperCase) //s -> s.toUpperCase()
                .collect(Collectors.toList());
    }

    /**
     * Функция считает сколько отрицательных чисел в списке чисел {@code integers}.
     *
     * @param integers список Integer чисел
     * @return количество отрицательных чисел в списке
     */
    @Override
    public long countNegativeIntegers(List<Integer> integers) {
        return integers.stream()
                .filter(i -> i < 0)
                .count();
    }

    /**
     * Функция считает сколько раз в списке слов(строк) {@code words}
     * встречается определенное слово(строка) {@code wordToCount}.
     *
     * @param words       список строк
     * @param wordToCount строка, количество повторений которой нужно найти
     * @return количество {@code wordToCount} в списке
     */
    @Override
    public long countWordsInList(List<String> words, String wordToCount) {
        return words.stream()
                .filter(s -> s.equals(wordToCount))
                .count();
    }

    /**
     * Функция превращает список массивов строк {@code stringsArrays} в список всех уникальных строк.
     * Пример:
     * stringsArrays = [ ["foo", "bar", "baz"], ["foo", "bar", "fuz"] ]
     * Вернет:
     * ["foo", "bar", "baz", "fuz"]
     *
     * @param stringsArrays список массивов строк
     * @return Список строк
     */
    @Override
    public List<String> toDistinctList(List<String[]> stringsArrays) {
        return stringsArrays.stream()
                .flatMap(Arrays::stream) //s -> Arrays.stream(s)
                .distinct()
                .collect(Collectors.toList());
    }

    /**
     * Функция проверяет, ялвяется ли длинна всех строк в списке {@code strings} больше
     * чем заданный параметр {@code numberOfCharacters}
     * Пример:
     * strings = ["test", "tonystark", "diobrando"],
     * numberOfCharacters = 3, функция вернет true
     * но если numberOfCharacters = 4, функция вернет false
     *
     * @param strings            список строк
     * @param numberOfCharacters количество символов
     * @return boolean значение
     * @throws IllegalArgumentException если {@code numberOfCharacters} - отрицательное число
     */
    @Override
    public boolean isAllStringsLongerThen(List<String> strings, long numberOfCharacters) {
        if (numberOfCharacters < 0) {
            throw new IllegalArgumentException("numberOfCharacters must be bigger than 0");
        }

        return strings.stream()
                .allMatch(s -> s.length() > numberOfCharacters);
    }

    /**
     * Функция принимает массив целых чисел {@code numbers} и возвращает 5 самых больших чисел
     * в порядке возростания.
     * Пример:
     * numbers = [1, 2, 3, 4, 9, 8, 7, 6, 5]
     * Вернет [5, 6, 7, 8, 9]
     *
     * @param numbers массив целых чисел
     * @return список самых больших чисел
     * @throws IllegalArgumentException если длинна {@code numbers} меньше чем 5
     */
    @Override
    public List<Integer> getMaxFiveNumbers(int[] numbers) {
        int amountOfMax = 5;
        if (numbers.length < amountOfMax) {
            throw new IllegalArgumentException("numbers.length must be bigger than 5 or equal");
        }

        return Arrays.stream(numbers)
                .boxed()
                .sorted()
                .skip(numbers.length - amountOfMax)
                .collect(Collectors.toList());
    }

    /**
     * Функция принимает массив целых чисел {@code numbers} и возвращает их в виде одной строки,
     * где каждое число разделено запятой. Если массив пустой - вернет пустую строку.
     * Пример:
     * numbers = [1, 53, 63, 23]
     * вернет строку "1,53,63,23"
     *
     * @param numbers массив целых чисел
     * @return строка со всеми числами массива, разделенными запятой
     */
    @Override
    public String getStringOfNumbers(int[] numbers) {
        if (numbers == null) {
            return null;
        }

        String[] res = Arrays.stream(numbers)
                .mapToObj(String::valueOf)
                .toArray(String[]::new);

        return Arrays.toString(res)
                .substring(1, Arrays.toString(res).length() - 1)
                .replaceAll("\\s", "");
    }

    /**
     * Функция принимает список строк {@code strings} и возвращает строку с первыми символами этих строк.
     * Если список пустой - вернуть пустую строку.
     * Функция должна использовать {@code Stream.reduce(..)}
     * Пример:
     * strings = ["serial", "number", "of", "turbo"]
     * вернет строку "snot"
     *
     * @param strings список строк
     * @return строку с первыми символами каждой строки из {@code strings}
     */
    @Override
    public String getFirstCharactersAsString(List<String> strings) {
        if (strings == null) {
            return null;
        }

        return strings.stream()
                .map(s -> s.substring(0, 1))
                .collect(Collectors.joining());
    }

    /**
     * Функция принимает список строк {@code strings} и возвращает {@code Map}, в котором
     * ключ это длинна строки, а значение - список строк с такой длинной строки.
     * Пример:
     * strings = ["Irene", "Wendy", "Seulgi", "Joy", "Yeri", "Red", "Velvet"]
     * вернет Map = {3=[Joy, Red], 4=[Yeri], 5=[Irene, Wendy], 6=[Seulgi, Velvet]}
     *
     * @param strings список строк
     * @return {@code Map}, в котором ключ это длинна строки, а значение - список строк с такой длинной строки
     */
    @Override
    public Map<Integer, List<String>> groupByLength(List<String> strings) {
        return strings.stream()
                .collect(Collectors.groupingBy(String::length));
    }
}
