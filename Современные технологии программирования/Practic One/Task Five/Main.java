package com.company;

import java.util.Scanner;

public class Main {

    static public void main(String[] args){
        Scanner scanner = new Scanner(System.in);

        System.out.print("Введите количество значений: ");
        int n = scanner.nextInt();

        // Лучше делать на ArrayList<>
        int[] values = new int[n];
        int[] gravity = new int[n];

        System.out.println("Заполним массив значений: ");
        for (int i = 0; i < n; i++){
            values[i] = scanner.nextInt();
        }

        System.out.println("Заполним массив весов: ");
        for (int i = 0; i < n; i++){
            gravity[i] = scanner.nextInt();
        }

        Comparator comparator = new Comparator(values, gravity);
        System.out.println("Полученное значение: " + comparator.getRandomNumber());
    }
}