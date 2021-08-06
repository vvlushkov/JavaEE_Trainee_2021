package dateCompare;

import org.junit.Test;

import static org.junit.Assert.*;

public class DateCompareByThreadsTest {

    @Test
    public void run() {
        Class<?> date1 = DateCompareJava7.class;
        Class<?> date2 = DateCompareJava8.class;

        DateCompareByThreads[] dateThreads = new DateCompareByThreads[2];
        dateThreads[0] = new DateCompareByThreads(date1, 2019, 2, 30);
        dateThreads[0].start();
        dateThreads[1] = new DateCompareByThreads(date2, 2019, 2, 25);
        dateThreads[1].start();
        try {
            for (DateCompareByThreads dateTh : dateThreads) {
                dateTh.join();
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

    }
}