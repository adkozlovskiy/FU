//package com.company;

class Matrix {
    // Private class's vars
    private int height, width;
    private int[][] matrix;

    // Constructor (h, w) --> m[h][w]
    Matrix(int height, int width){
        this.height = height;
        this.width = width;
        this.matrix = new int[height][width];

        // Заполняем матрицу рандомными числами [0; 15]
        for (int i = 0; i < height; i++){
            for (int j = 0; j < width; j++){
                matrix[i][j] = (int) (Math.random() * 15);
            }
        }
    }

    // Сложение матриц
    int[][] addToMatrix(Matrix inputMatrix) throws Exception {
        int[][] result = new int[height][width];

        if ((width != inputMatrix.width) ||
                height!= inputMatrix.height) throw
                new Exception("Матрицы имеют неодинаковые размеры!");

        for (int i = 0; i < height; i++){
            for (int j = 0; j < width; j++) {
                result[i][j] = getMatrix()[i][j] + inputMatrix.getMatrix()[i][j];
            }
        }
        return result;
    }

    // Умножение матрицы на число
    int[][] mulByNumber(int number) {
        int[][] result = new int[height][width];
        for (int i = 0; i < height; i++){
            for (int j = 0; j < width; j++) {
                result[i][j] = getMatrix()[i][j] * number;
            }
        } return result;
    }

    // Умножение матрицы на матрицу / O(n^3)
    int[][] mulMatrixByMatrix(Matrix inputMatrix) throws Exception {

        if ((height != inputMatrix.width) ||
                width != inputMatrix.height) throw
                new Exception("Высота матрицы А не равна ширине матрицы Б, или наоборот");

        int[][] result = new int[
                Math.min(height,
                    inputMatrix.height)][
                            Math.min(width,
                                    inputMatrix.width)
                ];

        for (int i = 0; i < getMatrix().length; i++) {
            for (int j = 0; j < inputMatrix.getMatrix()[0].length; j++) {
                for (int k = 0; k < inputMatrix.getMatrix().length; k++) {
                    result[i][j] += getMatrix()[i][k] * inputMatrix.getMatrix()[k][j];
                }
            }
        } return result;
    }

    // Получить трансп. матрицу
    int[][] getTransMatrix() {
        int[][] result = new int [width][height];
        for (int i = 0; i < getMatrix().length; i++)
            for (int j = 0; j < getMatrix()[0].length; j++)
                result[j][i] = getMatrix()[i][j];
        return result;
    }

    // Возведение матрицы в степень
    int[][] powMatrix(int power) throws Exception{
        if (width != height) throw
                new Exception("Возводить в степень можно только квадратные матрицы.");

        int[][] result = getMatrix();
        int p = 1;

        while (p < power){
            result = mulMatrixByMatrixInt(result, getMatrix());
            p++;
        }

        return result;
    }

    int[][] getMatrix() {
        return matrix;
    }

    private int[][] mulMatrixByMatrixInt(int[][] matrix, int[][] inMatrix){
        int[][] result = new int[matrix.length][matrix[0].length];
        for (int i = 0; i < matrix.length; i++) {
            for (int j = 0; j < inMatrix[0].length; j++) {
                for (int k = 0; k < inMatrix.length; k++) {
                    result[i][j] += matrix[i][k] * inMatrix[k][j];
                }
            }
        }
        return result;
    }

}
