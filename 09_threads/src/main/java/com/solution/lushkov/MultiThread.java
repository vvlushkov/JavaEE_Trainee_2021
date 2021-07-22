package com.solution.lushkov;

import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;

public class MultiThread extends Thread{
    /** Первая (левая) матрица. */
    private final int[][] firstMatrix;
    /** Вторая (правая) матрица. */
    private final int[][] secondMatrix;
    /** Результирующая матрица. */
    private final int[][] resultMatrix;
    /** Начальный индекс. */
    private final int firstIndex;
    /** Конечный индекс. */
    private final int lastIndex;
    /** Число членов суммы при вычислении значения ячейки. */
    private final int sumLength;

    private static final Logger LOG = LogManager
            .getLogger(MultiThread.class.getName());

    /**
     * @param firstMatrix  Первая (левая) матрица.
     * @param secondMatrix Вторая (правая) матрица.
     * @param resultMatrix Результирующая матрица.
     * @param firstIndex   Начальный индекс (ячейка с этим индексом вычисляется).
     * @param lastIndex    Конечный индекс (ячейка с этим индексом не вычисляется).
     */
    public MultiThread(final int[][] firstMatrix,
                       final int[][] secondMatrix,
                       final int[][] resultMatrix,
                       final int firstIndex,
                       final int lastIndex)
    {
        this.firstMatrix  = firstMatrix;
        this.secondMatrix = secondMatrix;
        this.resultMatrix = resultMatrix;
        this.firstIndex   = firstIndex;
        this.lastIndex    = lastIndex;

        sumLength = secondMatrix.length;
    }

    /**Вычисление значения в одной ячейке.
     *
     * @param row Номер строки ячейки.
     * @param col Номер столбца ячейки.
     */
    private void calcValue(final int row, final int col)
    {
        int sum = 0;
        for (int i = 0; i < sumLength; ++i)
            sum += firstMatrix[row][i] * secondMatrix[i][col];
        resultMatrix[row][col] = sum;
    }

    /** Рабочая функция потока. */
    @Override
    public void run()
    {
        LOG.info("Thread " + getName() + " started. Calculating cells from "
                + firstIndex + " to " + lastIndex + "...");

        final int colCount = secondMatrix[0].length;  // Число столбцов результирующей матрицы.
        for (int index = firstIndex; index < lastIndex; ++index)
            calcValue(index / colCount, index % colCount);

        LOG.info("Thread " + getName() + " finished.");
    }
}
