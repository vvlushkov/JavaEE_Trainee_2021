package summators;

import interfaces.Summator;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author Victor Lushkov
 */
public class BigDecimalSummator implements Summator<BigDecimal> {

    /**
     * This method sum up elements of List<BigDecimal>
     * for every key from Map<String, List<BigDecimal>>
     * and return new Map<String, Double>.
     *
     * @param data - Map<String, List<BigDecimal>>
     *             with data that need to process.
     * @return - Map<String, Double> - result of processing data.
     */
    @Override
    public Map<String, Double> sum(Map<String, List<BigDecimal>> data) {
        //создание объекта интерфейса Map типа HashMap
        Map<String, Double> newMap = new HashMap<>();

        //перебор дерева с помощью интерфейса Map.Entry
        for(Map.Entry<String, List<BigDecimal>> entry : data.entrySet()) {
            //помещение информации в новое дерево
            //для подсчета суммы эдементов используется метод calculateSumOfElements
            newMap.put(entry.getKey(), calculateSumOfElements(entry.getValue().toArray()));
        }

        return newMap;
    }

    /**
     * This method sum up elements in array.
     *
     * @param array - array which elements need to sum up
     * @return - sum in double type
     */
    private double calculateSumOfElements(Object[] array) {
        //переменная суммы
        double sum = 0;

        //перебор массива
        for (int i = 0; i < array.length; i++) {
            //присвоение переменной значений
            sum += ((BigDecimal)array[i]).doubleValue();
        }

        return sum;
    }
}
