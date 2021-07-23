package figures;

public abstract class Figures {
    //Массивы абсцис и ординат
    double[] coordinatesX;
    double[] coordinatesY;

    //получить массив координат X
    public double[] getCoordinatesX() {
        return coordinatesX;
    }
    //получить массив координат Y
    public double[] getCoordinatesY() {
        return coordinatesY;
    }

    /**
     * Метод, который находит площадь фигуры
     *
     * @return - площадь в ед. кв
     */
    public abstract double getSquare();

    /**
     * Метод, который позволяет изменить Координату фигуры
     *
     * @param coordinateIndex - номер координаты, которую нужно заменить
     * @param x - координата x
     * @param y - координата y
     */
    public void setCoordinate(int coordinateIndex, double x, double y) {
        this.coordinatesX[coordinateIndex] = x;
        this.coordinatesY[coordinateIndex] = y;

    }
}
