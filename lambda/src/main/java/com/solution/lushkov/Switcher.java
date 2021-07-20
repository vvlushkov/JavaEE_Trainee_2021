package com.solution.lushkov;

import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;

import java.util.ArrayList;
import java.util.List;

public class Switcher {
    private static final Logger LOG = LogManager
            .getLogger(Switcher.class.getName());

    public List<ElectricityConsumer> consumers = new ArrayList<>();

    public void addElectricityConsumer(ElectricityConsumer consumer) {
        consumers.add(consumer);
    }

    public void switchOn() {
        LOG.info("Light switcher ON");
//        if (consumer != null) {
//            consumer.electricityOn();
//        }
        for (ElectricityConsumer consumer : consumers) {
            consumer.electricityOn();
        }
    }
}
