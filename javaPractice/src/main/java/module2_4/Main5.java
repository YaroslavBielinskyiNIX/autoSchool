package module2_4;

import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import java.util.Scanner;

import static java.util.Comparator.*;

public class Main5 {
    public static void main(String[] args) {
        firstLastMiddleSymbol();
        strReplace();
        longestWord();

    }

    public static void firstLastMiddleSymbol() {
        System.out.println("Task1");
        String string = "asasdf 1.  qwerdf";

        System.out.println((string.length() % 2 != 0 ? "Middle symbol = " + string.charAt(string.length() / 2) + ", " : "")
                        + "First symbol: " + string.charAt(0)
                        + ", Last symbol = " + string.charAt(string.length() - 1));

        for (char c:string.toCharArray()) {
            System.out.print(c);
            if (c == '.') {
                break;
            }
        }

        System.out.println("\nSpaces count = " + (string.length() - string.replaceAll(" ", "").length()));
    }

    public static void strReplace() {
        System.out.println("Task2");

        System.out.print("Enter defaultStr: ");
        String defaultStr = new Scanner(System.in).nextLine();
        System.out.print("Enter subStr: ");
        String subStr = new Scanner(System.in).nextLine();
        System.out.print("Enter newStr: ");
        String newStr = new Scanner(System.in).nextLine();

        System.out.println(defaultStr.replace(subStr,newStr));
    }

    public static void longestWord() {
        String string = "I just, want! to. be............ a ,superhero".replaceAll("[^a-zA-Z ]", "");
        System.out.println(Arrays.stream(string.split(" ")).max(comparingInt(String::length)));
    }

}
