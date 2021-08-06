import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import org.junit.Test;
import stringParse.Finder;

import java.io.*;
import java.util.Arrays;

public class FinderTest {
    public static final Logger LOG = LogManager
            .getLogger(FinderTest.class.getName());

    Finder finder = new Finder();

    @Test
    public void dateFinderTest() {
        String booba = "nininin 1927 isfdjnj sds 29 February";
        int[] arr = finder.dateFinder(booba);
        LOG.info(Arrays.toString(arr));
    }

    @Test
    public void parseMonths() {
        String month = "Jan";
        LOG.info(finder.castMonths(month));
    }

    Finder finderB = new Finder();

    @Test
    public void writeExternalTest() {
        Class<?> objC = finderB.getClass();
        System.out.println(objC.getName());
        String fileName = "/Users/Asus/Desktop/Studying/NIX SOLUTIONS STUDYING/serialize/serial.txt";
        try (FileOutputStream fos = new FileOutputStream(fileName);
             ObjectOutput fileOut = new ObjectOutputStream(fos)) {
            finderB.writeExternal(fileOut);
        } catch (IOException e) {
            e.printStackTrace();
        }

        System.out.println(Arrays.toString(finderB.getDateArrays()));
    }

    @Test
    public void readExternalTest() {
        String fileName = "/Users/Asus/Desktop/Studying/NIX SOLUTIONS STUDYING/serialize/serial.txt";
        try (FileInputStream fis = new FileInputStream(fileName);
             ObjectInput fileIn = new ObjectInputStream(fis)) {
            finderB.readExternal(fileIn);
        } catch (IOException e) {
            e.printStackTrace();
        }

        System.out.println(Arrays.toString(finderB.getDateArrays()));
    }
}