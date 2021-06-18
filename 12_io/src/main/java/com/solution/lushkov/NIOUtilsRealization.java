package com.solution.lushkov;

import com.nixsolutions.ppp.io.NIOUtils;

import org.apache.log4j.Logger;
import org.apache.log4j.LogManager;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.List;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

/**
 * @author Victor Lushkov
 */
public class NIOUtilsRealization implements NIOUtils {
    private static final Logger LOG = LogManager
            .getLogger(IOUtilsRealization.class.getName());

    /**
     * Функция ищет текст в файле, согласно следующим правилам. В файле
     * расположен текст и числа типа Integer(положительные и отрицательные)
     * разделитель пробел. Между разделителями не более 10 байт.<br>
     * Если функция считывает число N, она должна начать следующее считываение
     * через N байт, относительно текущей позиции. Для положительных чисел
     * позицию прибавляем, для отрицательных вычитаем. Если функция после
     * очередного числа считывает текст, то она возвращает это значение. Пример:
     * <br>
     * Содержимое файла: <br>
     * 1 7 -2 a -2 <br>
     * searchText(path, 4) вернет "а"<br>
     *
     * @param file
     *            путь к файлу
     * @param offset
     *            начальный сдвиг в файле
     * @return найденный в файле текст.
     * @throws IllegalArgumentException
     *             если {@code file} не существует
     */
    @Override
    public String searchText(Path file, int offset)
            throws IllegalArgumentException {
        try {
            // Создаём массив строк.
            String[] lines = Files.readAllLines(file,
                    StandardCharsets.UTF_8).toArray(new String[0]);
            // Инициализируем переменную хранящую весь текст из файла.
            StringBuilder allFileText = new StringBuilder();
            // Записыываем все строки в один файл.
            for (int i = 0; i < lines.length; i++) {
                allFileText.append(lines[i]).append(" ");
            }
            // Создваём массив значаений найденных во всем тексте из файла.
            String[] elements = allFileText.toString().split(" ");
            return searchTextAt(elements, offset);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    /**
     * Функция ищет все файлы с расширением {@code ext} в {@code folder}
     * директории и если находит вложенные директории то
     * вызывает сама себя внутри этой директории.
     *
     * @param values массив найденных значений
     * @param offset позиция
     * @return найденное по позиции значение.
     */
    private String searchTextAt(String [] values, int offset) {     //Нет описания работы метода
        Pattern pattern = Pattern.compile("[0-9,-]+");
        String found;
        if(pattern.matcher(values[offset]).matches()){
            found = searchTextAt(values,
                    Integer.parseInt(values[offset]) + offset);
        }
        else {
            return values[offset];
        }
        return found;
    }

    /**
     * Функция ищет все файлы с расширением {@code ext} в {@code folder}
     * директории и поддиректориях и возвращает их полные пути в виде массива
     * строк. В случае если extension == null, считаем что это пустая строка и
     * ищем все файлы.
     *
     * @param folder
     *            корневая директория для поиска
     * @param ext
     *            расширение файла
     * @return список найденных файлов с абсолютными путями.
     * @throws IllegalArgumentException
     *             если {@code folder} не существует
     *
     */
    @Override
    public String[] search(Path folder, String ext) throws IllegalArgumentException {
        //String[] searchedFiles = null;
        List<String> searchedFiles = null;
        try {
            if (ext == null) {
                searchedFiles = Files.walk(folder)
                        .filter(file -> file.toFile().isFile())
                        .map(file -> file.toString())
                        .collect(Collectors.toList());
            } else {
                searchedFiles = Files.walk(folder)
                        .filter(file -> file.toString().endsWith(ext))
                        .map(file -> file.toString())
                        .collect(Collectors.toList());
            }

        } catch (IOException e) {
            e.printStackTrace();
        }
        return searchedFiles.toArray(new String[searchedFiles.size()]);
    }
}
