package com.solution.lushkov;

import com.nixsolutions.ppp.io.NIOUtils;

import org.apache.log4j.Logger;
import org.apache.log4j.LogManager;

import java.io.File;
import java.nio.file.Path;
import java.util.ArrayList;

/**
 * @author Victor Lushkov
 */
public class NIOUtilsRealization implements NIOUtils {
    private static final Logger LOG = LogManager
            .getLogger(IOUtilsRealization.class.getName());

    /**
     * Метод не сделан :(
     */
    @Override
    public String searchText(Path file, int offset) throws IllegalArgumentException {
        return null;
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
        //присвоение объекту File значения их Path
        File fileFolder = folder.toFile();
        //массив для найденных файлов
        ArrayList<String> searchedFiles = new ArrayList<>();
        //проверка на существование файла
        if(!fileFolder.exists()) {
            LOG.info(fileFolder.getAbsolutePath()
                    + " папка не существует");
            return null;
        }
        //все содержимое директории
        File[] files = fileFolder.listFiles();
        //перебор всех элементов в указанной директории
        for (File dir : files) {
            //рекурсивный метод который ищет файлы с заданным расширением
            finder(dir, ext, searchedFiles);
        }
        //проверка размера массива с файлами
        if (searchedFiles.size() == 0) {
            LOG.info(fileFolder.getAbsolutePath()
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
