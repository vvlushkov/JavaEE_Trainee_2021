package reflectionUtils;

import com.nixsolutions.ppp.reflection.Ignore;
import com.nixsolutions.ppp.reflection.Info;
import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import org.junit.Test;

import java.lang.annotation.Annotation;
import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import static org.junit.Assert.*;

public class ReflectionUtilsImplTest {
    private static final Logger LOG = LogManager
            .getLogger(ReflectionUtilsImplTest.class.getName());

    @Ignore
    public String biba = "biba";
    @Info
    public String booba = null;
    @Info
    public int zip;

    public static void main(String[] args) {
        ReflectionUtilsImplTest refT = new ReflectionUtilsImplTest();

        Class<?> objClass = refT.getClass();

        List<Field> fields = new ArrayList<>();
        for (Field field : objClass.getFields()) {
            for (Annotation annotation : field.getAnnotations()) {
                if (annotation.toString().equals("@com.nixsolutions.ppp.reflection.Info()")) {
                    fields.add(field);
                }
            }
        }

        for (Field field : fields) {
            System.out.println(field);
        }

    }



    @Test
    public void testToString() {
        ReflectionUtilsImplTest refT = new ReflectionUtilsImplTest();

        ReflectionUtilsImpl ref = new ReflectionUtilsImpl();
        LOG.info(ref.toString(refT));
    }

    @Test
    public void isTheSame() {
        MyTest refT1 = new MyTest("boom1", "boom2", 4);
        MyTest refT2 = new MyTest("boomBoom", "boom2", 4);

        ReflectionUtilsImpl ref = new ReflectionUtilsImpl();
        LOG.info(ref.isTheSame(refT1, refT2));
    }
}