package module2_5;

import java.util.Scanner;

import static java.lang.Math.random;
import static java.util.Arrays.stream;

public class Main6 {

    public static void main(String[] args) {
        max(initializeArray());
        reverseStrArr();
        twoArrs();
        reverseNumArr();
        littleBigArr();
    }

    public static int[] initializeArray() {
        int[] array = new int[20];

        for (int i = 0; i < 20; i++) {
            array[i] = (int) (random() * 100);
            System.out.print(array[i] + " ");
        }

        return array;
    }

    public static void max(int[] array) {
        System.out.println("Task1");

        System.out.println("\n" + stream(array).max().getAsInt());
    }

    public static void reverseStrArr() {
        System.out.println("Task2");

        String[] array = new String[10];

        for (int i = 0; i < 8; i++) {
            System.out.println(String.format("Enter %d line :", i + 1));
            array[i] = new Scanner(System.in).nextLine();
        }

        for (int i = 9; i >= 0; i--) {
            System.out.println(array[i]);
        }
    }

    public static void twoArrs() {
        System.out.println("Task3");

        int[] numArr = new int[10];
        String[] strArr = new String[10];

        for (int i = 0; i < 10; i++) {
            System.out.println(String.format("Enter %d line :", i + 1));
            strArr[i] = new Scanner(System.in).nextLine();
        }

        for (int i = 0; i < 10; i++) {
            numArr[i] = strArr[i].length();
            System.out.println(numArr[i]);
        }

    }

    public static void reverseNumArr() {
        System.out.println("Task4");

        int[] arr = new int[10];

        for (int i = 0; i < 10; i++) {
            arr[i] = new Scanner(System.in).nextInt();
        }

        for (int i = 0; i < 5; i++) {
            int temp = arr[i];
            arr[i] = arr[9 - i];
            arr[9 - i] = temp;
        }

        for (int i:arr) {
            System.out.println(i);
        }
    }

    public static void littleBigArr() {
        System.out.println("Task5");

        int[] bigArray = new int[20];
        int[] littleArray1 = new int[10];
        int[] littleArray2 = new int[10];

        for (int i = 0; i < 20; i++){
            bigArray[i] = new Scanner(System.in).nextInt();
        }

        for (int i = 0; i < 20; i++) {
            if (i < 10) {
                littleArray1[i] = bigArray[i];
            }
            else {
                littleArray2[i - 10] = bigArray[i];
            }
        }

        for (int i:littleArray2) {
            System.out.println(i);
        }
    }
}
