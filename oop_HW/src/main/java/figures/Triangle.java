package figures;

public class Triangle extends Figures {
    //кол-во углов
    final int numberOfAngles = 3;

    //конструктор
    public Triangle() {
        this.coordinatesX = new double[numberOfAngles];
        this.coordinatesY = new double[numberOfAngles];
    }

    //нахождение площади
    @Override
    public double getSquare() {
        return 0.5 * Math.abs(((this.coordinatesX[1] - this.coordinatesX[0]) *
                (this.coordinatesY[2] - this.coordinatesY[0])) -
                ((this.coordinatesX[2] - this.coordinatesX[0]) *
                        (this.coordinatesY[1] - this.coordinatesY[0])));
    }

}
