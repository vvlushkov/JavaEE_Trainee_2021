package interfaces;

public interface StringMaker<String, T> {
    java.lang.String FIELD_VALUE_SEPARATOR = " : ";

    java.lang.String ELEMENT_SEPARATOR = ", ";

    public String stringMaker(String str, T objToWrite) throws Exception;
}
