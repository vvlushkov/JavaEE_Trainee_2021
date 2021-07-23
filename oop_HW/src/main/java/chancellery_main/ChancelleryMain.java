package chancellery_main;

import chancellery.FeltPen;
import chancellery.Pen;
import chancellery.Pencil;
import chancellery.WritingUtensils;

public class ChancelleryMain {
    //точка входа
    public static void main(String[] args) {
        //кол-во элементов
        final int N = 10;
        WritingUtensils[] notingDevices = new WritingUtensils[N];
        notingDevices = randomDevices(notingDevices);

        StringBuilder stringBuilder = new StringBuilder();
        generateRandomText();
        printArray(notingDevices);
        System.out.println("---------------------");
        writeRandom(stringBuilder,notingDevices);
        printArray(notingDevices);
        System.out.println("---------------------");
        ChancelleryMain.sortByLeftOver(notingDevices);
        printArray(notingDevices);


    }

    /**
     * Метод, который генерирует случайные элементы(ручка, карандаш или фломастер)
     * @param notingDevices - пустой массив
     * @return - массив принадлежностей заполненный случайно
     */
    static WritingUtensils[] randomDevices(WritingUtensils[] notingDevices) {
        for (int i = 0; i < notingDevices.length; i++) {
            int switcher = (int) ((Math.random() * 3) + 1);
            switch (switcher) {
                case 1:
                    notingDevices[i] = new Pen();
                    break;
                case 2:
                    notingDevices[i] = new Pencil();
                    break;
                default:
                    notingDevices[i] = new FeltPen();
            }
        }
        return notingDevices;
    }

    /**
     * 10 кратное написание в созданный StringBuilder рандомной цепочки символов от 3 до 5 символов каждым из устройств,
     * если устройство умеет стирать, то стираем последний символ в каждой итерации.
     *
     * @param stringBuilder - строка в которую будет записываться инфа
     * @param notingDevices - массив принадлежностей
     */
    static void writeRandom(StringBuilder stringBuilder, WritingUtensils[] notingDevices) {
        final int kolOfPaste = 10;
        char[] text = generateRandomText();
        for (int i = 0; i < notingDevices.length; i++) {
            for (int j = 0; j < kolOfPaste; j++) {
                notingDevices[i].write(stringBuilder, text);
                if (notingDevices[i].isClearAble()) {
                    notingDevices[i].clear(stringBuilder);
                }
            }
        }

    }

    /**
     * Метод, который генерирует случайный набор символов размеров от 3 до 5
     *
     * @return  - массив случайно сгенерированных символов
     */
    static char[] generateRandomText() {
        char[] result;
        int size = (int) ((Math.random() * 3) + 3);
        result = new char[size];
        for (int i = 0; i < size; i++) {
            result[i] = ((char) (0 + Math.random() * 127));
        }
        return result;
    }

    /**
     * Метод, который сортирует массив по количеству оставшегося запаса(чернил и т.д)
     *
     * @param notingDevices - массив принадлежностей
     * @return - отсортированный массив принадлежностей
     */
    static WritingUtensils[] sortByLeftOver(WritingUtensils[] notingDevices) {
        for (int i = 0; i < notingDevices.length - 1; i++) {
            for (int j = 0; j < notingDevices.length - i - 1; j++) {
                if (notingDevices[j].getLeftoverPercentage() > notingDevices[j + 1].getLeftoverPercentage()) {
                    WritingUtensils temp = notingDevices[j];
                    notingDevices[j] = notingDevices[j + 1];
                    notingDevices[j + 1] = temp;
                }
            }
        }
        return notingDevices;
    }

    /**
     * Метод, который выводит массив в консоль
     *
     * @param notingDevice - массив принадлежностей
     */
    static void printArray(WritingUtensils[] notingDevice) {
        for (int i = 0; i < notingDevice.length; i++) {
            System.out.println(notingDevice[i].getLeftoverPercentage());
        }
    }
}
