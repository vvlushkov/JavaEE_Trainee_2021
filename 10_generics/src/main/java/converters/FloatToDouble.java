package converters;

import interfaces.Converter;

/**
 * @author Victor Lushkov
 */
public class FloatToDouble implements Converter<Double, Float> {

    /**
     * This method converts object with type Float to object with type Double.
     *
     * @param value - data that need to convert in Float type.
     * @return - the result of convertation in Double type.
     */
    @Override
    public Double get(Float value) {
        return value.doubleValue();
    }
}
