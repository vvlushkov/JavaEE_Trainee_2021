package com.solution.lushkov;

import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;

public class MusicInstrument {
    protected static final Logger LOG = LogManager
            .getLogger(MusicInstrument.class.getName());

    private int tonality;

    public void setTonality(int tonality) {
        this.tonality = tonality;
    }

    public int getTonality() {
        return tonality;
    }

    public void makeSound() {
        LOG.info("~Instrument sound~");
    }

    public void  instrumentSetting(boolean grade) {
        if (grade) {
            tonality++;
        } else {
            tonality--;
        }
    }
}
