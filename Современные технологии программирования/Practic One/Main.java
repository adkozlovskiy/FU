//package com.company

import java.util.Scanner;

public class Main {
  static public void main(String[] args){
    Scanner in = new Scanner(System.in);

    System.out.print("Введите высоту матрицы A: ");
    int Aheight = in.nextInt();
    System.out.print("Введите ширину матрицы A: ");
    int Awidth = in.nextInt();

    System.out.print("Введите высоту матрицы B: ");
    int Bheight = in.nextInt();
    System.out.print("Введите ширину матрицы B: ");
    int Bwidth = in.nextInt();

    Matrix matrix1 = new Matrix(Aheight, Awidth);
    Matrix matrix2 = new Matrix(Bheight, Bwidth);

    try{
      int[][] result = matrix1.addToMatrix(matrix2);
    } catch (Exception exception){
      System.out.println("Ошибка. Неодинаковый matrix volume!");
    }
  }
}
