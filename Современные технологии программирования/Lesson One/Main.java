//package com.company;
import java.util.Random;
import java.util.Scanner;

public class Main {
    
    private static void taskOne(){
        System.out.println("Hello World");
    }

    private static void taskTwo(){
        int num = 7;
        System.out.println("Ответ: " + num);
    }

    private static void taskThree(){
        int a = 0;
        a += 1;
        a = a + 1;
        a = a + Integer.parseInt("1");
        System.out.println("Ответ: " + a);
    }

    private static void taskFour(){
        int b = 1, c = 2;
        b = b + c - (c = b);

        int d = b;
        b = c;
        c = d;
        System.out.println("Ответ: \nb = " + b + ", c = " + c);
    }

    private static void taskFive(int a, int b){
        System.out.println("Ответ: " + Math.sqrt(a*a + b*b));
    }

    private static void taskSix(int i){
        System.out.println("Ответ: " + i % 10);
    }

    private static void taskSeven(int i){ //за такие приколы руки отрывают, но пусть будет. Адекватное решение в taskEight.
        String res = String.valueOf(i);
        char ch = res.charAt(res.length() - 2);

        System.out.println("Ответ: " + ch);
    }

    private static void taskEight(int i){ //решение подходит и для taskSeven.
        System.out.println("Ответ: " + ((i % 100)/10);
    }

    private static int taskNine(int i){
        return i - 21;
    }

    private static double taskTen(int a, int b){
        return (a + b) / 2;
    }

    private static double taskEleven(int a, int b){
        return Math.sqrt(a * b);
    }

    private static double taskTwelve(double Ax, double Ay,
                                     double Bx, double By){

        return Math.sqrt(Math.pow((Ax - Ay), 2) + Math.pow((Bx - By), 2));
    }
    
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        System.out.print("Введите номер задания: ");
        int key = scanner.nextInt();
        int num, r1, r2;

        switch (key){
            case 1:
                taskOne();
                break;

            case 2:
                taskTwo();
                break;

            case 3:
                taskThree();
                break;

            case 4:
                taskFour();
                break;

            case 5:
                System.out.print("Введите число 1: ");
                r1 = scanner.nextInt();
                System.out.print("Введите число 2: ");
                r2 = scanner.nextInt();

                taskFive(r1, r2);
                break;

            case 6:
                System.out.print("Введите число: ");
                num = scanner.nextInt();

                taskSix(num);
                break;

            case 7:
                System.out.print("Введите число: ");
                num = scanner.nextInt();

                taskSeven(num);
                break;

            case 8:
                System.out.print("Введите число: ");

                num = scanner.nextInt();

                taskEight(num);
                break;

            case 9:
                System.out.print("Введите количество чисел: ");
                int count = scanner.nextInt();
                System.out.println();

                System.out.print("Введите диапазон Random: ");
                int range = scanner.nextInt();

                Random random = new Random();
                System.out.println("Ответ: \n");
                for (int i = 0; i < count; i++){
                    System.out.println(taskNine(
                            random.nextInt(range)));
                }

                break;

            case 10:
                System.out.print("Введите первое число: ");
                r1 = scanner.nextInt();

                System.out.print("Введите второе число: ");
                r2 = scanner.nextInt();

                System.out.println("Ответ: " + taskTen(r1, r2));
                break;

            case 11:
                System.out.print("Введите первое число: ");
                r1 = scanner.nextInt();

                System.out.print("Введите второе число: ");
                r2 = scanner.nextInt();

                System.out.println("Ответ: " + taskEleven(r1, r2));
                break;

            case 12:
                System.out.print("Введите координату A(x): ");
                double Ax = scanner.nextDouble();

                System.out.print("Введите координату A(y): ");
                double Ay = scanner.nextDouble();

                System.out.print("Введите координату B(x): ");
                double Bx = scanner.nextDouble();

                System.out.print("Введите координату B(y): ");
                double By = scanner.nextDouble();


                System.out.println("Ответ: " + taskTwelve(Ax, Ay, Bx, By));
                break;

            default:
                System.out.println("Некорректный ввод. ");
                break;
        }
    }

}
