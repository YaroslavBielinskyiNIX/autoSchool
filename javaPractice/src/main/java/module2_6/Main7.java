package module2_6;

import java.util.Scanner;

import static java.lang.Integer.max;
import static java.lang.Thread.sleep;

public class Main7 {

    public static void main(String[] args) {
        task1();
        task2();
        task3();
        task4();
        task5and6();
        task7();
        task8();
        task9();
        task10();
        task11();
        task12();
        task13();
        task14();
        task15();
        task16();
        task17();
        task18();
    }

    public static void task1() {
        System.out.println("Task1");

        int firstNum = new Scanner(System.in).nextInt();
        int secondNum = new Scanner(System.in).nextInt();
        System.out.println("Min = " + (firstNum < secondNum ? firstNum : secondNum));
    }

    public static void task2() {
        System.out.println("Task2");

        int firstNum = new Scanner(System.in).nextInt();
        int secondNum = new Scanner(System.in).nextInt();
        int thirdNum = new Scanner(System.in).nextInt();
        int fourthNum = new Scanner(System.in).nextInt();

        System.out.println("Max = " + max(max(firstNum, secondNum), max(thirdNum, fourthNum)));
    }

    public static void task3() {
        System.out.println("Task3");

        int firstNum = new Scanner(System.in).nextInt();
        int secondNum = new Scanner(System.in).nextInt();
        int thirdNum = new Scanner(System.in).nextInt();

        if (firstNum > secondNum && firstNum > thirdNum) System.out.println(firstNum);
        else if (secondNum > firstNum && secondNum > thirdNum) System.out.println(secondNum);
        else System.out.println(thirdNum);
        if (firstNum < secondNum && firstNum > thirdNum) System.out.println(firstNum);
        else if (secondNum < firstNum && secondNum > thirdNum) System.out.println(secondNum);
        else System.out.println(thirdNum);
        if (firstNum < secondNum && firstNum < thirdNum) System.out.println(firstNum);
        else if (secondNum < firstNum && secondNum < thirdNum) System.out.println(secondNum);
        else System.out.println(thirdNum);
    }

    public static void task4() {
        System.out.println("Task4");

        String firstName = new Scanner(System.in).nextLine();
        String secondName = new Scanner(System.in).nextLine();

        if (firstName.equals(secondName)) System.out.println("Same names");
        else if (firstName.length() == secondName.length()) System.out.println("Same name lengths");
        else System.out.println("Different names");
    }

    public static void task5and6() {
        System.out.println("Task5&6");

        int age = new Scanner(System.in).nextInt();
        String name = new Scanner(System.in).nextLine();

        if (age < 18) System.out.println("Grow up");
        else if (age > 20) System.out.println("18 enough");
    }

    public static void task7() {
        System.out.println("Task7");

        int magicNum = 7;
        int yourNum = 0;


        for (int i = 0; i < 7; i++) {
            System.out.println("Enter num");
            yourNum = new Scanner(System.in).nextInt();

            if (magicNum == yourNum) {
                System.out.println("You win!!!");
                break;
            } else if (magicNum < yourNum) System.out.println("Magic num is smaller");
            else System.out.println("Magic num is bigger");
        }

        System.out.println("Game over");
    }

    public static void task8() {
        System.out.println("Task8");

        int counter = 1;

        while (counter <= 10) {
            System.out.print(counter + " ");
            counter++;
        }
    }

    public static void task9() {
        System.out.println("\nTask9");

        int counter = 10;

        while (counter >= 1) {
            System.out.print(counter + " ");
            counter--;
        }
    }

    public static void task10() {
        System.out.println("Task10");

        System.out.println("Enter N: ");
        int n = new Scanner(System.in).nextInt();
        System.out.println("Enter string: ");
        String str = new Scanner(System.in).nextLine();

        while (n >= 1) {
            System.out.println(str);
            n--;
        }

    }

    public static void task11() {
        int i = 0;
        int j = 0;

        while (i < 5) {
            while (j < 5) {
                System.out.print("S");
                j++;
            }
            System.out.println();
            i++;
            j = 0;
        }
    }

    public static void task12() {
        int i = 1;
        int j = 1;

        while (i <= 10) {
            while (j <= 10) {
                System.out.print(i * j + "\t");
                j++;
            }
            System.out.println();

            j = 1;
            i++;
        }
    }

    public static void task13() {
        for (int i = 1; i <= 100; i++) {
            if (i % 2 == 0) System.out.print(i + " ");
        }
    }

    public static void task14() {
        System.out.println("Enter m = ");
        int m = new Scanner(System.in).nextInt();
        System.out.println("Enter n = ");
        int n = new Scanner(System.in).nextInt();

        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                System.out.print(8);
            }
            System.out.println();
        }
    }

    public static void task15() {
        for (int i = 0; i <= 10; i++) {
            for (int j = i; j > 0; j--) {
                System.out.print(8);
            }
            System.out.println();
        }
    }

    public static void task16() {
        for (int i = 0; i < 9; i++) {
            System.out.print(8);
        }
        for (int i = 0; i < 10; i++) {
            System.out.println(8);
        }
    }

    public static void task17() {
        String name = new Scanner(System.in).nextLine();

        for (int i = 0; i < 10; i++) {
            System.out.println(name + " love me.");
        }
    }

    public static void task18() {
        for (int i = 30; i >= 0; i--) {
            System.out.println(i);

            try {
                sleep(100);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        System.out.println("Boom!!!!");
    }
}
