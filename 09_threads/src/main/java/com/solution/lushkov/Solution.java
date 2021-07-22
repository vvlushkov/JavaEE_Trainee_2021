package com.solution.lushkov;

import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;

/**
 * @author Victor Lushkov
 */
public class Solution {
    private static final Logger LOG = LogManager
            .getLogger(Solution.class.getName());

    public void startCalculating(int[][] firstMatrix,
                                 int[][] secondMatrix)
    {
        int[][] resultMatrixMT = multiplyMatrix(firstMatrix, secondMatrix,
                Runtime.getRuntime().availableProcessors()); //возвращает количество доступных процессоров

        printAllMatrix(firstMatrix, secondMatrix, resultMatrixMT);
    }

    /**
     * Вывод матрицы в файл.
     * Производится выравнивание значений для лучшего восприятия.
     *
     * @param matrix Выводимая матрица.
     */
    private void printMatrix(int[][] matrix)
    {
        for (int[] row : matrix) {
            System.out.print("\t\t\t\t");
            for (int elem : row) {
                System.out.print(elem + "\t");
            }
            System.out.println();
        }
    }

    /**
     * Вывод трёх матриц в файл. Файл будет перезаписан.
     *
     * @param firstMatrix  Первая матрица.
     * @param secondMatrix Вторая матрица.
     * @param resultMatrix Результирующая матрица.
     */
    private void printAllMatrix(int[][] firstMatrix,
                                int[][] secondMatrix,
                                int[][] resultMatrix)
    {
        LOG.info("First matrix:\n");
        printMatrix(firstMatrix);

        LOG.info("Second matrix:\n");
        printMatrix(secondMatrix);

        LOG.info("Result matrix:\n");
        printMatrix(resultMatrix);
    }


    /**
     * Многопоточное умножение матриц.
     *
     * @param firstMatrix  Первая (левая) матрица.
     * @param secondMatrix Вторая (правая) матрица.
     * @param threadCount Число потоков.
     * @return Результирующая матрица.
     */
    private int[][] multiplyMatrix(int[][] firstMatrix,
                                   int[][] secondMatrix,
                                   int threadCount)
    {
        //Поиск минимального значения по условию
        int min = Math.min(firstMatrix.length,
                Math.min(firstMatrix[0].length, secondMatrix[0].length));
        if (min < threadCount) {
            threadCount = min - 1;
        }

        int rowCount = firstMatrix.length;             // Число строк результирующей матрицы.
        int colCount = secondMatrix[0].length;         // Число столбцов результирующей матрицы.
        int[][] result = new int[rowCount][colCount];  // Результирующая матрица.

        int cellsForThread = (rowCount * colCount) / threadCount;  // Число вычисляемых ячеек на поток.
        int firstIndex = 0;  // Индекс первой вычисляемой ячейки.

        MultiThread[] multiplierThreads = new MultiThread[threadCount];  // Массив потоков.

        // Создание и запуск потоков.
        for (int threadIndex = threadCount - 1; threadIndex >= 0; --threadIndex) {
            int lastIndex = firstIndex + cellsForThread;  // Индекс последней вычисляемой ячейки.
            if (threadIndex == 0) {
                /* Один из потоков должен будет вычислить не только свой блок ячеек,
                   но и остаток, если число ячеек не делится нацело на число потоков. */
                lastIndex = rowCount * colCount;
            }
            multiplierThreads[threadIndex] = new MultiThread(firstMatrix, secondMatrix, result, firstIndex, lastIndex);
            multiplierThreads[threadIndex].start();
            firstIndex = lastIndex;
        }

        // Ожидание завершения потоков.
        try {
            for (MultiThread multiplierThread : multiplierThreads) {
                multiplierThread.join();
            }
        }
        catch (InterruptedException e) {
            e.printStackTrace();
        }
        return result;
    }
}
