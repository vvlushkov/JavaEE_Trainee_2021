import java.util.HashMap;
import java.util.Map;
//класс с решением
public class IcebergSolution {
    //счетчик квадратов входящих в один айсберг
    int blockCounter = 0;

    //счетчик входов в рекурсию
    int counter1 = 0;

    //счетчик выходов из ркурсии
    int counter2 = 0;

    //счетчик входа в блоки кода при нахождении смежного квадрата (костыль)
    int entrance = 0;

    //коллекция Мап для хранения структуры ключ-значение,
    //в данном случае количество квадратов/количество айсбергов
    Map<Integer, Integer> icebergInfo = new HashMap<>();

    //метод подсчета айсбергов. Как параметры: массив квадратов,
    //массив посещенных ячеек(см. Main.java), индексы рядка и столбика
    public void icebergCounter (int[][] array, boolean[][] checkArray,
                                int row, int column) {
        //перебор массива квадратов
        for (int i = row; i < array.length; i++) {
            for (int j = column; j < array[i].length; j++) {
                //использование массива посещений
                //false - не посещенная ячейка, true - посещенная
                if (checkArray[i][j] == false) {
                    checkArray[i][j] = true;//сразу ставится в фазу "посещенная"
                    if (array[i][j] == 1) { //находит квадрат (единицу)
                        blockCounter++; //итерация кол-ва блоков

                        //костыльный счетчик обнуляется
                        //в начале для последующих рекурсий
                        entrance = 0;

                        //проверка чтобы не было вылета
                        //за пределы диапазона массива. Далее везде
                        //такая же конструкция
                        if ((i - 1) >= 0) {
                            //проверка на наличие квадрата сверху
                            if (array[i - 1][j] == 1) {
                                entrance++; //итерация костыльного счетчика
                                //проверка на посещенность этой ячейки
                                //пишу таким образом для лучшей читабельности
                                //(вместо !checkArray[i - 1][j])
                                if (checkArray[i - 1][j] == false) {
                                    //итерация счетчика "вход в рекурсию"
                                    counter1++;
                                    //вызов этого же метода(рекурсия)
                                    //с передачей индексов нынешнего положения
                                    //в переборе массива
                                    icebergCounter(array, checkArray, i - 1, j);
                                    //итерация счетчика "выход их рекурсии"
                                    counter2++;
                                }
                            }
                        } //этому блоку идентичны последующие три


                        if ((j - 1) >= 0) {
                            //проверка на наличие квадрата слева
                            if (array[i][j - 1] == 1) {
                                entrance++;
                                if (checkArray[i][j - 1] == false) {
                                    counter1++;
                                    icebergCounter(array, checkArray, i, j - 1);
                                    counter2++;
                                }
                            }

                        }


                        if ((j + 1) < array[i].length) {
                            //проверка на наличие квадрата справа
                            if (array[i][j + 1] == 1) {
                                entrance++;
                                if (checkArray[i][j + 1] == false) {
                                    counter1++;
                                    icebergCounter(array, checkArray, i, j + 1);
                                    counter2++;
                                }
                            }
                        }


                        if ((i + 1) < array.length) {
                            //проверка на наличие квадрата снизу
                            if (array[i + 1][j] == 1) {
                                entrance++;
                                if (checkArray[i + 1][j] == false) {
                                    counter1++;
                                    icebergCounter(array, checkArray, i + 1, j);
                                    counter2++;
                                }
                            }
                        }

                        //проверка на соответствие открытых и закрытых рекурсий
                        if (counter1 == counter2) {
                            //в объект коллекции помещается ключ и его значение
                            //как ключ - счетчик blockCounter
                            icebergInfo.put(blockCounter,
                                    //вызов значения ключа
                                    icebergInfo.get(blockCounter) == null
                                    //с помощью тернарного оператора проверяется
                                    //есть ли значение у ключа.
                                    //Если нет, то передается единица
                                    //если есть, то к значению + 1
                                    ? 1 : (icebergInfo.get(blockCounter) + 1));
                        }
                        //костыль
                        //проверяет ситуацию, когда заходы в блок с рекурсие были,
                        //но выходов из нее меньше, чем входов.
                        //такое бывает, когда попадается единица в правый
                        //верхний угол, а под ней ноль
                        if ((entrance != 0) && (counter1 > counter2)) {
                            return; //возврат из метода (завершает рекурсию)
                        }
                    }
                }

                //проверка на то, были ли входы и выходы из рекурсий
                if ((counter1 == 0) && (counter2 == 0)) {
                    //обнуление счетчика квадратов одного айсберга
                    blockCounter = 0;
                } else {
                    //проверка на закрытие всех рекурсий
                    //(то есть завершился ли перебор всех смежных квадратов)
                    if (counter1 == counter2) {
                        //обнуление счетчика квадратов одного айсберга
                        blockCounter = 0;
                    }
                }

            }



        }

    }
    //метод вывода результата
    public void infoOutput() {
        //для вывода элементов Map<K, V> используется Map.Entry<K, V>
        //метод entrySet() возвращает ключи-значения Map<K, V>
        //беребор с помощью for-each
        for (Map.Entry<Integer, Integer> entry : icebergInfo.entrySet()) {

            //пропуск ключей с номером 0 если такие случайно создались
            if (entry.getKey() == 0) {
                continue;
            }
            //вывод содержимого Map<K, V> на экран
            System.out.println("Айсбергов из " + entry.getKey()
                                + " блоков - " + entry.getValue() + " штук.\n");
        }
    }
}