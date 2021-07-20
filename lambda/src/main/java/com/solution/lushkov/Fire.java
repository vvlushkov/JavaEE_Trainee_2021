package com.solution.lushkov;

import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;

public class Fire implements  ElectricityConsumer{
    private static final Logger LOG = LogManager
            .getLogger(Fire.class.getName());

    @Override
    public void electricityOn() {
        LOG.info("Fire alarm!");
    }
}
