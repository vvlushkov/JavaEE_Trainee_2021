package interfaces;

/**
 * Methods to make informational strings.
 *
 * <p> This interface using generics to limit type of object
 * about which can be made informational string in {@code infoStringAboutObj}.
 *
 * @param   <T>
 *          Acceptable type of objects in {@code infoStringAboutObj}.
 *
 */
public interface StringMaker<T> {
    /** Field of separator between field and its value. */
    java.lang.String FIELD_VALUE_SEPARATOR = " : ";

    /** Field of separator between elements. */
    java.lang.String ELEMENT_SEPARATOR = ", ";

    /**
     * To add info about such object.
     *
     * @param   str
     *          string to write info in.
     * @param   objToWrite
     *          such object.
     * @return  string with info.
     * @throws  Exception
     *          in case of such exception.
     */
    String infoStringAboutObj(String str, T objToWrite) throws Exception;

    /**
     * To add in such string {@code res} info from string {@code str}
     * that received from {@code clazz}.
     *
     * @param   str
     *          string with new info.
     * @param   res
     *          string in which new info are writing.
     * @param   clazz
     *          {@code Class} of object from which info received.
     * @return  updated {@code res} by new info.
     */
    String infoString(String str, String res, Class<?> clazz);
}
