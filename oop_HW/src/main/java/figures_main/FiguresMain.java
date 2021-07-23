package figures_main;

import figures.Circle;
import figures.Figures;
import figures.Square;
import figures.Triangle;
import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;

/**
 * @author Victor Lushkov
 */
public class FiguresMain {
    private static final Logger LOG = LogManager
            .getLogger(FiguresMain.class.getName());

    //Точка входа
    public static void main(String[] args) {
        //переменная для кол-ва фигур
        final int N = 10;
        //создание и заполнение массива
        Figures[] figures = new Figures[N];
        figures = FiguresMain.randomFigures(figures, N);
        //вывод массива
        printArray(figures);
        LOG.info("-------------------------");
        //сортировка по площади
        figures = FiguresMain.sortBySquare(figures);
        printArray(figures);
        LOG.info("-------------------------");
        //изменение размера
        changeSize(figures);
        printArray(figures);


    }

    /**
     * Метод, который заполняет случайными фигурами массив
     *
     * @param figures - массив фигур
     * @param size - размер массива
     * @return - возращает заполненный массив
     */
    static Figures[] randomFigures(Figures[] figures, int size) {
        //кол-во координат в фигурах
        final int triangleKol = 3;
        final int squareKol = 4;
        final int circleKol = 2;
        //заполнение массива случайными фигурами
        for (int i = 0; i < size; i++) {
            int switcher = (int) ((Math.random() * 3) + 1);
            switch (switcher) {
                case 1:
                    figures[i] = new Triangle();
                    //присвоение случайных координат
                    figures[i] = FiguresMain.randomCoordinates(figures[i], triangleKol);
                    break;
                case 2:
                    figures[i] = new Square();
                    figures[i] = FiguresMain.randomCoordinates(figures[i], squareKol);
                    break;
                default:
                    figures[i] = new Circle();
                    figures[i] = FiguresMain.randomCoordinates(figures[i], circleKol);
            }
        }
        return figures;
    }

    /**
     * Метод, который случайно заполняет все координаты фигуры
     *
     * @param figure - фигура, координаты которой заполняются
     * @param size - количество координат
     * @return - возвращает фигуру со случайными координатами
     */
    static Figures randomCoordinates(Figures figure, int size) {
        for (int i = 0; i < size; i++) {
            figure.setCoordinate(i, Math.random(), Math.random());
        }
        return figure;
    }

    /**
     * Метод, который сортирует массив фигур по возрастанию в зависимости от площи фигуры
     *
     * @param figures - массив фигур
     * @return - массив фигур отсортированный
     */
    static Figures[] sortBySquare(Figures[] figures) {
        for (int i = 0; i < figures.length - 1; i++) {
            for (int j = 0; j < figures.length - i - 1; j++) {
                if (figures[j].getSquare() > figures[j + 1].getSquare()) {
                    Figures temp = figures[j];
                    figures[j] = figures[j + 1];
                    figures[j + 1] = temp;
                }
            }
        }
        return figures;
    }

    /**
     * Метод, который выводит массив в консоль
     *
     * @param figures - массив фигур
     */
    static void printArray(Figures[] figures) {
        for (Figures figure : figures) {
            LOG.info(figure.getSquare());
        }
    }

    /**
     * Метод, который масштабирует размеры фигуры в зависимости от случайног коэффициента
     *
     * @param figures - массив фигур
     */
    static void changeSize(Figures[] figures) {
        final int minNumber = 0;
        final int maxNumber = 1000;
        double coefficient = (Math.random() * (maxNumber - minNumber + 1)) + minNumber;
        for (Figures figure : figures) {
            for (int j = 0; j < figure.getCoordinatesX().length; j++) {
                figure.setCoordinate(j, figure.getCoordinatesX()[j] * coefficient,
                        figure.getCoordinatesY()[j] * coefficient);
            }
        }
    }
}

