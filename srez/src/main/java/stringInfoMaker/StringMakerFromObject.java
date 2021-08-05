package stringInfoMaker;

import interfaces.StringMaker;
import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;

import java.lang.annotation.Annotation;
import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class StringMakerFromObject implements StringMaker<String, Object> {
    /**
     * Field for logging.
     */
    private static final Logger LOG = LogManager
            .getLogger(StringMakerFromObject.class.getName());







    /**
     *
     *
     * @param str
     * @param objToWrite
     * @return
     * @throws Exception
     */
    @Override
    public String stringMaker(String str, Object objToWrite) throws Exception {
        StringBuilder res = new StringBuilder(str);
        res.append("\n").append("Object of ")
                        .append(objToWrite.getClass()).append(" information:\n");
        Class<?> objClass = objToWrite.getClass();
        String annotationToIgnore = "@annotations.NorForWrite()";
        List<Field> fields = findFieldsWithoutAnnotation(objClass, annotationToIgnore);
        if (fields.size() == 0) {
            res.append("No info about fields.");
            throw new Exception("No fields in object to write.");
        } else {
            fieldToStrAppender(fields, res, objToWrite);
        }
        return  res.toString();
    }




    /**
     *
     * @param fields
     * @param res
     * @param objToWrite
     */
    private void fieldToStrAppender(List<Field> fields,
                                    StringBuilder res,
                                    Object objToWrite) {
        for (Field field : fields) {
            res.append(field.getType()).append(" ").append(field.getName())
                    .append(FIELD_VALUE_SEPARATOR);
            try {
                res.append(field.get(objToWrite)).append(ELEMENT_SEPARATOR);
            } catch (IllegalAccessException e) {
                LOG.info(e.getMessage());
            }
        }
        res.delete(res.length() - 2, res.length());
    }

    /**
     * This method take object and find fields in it
     * without annotation {@code annotationName}
     * and then return list with this fields.
     *
     * @param   objClass
     *          the object in which need to find the fields.
     * @return  list with found fields without such annotation.
     */
    private List<Field> findFieldsWithoutAnnotation(Class<?> objClass,
                                                    String annotationName) {
        List<Field> fields = new ArrayList<>();
        for (Field field : objClass.getFields()) {
            List<Annotation> annotations = Arrays.asList(
                    field.getAnnotations());
            //сбор всех аннотаций в виде стрингов в массив
            ArrayList<String> annotationsStr = annotations.stream()
                    .map(Annotation::toString)
                    .collect(Collectors.toCollection(ArrayList::new));
            //проверка на наличие аннотации
            if (!(annotationsStr.contains(annotationName))) {
                //если нет ее, то добавляем в лист полей
                fields.add(field);
            }
        }
        return fields;
    }





}
