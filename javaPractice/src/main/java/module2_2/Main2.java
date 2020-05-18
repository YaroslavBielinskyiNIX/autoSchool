package module2_2;

import module2_2.com.welcome.Hello;

import java.util.Scanner;

public class Main2 {

    public static void main(String[] args) {
        System.out.print("Enter name: ");
        String name = new Scanner(System.in).nextLine();
        Hello hello = new Hello();

        hello.setupName(name);
        hello.welcome();
        System.out.println("Hello, World!");
        hello.byeBay();
    }

}
