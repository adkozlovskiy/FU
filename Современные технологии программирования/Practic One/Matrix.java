//package com.company;

class Matrix {
    //private class's vars
    private int height, width;
    private int[][] matrix;

    //constructor (h, w) --> m[h][w]
    Matrix(int height, int width){
        this.height = height;
        this.width = width;
        this.matrix = new int[height][width];

        //заполняем матрицу рандомными числами [0; 15]
        for (int i = 0; i < height; i++){
            for (int j = 0; j < width; j++){
                matrix[i][j] = (int) (Math.random() * 15);
            }
        }
    }

    //сложение матриц
    int[][] addToMatrix(Matrix inputMatrix) throws Exception {
        int[][] result = new int[getHeight()][getWidth()];

        if ((getWidth() != inputMatrix.getWidth()) ||
                getHeight() != inputMatrix.getHeight()) throw
                new Exception("Матрицы имеют неодинаковые размеры!");

        for (int i = 0; i < getHeight(); i++){
            for (int j = 0; j < getWidth(); j++) {
                result[i][j] = getMatrix()[i][j] + inputMatrix.getMatrix()[i][j];
            }
        }
        return result;
    }

    //умножение матрицы на число
    int[][] mulByNumber(int number) {
        int[][] result = new int[getHeight()][getWidth()];
        for (int i = 0; i < getHeight(); i++){
            for (int j = 0; j < getWidth(); j++) {
                result[i][j] = getMatrix()[i][j] * number;
            }
        } return result;
    }

    // O(n^3)
    int[][] mulMatrixByMatrix(Matrix inputMatrix) throws Exception {

        if ((getHeight() != inputMatrix.getWidth()) ||
                getWidth() != inputMatrix.getHeight()) throw
                new Exception("Высота матрицы А не равна ширине матрицы Б, или наоборот");

        int[][] result = new int[
                Math.min(getHeight(),
                    inputMatrix.getHeight())][
                            Math.min(getWidth(),
                                    inputMatrix.getWidth())
                ];

        for (int i = 0; i < getMatrix().length; i++) {
            for (int j = 0; j < inputMatrix.getMatrix()[0].length; j++) {
                for (int k = 0; k < inputMatrix.getMatrix().length; k++) {
                    result[i][j] += getMatrix()[i][k] * inputMatrix.getMatrix()[k][j];
                }
            }
        } return result;
    }

    //получить трансп. матрицу
    int[][] getTransMatrix() {
        int[][] result = new int [getWidth()][getHeight()];
        for (int i = 0; i < getMatrix().length; i++)
            for (int j = 0; j < getMatrix()[0].length; j++)
                result[j][i] = getMatrix()[i][j];
        return result;
    }

    // возведение матрицы в степень
    int[][] powMatrix(int power) throws Exception{
        if (getWidth() != getHeight()) throw
                new Exception("Возводить в степень можно только квадратные матрицы.");

        int[][] result = getMatrix();
        int p = 1;

        while (p < power){
            result = mulMatrixByMatrixInt(result, getMatrix());
            p++;
        }

        return result;
    }

    //приватные геттеры (за такое обычно отрывают руки)
    private int getHeight() {
        return height;
    }

    private int getWidth() {
        return width;
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
