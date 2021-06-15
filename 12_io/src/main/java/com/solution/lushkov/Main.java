package com.solution.lushkov;

import org.apache.log4j.Logger;
import org.apache.log4j.LogManager;

import java.io.File;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Arrays;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Main {
    private static final Logger LOG = LogManager
            .getLogger(Main.class.getName());

    public static void main(String[] args) {
        LOG.info("IO test");
        IOUtilsRealization ioUtil = new IOUtilsRealization();

//        LOG.info("gzip:");
//        String file = "(path)";
//        String folder = "C:\\Users\\Asus\\Desktop\\test.gzip";
//        LOG.info(ioUtil.gzip(file, folder));

        LOG.info("searchText:");
        String file = "/Users/Asus/Desktop/sd.txt";
        String mark = "wood";
        LOG.info("Lines with mark: " + ioUtil.searchText(file, mark) + "\n");

        LOG.info("search IO:");
        File fold = new File("/Users/Asus/Desktop/third");
        String ext = ".txt";
        String[] arr = ioUtil.search(fold, ext);
        if (arr != null) {
            for (String str : arr) {
                System.out.println(str);
            }
        }

        System.out.println("\n");
        LOG.info("NIO test");
        NIOUtilsRealization nioUtil = new NIOUtilsRealization();

        LOG.info("search NIO:");
        Path path = Paths.get("/Users/Asus/Desktop/third");
        String extension = ".txt";
        String[] array = nioUtil.search(path, extension);
        if (array != null) {
            for (String str : array) {
                System.out.println(str);
            }
        }
    }
}
