package interfaces;

/**
 * @author Victor Lushkov
 *
 * Interface with method to convert object with one type
 * to object with other type.
 */
public interface Converter<T, I> {

    /**
     * This method converts object with type I to object with type T.
     *
     * @param value - data that need to convert.
     * @return - the result of convertation.
     */
    T get(I value);
}

