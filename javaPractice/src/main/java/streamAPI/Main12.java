package streamAPI;

import java.util.LinkedList;
import java.util.List;
import java.util.Random;

public class Main12 {

    public static void main(String[] args) {
        List<String> list = new LinkedList<>();
        list.add("aaaaaa");

        for (int i = 0; i < 50; i++) {
            String str = "word";
            Random random = new Random();

            for (int j = 0; j < random.nextInt(5); j++) {
                str = str + " " + str;
            }

            list.add(str);
        }

        for (String s:list) {
            System.out.println(s);
        }

        list.forEach(str -> System.out.print(str.length() + "\t"));
        System.out.println();
        list.forEach(str -> System.out.println(str.contains("d") ? "" : str));
        list.stream().sorted().forEachOrdered(System.out::println);
    }
}
