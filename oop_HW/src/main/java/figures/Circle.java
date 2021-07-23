package figures;

public class Circle extends Figures {
    //кол-во координат
    final int numberOfCoordinates = 2;

    //конструктор
    public Circle() {
        this.coordinatesX = new double[numberOfCoordinates];
        this.coordinatesY = new double[numberOfCoordinates];
    }

    //нахождение площади
    @Override
    public double getSquare() {
        final double pi = 3.14;
        double varX = Math.pow((this.coordinatesX[1] - this.coordinatesX[0]), 2);
        double varY = Math.pow((this.coordinatesY[1] - this.coordinatesY[0]), 2);
        double radius = Math.sqrt(varX + varY);
        return Math.pow(radius, 2) * pi;
    }

}
