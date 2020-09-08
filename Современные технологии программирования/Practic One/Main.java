//package com.company;
//бесполезный класс (не нужен для задания)
import java.util.Scanner;

public class Main {
    static public void main(String[] args){
        Scanner in = new Scanner(System.in);

        System.out.print("Введите высоту матрицы A: ");
        int A_height = in.nextInt();
        System.out.print("Введите ширину матрицы A: ");
        int A_width = in.nextInt();

        System.out.print("Введите высоту матрицы B: ");
        int B_height = in.nextInt();
        System.out.print("Введите ширину матрицы B: ");
        int B_width = in.nextInt();

        Matrix matrixA = new Matrix(A_height, A_width);
        Matrix matrixB = new Matrix(B_height, B_width);

        System.out.println("Матрица А: ");
        printMatrix(matrixA.getMatrix());

        System.out.println("Матрица B: ");
        printMatrix(matrixB.getMatrix());

        System.out.println("Введите номер задания: ");
        String key = in.next();

        try{
            switch (key){
                case "a":
                    int[][] sumMatrix = matrixA.addToMatrix(matrixB);
                    System.out.println("Результат: ");
                    printMatrix(sumMatrix);
                    break;

                case "b":
                    System.out.println("Введите число: ");
                    int num = in.nextInt();
                    int[][] mulByNumberMatrix = matrixA.mulByNumber(num);
                    System.out.println("Результат: ");
                    printMatrix(mulByNumberMatrix);
                    break;

                case "с":
                    int[][] mulByMatrixMatrix = matrixA.mulMatrixByMatrix(matrixB);
                    System.out.println("Результат: ");
                    printMatrix(mulByMatrixMatrix);
                    break;

                case "d":
                    int[][] transMatrixA = matrixA.getTransMatrix();
                    System.out.println("Результат A: ");
                    printMatrix(transMatrixA);

                    int[][] transMatrixB = matrixB.getTransMatrix();
                    System.out.println("Результат B: ");
                    printMatrix(transMatrixB);
                    break;

                case "e":
                    System.out.println("Введите степень: ");
                    int p = in.nextInt();
                    int[][] poweredMatrixA = matrixA.powMatrix(p);
                    System.out.println("Результат А: ");
                    printMatrix(poweredMatrixA);

                    int[][] poweredMatrixB = matrixB.powMatrix(p);
                    System.out.println("Результат B: ");
                    printMatrix(poweredMatrixB);
                    break;

                default:
                    System.out.println("Неккоректный ввод.");
                    break;
            }

        } catch (Exception exception) {
                System.out.println(exception.getMessage());
            }
    }

    //красивый вывод матрицы
    static private void printMatrix(int[][] matrix){
        for (int[] ints : matrix) {
            for (int j = 0; j < matrix[0].length; j++) {
                System.out.format("%6d ", ints[j]);
            } System.out.println();
        }
    }
}
