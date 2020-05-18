package module2_3;

import java.util.Scanner;

public class Main3 {


    public static void main(String[] args) {
        divisionRemainder();
        digitSum();
        round();
        quickMath();
    }

    public static void divisionRemainder() {
        System.out.println("Task1");

        int q;
        int w;

        System.out.print("Enter q: ");
        q = new Scanner(System.in).nextInt();
        System.out.print("Enter w: ");
        w = new Scanner(System.in).nextInt();

        System.out.println("q / w = " + q / w + ", remainder = " + q % w);
    }

    public static void digitSum() {
        System.out.println("Task2");

        System.out.println("Enter double digit num: ");
        int n = new Scanner(System.in).nextInt();

        System.out.println("Sum = " + (n % 10 + (n - (n % 10)) / 10));
    }

    public static void round() {
        System.out.println("Task3");

        System.out.println("Enter n: ");
        double n = new Scanner(System.in).nextDouble();
        System.out.println("Result = " + Math.round(n));
    }

    public static void quickMath() {
        System.out.println("Task4");

        System.out.print("Enter a = ");
        int a = new Scanner(System.in).nextInt();
        System.out.print("Enter b = ");
        int b = new Scanner(System.in).nextInt();
        System.out.print("Enter c = ");
        int c = new Scanner(System.in).nextInt();

        a = a + b;
        b = c - a + b;
        c = a + c;

        System.out.println("a = " + a + ", b = " + b + ", c = " + c);
    }
}
