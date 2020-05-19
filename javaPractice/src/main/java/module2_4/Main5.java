package module2_4;

import java.util.Scanner;

import static java.lang.String.format;
import static java.util.Arrays.stream;
import static java.util.Comparator.*;

public class Main5 {

    public static void main(String[] args) {
        firstLastMiddleSymbol();
        strReplace();
        longestWord();
    }

    public static void firstLastMiddleSymbol() {
        System.out.println("Task1");
        String str = "asasdf 1.  qwerdf";
        int strLength = str.length();

        System.out.println(format("%sFirst symbol: %s, Last symbol = %s",
                strLength % 2 != 0 ? "Middle symbol = " + str.charAt(strLength / 2) + ", " : "",
                str.charAt(0),
                str.charAt(strLength - 1)));

        for (char c:str.toCharArray()) {
            System.out.print(c);
            if (c == '.') {
                break;
            }
        }

        System.out.println("\nSpaces count = " + (strLength - str.replaceAll(" ", "").length()));
    }

    public static void strReplace() {
        System.out.println("Task2");

        System.out.print("Enter defaultStr: ");
        String defaultStr = new Scanner(System.in).nextLine();
        System.out.print("Enter subStr: ");
        String subStr = new Scanner(System.in).nextLine();
        System.out.print("Enter newStr: ");
        String newStr = new Scanner(System.in).nextLine();

        System.out.println(defaultStr.replace(subStr, newStr));
    }

    public static void longestWord() {
        String string = "I just, want! to. be............ a ,superhero".replaceAll("[^a-zA-Z ]", "");
        System.out.println(stream(string.split(" ")).max(comparingInt(String::length)));
    }
}
