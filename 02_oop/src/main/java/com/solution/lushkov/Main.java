package com.solution.lushkov;

import org.apache.log4j.Logger;
import org.apache.log4j.LogManager;

public class Main {
    private static final Logger LOG = LogManager
            .getLogger(Main.class.getName());

    public static void main(String[] args) {
        LOG.info("~Some info about music instruments~");
    }
}
