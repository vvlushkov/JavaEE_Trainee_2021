package stringInfoMaker;

import annotations.NorForWrite;
import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;

import java.lang.annotation.Annotation;
import java.lang.reflect.Field;

public class Tester {
    @NorForWrite
    public int baba = 34;
    public String str = "sfsad";
    public Object object;
//    @NorForWrite
//    public Logger LOG = LogManager
//            .getLogger(StringMakerFromObject.class.getName());

    public static void main(String[] args) {
        Tester tester = new Tester();
        Class<?> objClass = tester.getClass();
        Field[] fields = objClass.getFields();
        for (Field field : fields) {
            field.setAccessible(true);
            try {
                System.out.println(field.getType() + " - " + field.get(tester));
            } catch (IllegalAccessException e) {
                e.printStackTrace();
            }
            for (Annotation annot : field.getAnnotations()) {
                System.out.println(annot);
            }
        }
        System.out.println(fields.length);



    }
}
