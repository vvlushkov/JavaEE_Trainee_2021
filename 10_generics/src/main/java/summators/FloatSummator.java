package summators;

import interfaces.Summator;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author Victor Lushkov
 */
public class FloatSummator implements Summator<Float> {

    /**
     * This method sum up elements of List<Float>
     * for every key from Map<String, List<Float>>
     * and return new Map<String, Double>.
     *
     * @param data - Map<String, List<Float>>
     *             with data that need to process.
     * @return - Map<String, Double> - result of processing data.
     */
    @Override
    public Map<String, Double> sum(Map<String, List<Float>> data) {
        //создание объекта интерфейса Map типа HashMap
        Map<String, Double> newMap = new HashMap<>();

        //перебор дерева с помощью интерфейса Map.Entry
        for(Map.Entry<String, List<Float>> entry : data.entrySet()) {
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
            sum += (float)array[i];
        }

        return sum;
    }
}
