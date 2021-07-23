package chancellery;

public class Pen extends WritingUtensils {
    final double perChar = 1.15;

    //конструктор
    public Pen() {
        this.clearAble = false;
        this.leftoverPercentage = 100;
    }

    //записать
    @Override
    public void write(StringBuilder stringBuilder, char[] text) {
        for (int i = 0; i < text.length; i++) {
            if (this.leftoverPercentage - perChar >= 0) {
                stringBuilder.append(text[i]);
                this.leftoverPercentage -= perChar;
            } else return;
        }
    }

    //стереть
    @Override
    public void clear(StringBuilder stringBuilder) {
        return;
    }
}
