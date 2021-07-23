package chancellery;

public class Pencil extends WritingUtensils {
    final double perChar = 0.95;
    int kolChars;

    //конструктор
    public Pencil() {
        this.clearAble = true;
        this.leftoverPercentage = 100;
        this.kolChars = 0;
    }

    //записать
    @Override
    public void write(StringBuilder stringBuilder, char[] text) {
        for (int i = 0; i < text.length; i++) {
            if (this.leftoverPercentage - perChar >= 0) {
                stringBuilder.append(text[i]);
                this.leftoverPercentage -= perChar;
                kolChars++;
                if (kolChars % 20 == 0) {
                    this.leftoverPercentage -= 3;
                }
            } else return;
        }
    }

    //стереть
    @Override
    public void clear(StringBuilder stringBuilder) {
        stringBuilder.deleteCharAt(stringBuilder.length() - 2);
    }
}
