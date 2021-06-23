package com.solution.lushkov;

public class AcousticGuitar extends Guitars {

    private int strings = 6;

    private int bassStrings = 3;

    private int tonality;

    public void setStrings(int strings) {
        this.strings = strings;
    }

    public int getStrings() {
        return strings;
    }

    public void setBassStrings(int bassStrings) {
        this.bassStrings = bassStrings;
    }


    public int getBassStrings() {
        return bassStrings;
    }

    @Override
    public void setTonality(int tonality) {
        this.tonality = tonality;
    }

    @Override
    public int getTonality() {
        return tonality;
    }

    @Override
    public void makeSound() {
        LOG.info("~Acoustic guitar sound~");
    }

    @Override
    public void usingCapodaster(int guitarFret) {
        this.tonality += guitarFret;
    }
}
