package com.solution.lushkov;

import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;

public class Lamp implements  ElectricityConsumer{
    private static final Logger LOG = LogManager
            .getLogger(Lamp.class.getName());

    public void lightOn() {
        LOG.info("Lamp is lightning");
    }

    @Override
    public void electricityOn() {
        lightOn();
    }
}
