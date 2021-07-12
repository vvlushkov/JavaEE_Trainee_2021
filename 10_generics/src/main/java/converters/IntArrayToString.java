package converters;

import interfaces.Converter;

/**
 * @author Victor Lushkov
 */
public class IntArrayToString implements Converter<String, Integer[]> {

    /**
     * This method converts array with type Integer[]
     * to object with type String.
     * All elements from array concat to string separated by space.
     *
     * @param value - array that need to convert.
     * @return - string - the result of convertation.
     */
    @Override
    public String get(Integer[] value) {
        //использование стрингбилдера из-за частого изменения строки
        StringBuilder str = new StringBuilder();

        //перебор массива
        for (Integer num : value) {
            //добавление числе в строку
            str.append(num).append(" ");
        }

        return str.toString().trim();
    }
}
