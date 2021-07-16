package interfaces;

import java.util.List;
import java.util.Map;

/**
 * @author Victor Lushkov
 *
 * Interface with method to convert data in Map.
 */
public interface Summator<T extends Number> {

    /**
     * This method sum up elements in List<T> for every key in {data}
     * and return new Map.
     *
     * @param data - Map with data that need to process.
     * @return - result of processing data.
     */
    Map<String, Double> sum(Map<String, List<T>> data);
}
