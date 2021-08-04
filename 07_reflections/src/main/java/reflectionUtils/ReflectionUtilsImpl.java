package reflectionUtils;

import com.nixsolutions.ppp.reflection.ReflectionUtil;
import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;

import java.lang.annotation.Annotation;
import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Class implements reflection utils.
 *
 * @author Victor Lushkov
 * @version 1.0
 */
public class ReflectionUtilsImpl implements ReflectionUtil {
    /** Field for logging. */
    private static final Logger LOG = LogManager
            .getLogger(ReflectionUtilsImpl.class.getName());

    /**
     * Метод приведения произвольного обьекта к строке в JSON подобном формате:
     * {key_1 : value_1, key_2 : value_2, key_3 : {key_4 : value_4 }, key_5 : value_5}.
     * Обьект может состоять из базовых и составных обьектов/елементов.
     * Базовый обьект - это обьект с одним из типов: java.lang.String, long, int, double, boolean.
     * В результирующей строке он должен быть представлен как key : value, где key - имя поля, value - его значение
     * Составной обьект - это обьект сформированный из базовых или других составных обьектов.
     * Составной обьект в результирующей строке находиться между символам '{' и '}'.
     * В результирующую строку должны попасть только поля обекта помеченные аннотацией @Info.
     *
     * @param   object
     *          object to be cast to a string.
     * @return  string containing information about the object.
     */
    @Override
    public String toString(Object object) {
        Class<?> objClass = object.getClass();
        String annotationName = "@com.nixsolutions.ppp.reflection.Info()";
        List<Field> fields = findFieldsWithAnnotation(objClass, annotationName);
        StringBuilder result = new StringBuilder();
        return (ELEMENT_START + infoStringMaker(fields, object, result)
                                                             + ELEMENT_END);
    }

    /**
     * This method make string with info about fields from list {@code fields}
     * in style like {@code key_1 : value_1, key_2 : value_2 ...}
     * where {@code key} - name of field, {@code value} - its value.
     *
     * @param   fields
     *          list with fields of such object.
     * @param   object
     *          the object from which fields are.
     * @param   res
     *          string with info about fields.
     * @return  result string.
     */
    private String infoStringMaker(List<Field> fields,
                                   Object object,
                                   StringBuilder res) {
        for (Field field : fields) {
            field.setAccessible(true);
            res.append(field.getName()).append(KEY_VALUE_SEPARATOR);
            try {
                res.append(field.get(object)).append(ELEMENT_SEPARATOR)
                                             .append(" ");
            } catch (IllegalAccessException e) {
                LOG.info(e.getMessage());
            }
        }
        //убираю ", " с конца и возвращаю
        return res.substring(0, res.length() - 2);
    }

    /**
     * This method take object and find fields in it
     * with annotation {@code annotationName}
     * and then return list with this fields.
     *
     * @param   objClass
     *          the object in which need to find the fields.
     * @return  list with found fields.
     */
    private List<Field> findFieldsWithAnnotation(Class<?> objClass,
                                                 String annotationName) {
        List<Field> fields = new ArrayList<>();
        for (Field field : objClass.getFields()) {
            if (isBaseElement(field.getType())) {
                for (Annotation annotation : field.getAnnotations()) {
                    //проверка на наличие аннотации
                    if (annotation.toString().equals(annotationName)) {
                        //проверка на то, есть ли уже такое поле в листе
                        if (!(fields.contains(field))) {
                            //добавление поля в лист, если такого еще нет
                            fields.add(field);
                        }
                    }
                }
            }
        }
        return fields;
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
            if (isBaseElement(field.getType())) {
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
        }
        return fields;
    }

    /**
     * Метод проверки идентичности двух произвольных обьектов.
     * Два обьекта считаются идентичными если их соответствующие поля имеют одинаковый тип и значение.
     * Поля обьектов помеченные аннотацие @Ignore не должны влиять на результат сравнения.
     *
     * @param   object1
     *          first object to compare.
     * @param   object2
     *          second object to compare.
     * @return  boolean result of comparing.
     */
    @Override
    public boolean isTheSame(Object object1, Object object2) {
        String annotationName = "@com.nixsolutions.ppp.reflection.Ignore()";
        Class<?> objClass1 = object1.getClass();
        Class<?> objClass2 = object2.getClass();
        List<Field> fields1 = findFieldsWithoutAnnotation(objClass1, annotationName);
        List<Field> fields2 = findFieldsWithoutAnnotation(objClass2, annotationName);
        return compareTwoObjects(fields1, fields2, object1, object2);
    }

    /**
     * Method return false if fields from lists have same name
     * but different types and values.
     *
     * @param   fields1
     *          first list of fields.
     * @param   fields2
     *          second list of fields.
     * @param   object1
     *          the object from which first list of fields from.
     * @param   object2
     *          the object from which second list of fields from.
     * @return  true if the compare is passed and false if not.
     */
    private boolean compareTwoObjects(List<Field> fields1, List<Field> fields2,
                                      Object object1, Object object2) {
        for (Field field1 : fields1) {
            for (Field field2 : fields2) {
                if (field2.getName().equals(field1.getName())) {
                    try {
                        //одновременная сверка типа и значения полей
                        //вернет false если хоть что-то в циклах не совпадет
                        if (!((field2.getType()
                                .equals(field1.getType()))
                                && field2.get(object2)
                                .equals(field1.get(object1)))) {
                            return false;
                        }
                    } catch (IllegalAccessException e) {
                        LOG.info(e.getMessage());
                    }
                }
            }
        }
        //возвращает true если циклы проганяются без вылетов IF-а
        return true;
    }

}
