package chancellery;

public abstract class WritingUtensils {

    double leftoverPercentage;
    boolean clearAble;

    //возвращает значение
    public double getLeftoverPercentage() {
        return leftoverPercentage;
    }

    //может ли стирать
    public boolean isClearAble() {
        return clearAble;
    }

    //записать
    public abstract void write(StringBuilder stringBuilder, char[] text);

    //стереть
    public abstract void clear(StringBuilder stringBuilder);
}
