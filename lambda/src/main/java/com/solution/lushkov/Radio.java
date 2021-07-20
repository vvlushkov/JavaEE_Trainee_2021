package com.solution.lushkov;

import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;

public class Radio implements ElectricityConsumer {
    private static final Logger LOG = LogManager
            .getLogger(Radio.class.getName());

    public void playRadio() {
        LOG.info("Radio is playing");
    }

    @Override
    public void electricityOn() {
        playRadio();
    }
}
