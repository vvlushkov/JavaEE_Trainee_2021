package com.solution.lushkov;

import com.nixsolutions.ppp.strings.StringUtils;

import java.util.Arrays;
import java.util.regex.Pattern;
import java.util.regex.Matcher;
import org.apache.log4j.Logger;
import org.apache.log4j.LogManager;

public class StringUtilsRealization implements StringUtils {
    private static final Logger LOG = LogManager
            .getLogger(Main.class.getName());


    /**
     * Функция проверяет, что ip соответствует формату NNN.NNN.NNN.NNN
     * То есть, что ip состоит из четырёх чисел разделённых точкой
     * Каждое фракция должна быть в промежутке от 0 до 255
     * В начале {@code ip} и конце возможно будут пробелы.
     * IP фракции от 0 до 127 преобразуются в байт от 0 до 127
     * IP фракции от 128 до 255 преобразуются в байт от -128 до -1
     *
     * Пример: "127.0.0.255"; {127, 0, 0, -1}
     *
     * @param ip - IP адрес
     * @return {@code ip} в виде массива {@code byte} или, в случае ошибки,
     *         {@code null}.
     */
    @Override
    public byte[] ip2Bytes(String ip) {
        //Метод trim() убирает пробелы сзади и спереди строки
        String ipStr = ip.trim();
        String regex = "([0-9]{1,3}\\.){3}[0-9]{1,3}";
        Pattern pattern = Pattern.compile(regex);
        Matcher matcher = pattern.matcher(ipStr);
        //Проверка шаблона рег. выражения
        if (matcher.matches() == false) {
            LOG.info("Wrong format!");
            return null;
        }
        //массив для кранения байтовых чисел
        byte[] byteArray = new byte[4];
        //массив строк (разделенная заданная строка)
        String[] strArray = ipStr.split("\\.");
        for (int i = 0; i < strArray.length; i++) {
            //проверка по условию 0-255
            if ((Integer.parseInt(strArray[i]) > 255)
                    || (Integer.parseInt(strArray[i]) < 0)) {
                LOG.info("Wrong format! You need parts in diapason [0-255]!");
                return null;
            } else {
                                //перевод из строки в байт
                byteArray[i] = (byte)Integer.parseInt(strArray[i]);
            }
        }
        return byteArray;
    }


    /**
     * Функция конвертирует ip в NNN.NNN.NNN.NNN формат,
     * где каждая группа разделённая точкой будет состоять из трёх чисел
     * Перед конвертацией необходимо удостовериться, что ip адрес, который передан в метод имеет правильный формат
     * То есть, что ip состоит из четырёх чисел разделённых точкой
     * Каждое число должно быть в промежутке от 0 до 255
     * В начале {@code ip} и конце возможно будут пробелы.
     * Пример конвертации: "127.0.0.1"; "127.000.000.001"
     *
     * @param ip - Ip адрес
     * @return {@code ip} в виде строки формата NNN.NNN.NNN.NNN
     *         {@code null} в случае, если переданный ip адрес имеет неверный формат
     */
    @Override
    public String convertIp(String ip) {
        //Метод trim() убирает пробелы сзади и спереди строки
        String ipStr = ip.trim();
        String regex = "([0-9]{1,3}\\.){3}[0-9]{1,3}";
        Pattern pattern = Pattern.compile(regex);
        Matcher matcher = pattern.matcher(ipStr);
        //Проверка шаблона рег. выражения
        if (matcher.matches() == false) {
            LOG.info("Wrong format!");
            return null;
        }
        //массив строк (разделенная заданная строка)
        String[] strArray = ipStr.split("\\.");
        for (int i = 0; i < strArray.length; i++) {
            //проверка по условию 0-255
            if ((Integer.parseInt(strArray[i]) > 255)
                     || (Integer.parseInt(strArray[i]) < 0)) {
                LOG.info("Wrong format! " +
                        "You need parts in diapason [0-255]!");
                return null;
            } else {
                //в зависимости от количества цифр в сегменте прибавляются нули
                if (strArray[i].length() < 3) {
                    //с помощью объекта StringBuilder и метода insert()
                    //вставляются нули в сегменты
                    strArray[i] = ((new StringBuilder(strArray[i]))
                            .insert(0, strArray[i].length() == 1 ? "00" : "0"))
                            .toString();
                }
            }
        }
                //вывод в виде строки
        return Arrays.toString(strArray);
    }

    /**
     * Функция Создает строку всех символов английского алфавита "A..Z", где
     * четные буквы upper case, а нечетные в low case.
     *
     * @return StringBuilder с алфавитом.
     */
    @Override
    public StringBuilder alphabet() {
        StringBuilder stringBuilder = new StringBuilder();
        for (int i = 0; i < 26; i++) {
            //Чары из таблицы аски прибавляются к строке
            // в зависимости от четности
            if ((i % 2) == 0) {
                stringBuilder.append((char)(i + 97));
            } else {
                stringBuilder.append((char)(i + 65));
            }
        }
        return stringBuilder;
    }

    /**
     * НЕ ДОДЕЛАНА!
     *
     *
     * Функция преобразует url в массив составляющих <br>
     * ;схема;://[;логин;:;пароль;@;хост;:;порт;/;путь;?;параметры;#;якорь;
     * Во входной строке значение любая состовлящая может отсутствовать, в этом случае соответсвующее значение в выходном
     * массиве должно быть {@code null}
     * схема://логин:пароль@хост:порт/путь?параметры#якорь
     * @param uri - строка содержащая идентификатор ресурса
     * @return массив строк, в которой:<br>
     *         [0] - схема<br>
     *         [1] - логин<br>
     *         [2] - пароль<br>
     *         [3] - хост<br>
     *         [4] - port<br>
     *         [5] - путь<br>
     *         [6] - параметры<br>
     *         [7] - якорь<br>
     * если нет какой-либо части, то в данном поле массива возвращаем {@code null}
     * Например, для url: "ftp://1.2.3.4:25/pass0/pass1/pass2?a=1;b=2#anchor" ;
     * возвращаемое значение: ["ftp", null, null, "1.2.3.4", "25", "pass0/pass1/pass2", "a=1&amp;b=2", "anchor"]
     */
    @Override
    public String[] uri2Array(String uri) {
        //массив для инфы
        String[] urlSchema = new String[8];
        String regex = "\\b(https?|ftp|file)://[-a-zA-Z0-9+&@#/%?=~_|!:,.;]*" +
                "[-a-zA-Z0-9+&@#/%=~_|]";
        Pattern pattern = Pattern.compile(regex);
        Matcher matcher = pattern.matcher(uri);
        if (matcher.matches() == false) {
            LOG.info("Wrong format!");
            return null;
        }
        //поиск "схемы"
        if (uri.indexOf("://") < 1 ) {
            urlSchema[0] = null;
        } else {
            urlSchema[0] = uri.substring(0, uri.indexOf("://"));
        }

        //дальше хз

        return new String[0];
    }


    /**
     * Функция преобразует словосочетание в CamelCase независимо от оригинального регистра букв
     * Исключением служит первая буква. Её регистр нужно оставить нетронутым
     * Разделителем слов служат пробелы и запятые
     * Вначале и вконце строки возможны дополнительные пробелы
     * Пример: "HeLlO jAvA, WoRlD"; "HelloJavaWorld"
     *
     * @param str - обычная строка
     * @return - строка в CamelCase
     * или пустая строка если передан null или пустая строка
     */
    @Override
    public String toCamelCase(String str) {
        //проверка строки на пустоту
        if ((str == null) || (str.equals(""))) {
            LOG.info("Wrong format!");
            return null;
        }
        StringBuilder stringBuilder = new StringBuilder();
        //массив строк (разделенная заданная строка)
        String[] strArray = str.split("[ ,]+");
        for (int i = 0; i < strArray.length; i++) {
            //при первой итерации понижаются все кроме первой
            if (i == 0) {
                strArray[i] = strArray[i].substring(0, 1)
                            + strArray[i].substring(1).toLowerCase();
                //при следющих итерациях понижаются все
            } else {
                strArray[i] = strArray[i].substring(0, 1).toUpperCase()
                            + strArray[i].substring(1).toLowerCase();
            }
            stringBuilder.append(strArray[i]);
        }

        return stringBuilder.toString();
    }


    /**
     * Функция преобразует CamelCase строку в словосочетания в нижнем регистре
     * Исключением служит первая буква. Её регистр нужно оставить нетронутым
     * Пример: "HelloJavaWorld"; "Hello java world"
     *
     * @param camelStr - CamelCase строка
     * @return обычная строка
     * или пустая строка если передан null или пустая строка
     */
    @Override
    public String fromCamelCase(String camelStr) {
        //проверка строки на пустоту
        if ((camelStr != null) && (!camelStr.equals(""))) {
            StringBuilder temp = new StringBuilder();
            //счетчик добавлений сегментов в строку
            int k = 0;
            for (int i = 1; i < camelStr.length(); i++) {
                //поиск символов с Верхним регистром
                if ((camelStr.charAt(i) >= '\u0041')
                        && (camelStr.charAt(i) <= '\u005A')) {
                    //первый сегмент добавляется без изменений
                    if (k == 0) {
                        temp.append(camelStr.substring(0, i) + " ");
                        //удаление пройденного сегмента
                        camelStr = camelStr.substring(i);
                    } else {
                        temp.append(camelStr.substring(0, i)
                                .toLowerCase() + " ");
                        camelStr = camelStr.substring(i);
                    }
                    k++;
                    //обнуление счетчика цикла, так как сокращается длина строки
                    // из-за очистки пройденных сегментов
                    i = 0;
                }
            }
            temp.append(camelStr.toLowerCase());
            return temp.toString();
        } else {
            LOG.info("Wrong format!");
            return null;
        }

    }
}
