package module2_2;

import java.util.Scanner;

public class Main1 {

    public static void main(String[] args) {
        System.out.println("Hello, World!");
        printHelloName();
    }

    public static void printHelloName() {
        System.out.print("Enter name: ");
        String name = new Scanner(System.in).nextLine();
        System.out.println("Hello, " + name);
    }
}
