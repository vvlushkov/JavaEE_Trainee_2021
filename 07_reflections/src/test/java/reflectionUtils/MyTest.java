package reflectionUtils;

import com.nixsolutions.ppp.reflection.Ignore;
import com.nixsolutions.ppp.reflection.Info;

public class MyTest {
    @Ignore
    public String biba = "biba";
    @Info
    public String booba = null;
    @Info
    public int zip;

    public MyTest(String one, String two, int three) {
        biba = one;
        booba = two;
        zip = three;
    }
}
