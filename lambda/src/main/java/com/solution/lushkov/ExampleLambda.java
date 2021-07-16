package com.solution.lushkov;

import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;

public class ExampleLambda {
    private static final Logger LOG = LogManager
            .getLogger(ExampleLambda.class.getName());

    public static void main(String[] args) {
        Switcher switcher = new Switcher();

        Lamp lamp = new Lamp();
        Radio radio = new Radio();

        switcher.addElectricityConsumer(lamp);
        switcher.addElectricityConsumer(radio);
//        switcher.addElectricityConsumer(new Fire());

//        switcher.addElectricityConsumer(new ElectricityConsumer() {
//            @Override
//            public void electricityOn() {
//                LOG.info("Fire alarm!");
//            }
//        });

//        switcher.addElectricityConsumer(() -> LOG.info("Fire alarm!"));

//        switcher.addElectricityConsumer(() -> ExampleLambda.fire());

        switcher.addElectricityConsumer(ExampleLambda::fire);

        switcher.switchOn();
    }

    public static void fire() {
        LOG.info("Another fire alarm!");
    }
}
