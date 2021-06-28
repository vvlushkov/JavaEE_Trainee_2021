package com.solution.lushkov;

import com.nixsolutions.ppp.io.IOUtils;

import org.apache.log4j.Logger;
import org.apache.log4j.LogManager;

import java.io.*;
import java.util.ArrayList;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.zip.GZIPOutputStream;

/**
 * @author Victor Lushkov
 */
public class IOUtilsRealization implements IOUtils {
    public static final Logger LOG = LogManager
            .getLogger(IOUtilsRealization.class.getName());

    /**
     * Метод не работает :(
     */
    @Override
    public String gzip(String fileName, String folder)
            throws IllegalArgumentException {
        // Инициализируем поток чтения из файла
        FileInputStream inputStream = null;
        FileOutputStream outputStream = null;
        GZIPOutputStream gzipOutputStream = null;
        try {
            // Переопределяем поток чтения из файла принятым первым параметром
            inputStream = new FileInputStream(fileName);
            // Инициализируем и определяем поток записи байтов в файл, указывая
            // папкой с новым файлом папку из второго параметра.
            outputStream = new FileOutputStream(folder + "/newFile.gzip");
            // Инициализируем и определяем поток сжатия
            gzipOutputStream = new GZIPOutputStream(outputStream);
            // Устанавливаем размер шага чтения из файла
            int byteStep = 1024;
            byte[] buffer = new byte[byteStep];
            // Инициализируем переменную хранящую позицию
            int length;
            //Сдвиг
            int offset = 0;
            // Сжимаем и записываем данные в файл
            while((length = inputStream.read(buffer)) != -1){
                gzipOutputStream.write(buffer, offset, length);
            }
            return "Сжатие прошло успешно. Путь к файлу: "
                    + folder + "/newFile.gzip";
        } catch (IOException | IllegalArgumentException e) {
            e.printStackTrace();
            return null;
        } finally {
            try {
                gzipOutputStream.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
            try {
                outputStream.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
            try {
                inputStream.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }



    /**
     * Функция ищет в текстовом {@code fileName} все вхождения {@code mark} и
     * возвращает количество строк, в которых встречается {@code mark}.
     * @param fileName
     *            путь к файлу
     * @param mark
     *            текст для поиска
     * @return количество строк, в которых встречается {@code mark}.
     * @throws IllegalArgumentException
     *             если {@code file} не существует
     */
    @Override
    public Integer searchText(String fileName, String mark)
            throws IllegalArgumentException {
        //счетчик строк
        int counter = 0;
        //объекты классов, что используются, определяются вне блоков try/catch
        File file = null;
        FileReader fileReader = null;
        BufferedReader reader = null;
        Pattern pattern = null;
        Matcher matcher = null;
        String line = null;
        try {
            //инициализация объекта File
            file = new File(fileName);
            //инициализация FileReader для объекта File
            fileReader = new FileReader(file);
            //инициализация BufferedReader с существующего FileReader
            //для построчного считывания
            reader = new BufferedReader(fileReader);
            //первая строка считывается сразу
            line = reader.readLine();
            //паттерн для нахождения соответсвтвия в строке
            pattern = Pattern.compile(mark);
            //перебор всех строк
            while (line != null) {
                //мэтчер для строки
                matcher = pattern.matcher(line);
                //проверка на наличие искомого текста
                if (matcher.find()) {
                    //итерация счетчика
                    counter++;
                }
                //считываение следующей строки
                line = reader.readLine();
            }

        } catch (IllegalArgumentException | IOException e) {
            e.printStackTrace();
        } finally {
            try {
                fileReader.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
            try {
                reader.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return counter;
    }



    /**
     * Функция ищет все файлы с расширением {@code ext} в {@code folder}
     * директории и поддиректориях и возвращает их полные пути в виде массива
     * строк. В случае если extension == null считаем что это пустая строка и
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
    public String[] search(File folder, String ext)
            throws IllegalArgumentException {
        //массив для найденных файлов
        ArrayList<String> searchedFiles = new ArrayList<>();
        //проверка на существование файла
        if(!folder.exists()) {
            LOG.info(folder.getAbsolutePath()
                    + " папка не существует");
            return null;
        }
        //все содержимое директории
        File[] files = folder.listFiles();
        //перебор всех элементов в указанной директории
        for (File dir : files) {
            //рекурсивный метод который ищет файлы с заданным расширением
            finder(dir, ext, searchedFiles);
        }
        //проверка размера массива с файлами
        if (searchedFiles.size() == 0) {
            LOG.info(folder.getAbsolutePath()
                    + " не содержит файлов с расширением " + ext);
        }
        //возврат в виде стрингового массива
        return searchedFiles.toArray(new String[searchedFiles.size()]);
    }

    /**
     * Рекурсивный метод для поиска вайлов по заданному расширению.
     * Принимет три параметра: типа File(путь к элементу в директории),
     * типа String(заданное расширение файла для поиска)
     * и типа ArrayList(масссив, куда записывается путь найденных файлов).
     *
     * @param dir - параметр типа File, путь к элементу в директории
     * @param ext - параметр типа String, раширение для поиска
     * @param searchedFiles - параметр типа ArrayList, массив путей к файлам
     */
    private void finder(File dir, String ext, ArrayList searchedFiles) {
        //проверка на то, является ли элемент в директории файлом
        if (!dir.isFile()) {
            //если нет, то создается массив содержимых в нем элементов
            File[] files = dir.listFiles();
            //перебор этого массива
            for (File directory : files) {
                //для каждого элементва вызывается этот же метод
                finder(directory, ext, searchedFiles);
            }
        //если является файлом
        } else {
            //проверка на наличие какого-либо расширения для поиска
            if (ext != null) { //если да, то след. код
                //проверка на нужное расширение
                if (dir.getAbsolutePath().endsWith(ext)) {
                    //добавление в массив названия файла и пути к нему
                    searchedFiles.add(dir.getName() + ": "
                            + dir.getAbsolutePath());
                }
            //если никакого расширения для поиска не передали,
            //то в массив добавляются все файлы
            } else {
                searchedFiles.add(dir.getName() + ": "
                        + dir.getAbsolutePath());
            }
        }
    }
}


