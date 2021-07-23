package chancellery;

public class FeltPen extends WritingUtensils {
    final double perCharFirst = 1;
    final double perCharSecond = 1.09;
    final double perCharThird = 1.21;
    int kolChars;

    //конструктор
    public FeltPen() {
        this.clearAble = true;
        this.leftoverPercentage = 100;
        this.kolChars = 0;
    }

    //записать
    @Override
    public void write(StringBuilder stringBuilder, char[] text) {
        for (int i = 0; i < text.length; i++) {
            if (i <= 20 && this.leftoverPercentage >= perCharFirst) {
                this.leftoverPercentage -= perCharFirst;
                kolChars++;
            } else if (i <= 40 && this.leftoverPercentage >= perCharSecond) {
                this.leftoverPercentage -= perCharSecond;
                kolChars++;
            } else if (this.leftoverPercentage >= perCharThird) {
                this.leftoverPercentage -= perCharThird;
            } else return;
        }
    }

    //стереть
    @Override
    public void clear(StringBuilder stringBuilder) {
        stringBuilder.deleteCharAt(stringBuilder.length() - 2);
    }


}
