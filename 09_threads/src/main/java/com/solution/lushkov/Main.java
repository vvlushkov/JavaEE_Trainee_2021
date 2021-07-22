package com.solution.lushkov;

import java.util.Random;

/**
 * @author Victor Lushkov
 *
 * Тут только первое задание, второе не успел :-(
 */
public class Main {
    public static void main(String[] args)
    {
        int firstMatrixRows = 5;
        int firstMatrixCols = 6;
        int secondMatrixRows = 6; // == firstMatrixCols
        int secondMatrixCols = 7;

        int[][] firstMatrix  = new int[firstMatrixRows][firstMatrixCols];    // Первая (левая) матрица.
        int[][] secondMatrix = new int[secondMatrixRows][secondMatrixCols];  // Вторая (правая) матрица.

        randomMatrix(firstMatrix);
        randomMatrix(secondMatrix);

        Solution solution = new Solution();
        solution.startCalculating(firstMatrix, secondMatrix);
    }

    /**
     * Заполнение матрицы случайными числами.
     *
     * @param matrix Заполняемая матрица.
     */
    public static void randomMatrix(int[][] matrix)
    {
        Random random = new Random();  // Генератор случайных чисел.

        for (int row = 0; row < matrix.length; ++row)           // Цикл по строкам матрицы.
            for (int col = 0; col < matrix[row].length; ++col)  // Цикл по столбцам матрицы.
                matrix[row][col] = random.nextInt(10);         // Случайное число от 0 до 100.
    }
}
