package module5_2;

import java.time.LocalDate;
import java.util.*;
import java.util.stream.Collectors;

public class Main11 {

    public static void main(String[] args) {
        task1();
        task2();
        task3();
        task4and5();
        task6();
        task7();
        task8and9();

        System.out.println("Task10");

        List<String> strs = new ArrayList<>();
        for (int i = 0; i < 10; i++) {
            System.out.println("Enter str:");
            String str = new Scanner(System.in).nextLine();
            strs.add(0, str);
        }

        for (String str:strs) {
            System.out.println(str);
        }

        task11();
        task12();
        task13();
        task14();
        task15();
        task16();
        task17();

    }

    public static void task1() {
        System.out.println("Task1");

        HashSet<String> fruits = new HashSet<>();

        fruits.add("арбуз");
        fruits.add("банан");
        fruits.add("вишня");
        fruits.add("груша");
        fruits.add("дыня");
        fruits.add("ежевика");
        fruits.add("жень-шень");
        fruits.add("земляника");
        fruits.add("ирис");
        fruits.add("картофель");

        for (String str : fruits) {
            System.out.println(str);
        }
    }

    public static void task2() {
        System.out.println("Task2");

        HashMap<String, String> foods = new HashMap<>();

        foods.put("арбуз", "ягода");
        foods.put("банан", "трава");
        foods.put("вишня", "ягода");
        foods.put("груша", "фрукт");
        foods.put("дыня", "овощ");
        foods.put("ежевика", "куст");
        foods.put("жень-шень", "корень");
        foods.put("земляника", "ягода");
        foods.put("ирис", "цветок");
        foods.put("картофель", "клубень");

        foods.forEach((key, value) -> System.out.println(key + " - " + value));
    }

    public static void task3() {
        System.out.println("Task3");

        HashMap<String, Cat> cats = new HashMap<>();

        for (int i = 0; i < 10; i++) {
            Cat cat = new Cat("Cat" + i);
            cats.put(cat.getName(), cat);
        }

        cats.forEach((key, value) -> System.out.println(key + " - " + value));

    }

    public static void task4and5() {
        System.out.println("Task4and5");

        HashMap<String, String> strs = new HashMap<>();

        for (int i = 0; i < 10; i++) {
            strs.put("key" + i, "value" + i);
        }

        strs.forEach((key, value) -> System.out.println(key));
        strs.forEach((key, value) -> System.out.println(value));
    }

    public static void task6() {
        System.out.println("Task6");

        HashMap<String, Object> objcts = new HashMap<>();

        for (int i = 0; i < 10; i++) {
            objcts.put(String.valueOf(i), i);
        }

        objcts.forEach((key, value) -> System.out.println(key + " - " + value));
    }

    public static void task7() {
        System.out.println("Task7");

        List<String> strs = new ArrayList<>();

        for (int i = 0; i < 5; i++) {
            strs.add(String.valueOf(i));
        }

        System.out.println(strs.size());

        for (String str:strs) {
            System.out.println(str);
        }
    }

    public static void task8and9() {
        System.out.println("Task8and9");

        List<String> strs = new ArrayList<>();

        for (int i = 0; i < 5; i++) {
            System.out.println("Enter str:");
            String str = new Scanner(System.in).nextLine();
            strs.add(str);
        }

        int maxLength = 0;
        int minLength = Integer.MAX_VALUE;

        for (String str:strs) {
            if (maxLength < str.length()) maxLength = str.length();
            if (minLength > str.length()) minLength = str.length();
        }

        System.out.println("MaxLength:");
        for (String str:strs) {
            if (str.length() == maxLength) System.out.println(str);
        }

        System.out.println("MinLength:");
        for (String str:strs) {
            if (str.length() == minLength) System.out.println(str);
        }
    }

    public static void task11() {
        System.out.println("Task11");

        List<String> strs = new ArrayList<>();
        for (int i = 0; i < 5; i++) {
            System.out.println("Enter str:");
            String str = new Scanner(System.in).nextLine();
            strs.add(0, str);
        }

        for (int i = 0; i < 13; i++) {
            String tmp = strs.get(strs.size() - 1);
            strs.remove(strs.size() - 1);
            strs.add(0, tmp);
        }

        for (String str:strs) {
            System.out.println(str);
        }
    }

    public static void task12() {
        Set<String> words = new HashSet<>();

        for (int i = 0; i < 10; i++) {
            words.add("Л" + i);
        }
    }

    public static void task13() {
        Set<Integer> nums = new HashSet<>();

        for (int i = 0; i < 20; i++) {
            nums.add(i);
        }

        nums.removeIf(integer -> integer > 10);

        System.out.println(nums);
    }

    public static void task14() {
        Map<String, String> fio = new HashMap<>();

        fio.put("Vlad", "Kobzev");
        fio.put("Ken", "Kens");
        fio.put("Yar", "Bielinskyi");
        fio.put("Jil", "Jerry");
        fio.put("Jool", "Kobzev");
        fio.put("John", "Johnson");
        fio.put("Jin", "Kobzev");
        fio.put("Artur", "Nickon");
        fio.put("Ivan", "Stepanenko");
        fio.put("David", "Simonov");

        int counter = 0;

        for (Map.Entry<String, String> entry : fio.entrySet()) {
            if (entry.getKey().equals("Vlad") || entry.getValue().equals("Kobzev")) counter++;
        }

        System.out.println(counter);
    }

    public static void task15() {
        Map<String, LocalDate> personInfo = new HashMap<>();

        personInfo.put("Yaroslav", LocalDate.of(1999, 1, 10));
        personInfo.put("Vlad", LocalDate.of(1999, 2, 10));
        personInfo.put("John", LocalDate.of(1999, 3, 10));
        personInfo.put("Nick", LocalDate.of(1999, 4, 10));
        personInfo.put("Tom", LocalDate.of(1999, 5, 10));
        personInfo.put("Jerry", LocalDate.of(1999, 6, 10));
        personInfo.put("Mick", LocalDate.of(1999, 7, 10));
        personInfo.put("Nikc", LocalDate.of(1999, 8, 10));
        personInfo.put("Simon", LocalDate.of(1999, 9, 10));
        personInfo.put("David", LocalDate.of(1999, 12, 10));

        personInfo.entrySet().removeIf(entry -> entry.getValue().getMonthValue() >= 6 && entry.getValue().getMonthValue() <= 8);

        personInfo.forEach((key, value) -> System.out.println(key + " - " + value));
    }

    public static void task16() {
        Map<String, String> fio = new HashMap<>();

        fio.put("Vlad", "Kobzev");
        fio.put("Ken", "Kens");
        fio.put("Yar", "Bielinskyi");
        fio.put("Jil", "Jerry");
        fio.put("Jool", "Kobzev");
        fio.put("John", "Kobzev");
        fio.put("Jin", "Kobzev");
        fio.put("Artur", "Nickon");
        fio.put("Ivan", "Kobzev");
        fio.put("David", "Simonov");

        HashMap<String, String> tmpMap1 = new HashMap<>(fio);
        HashMap<String, String> tmpMap2 = new HashMap<>(fio);

        for (Map.Entry<String, String> keyValueTmpMap1:tmpMap1.entrySet())
        {
            tmpMap2.remove(keyValueTmpMap1.getKey());
            if (tmpMap2.containsValue(keyValueTmpMap1.getValue()))
            {
                HashMap<String, String> copyFio = new HashMap<>(fio);

                for (Map.Entry<String, String> keyValueCopyFio:copyFio.entrySet())
                    if (keyValueCopyFio.getValue().equals(keyValueTmpMap1.getValue())) fio.remove(keyValueCopyFio.getKey());
            }
        }

        fio.forEach((key, value) -> System.out.println(key + " - " + value));
    }

    public static void task17() {
        Map<String, String> fio = new HashMap<>();

        fio.put("Vlad", "Kobzev");
        fio.put("Ken", "Kens");
        fio.put("Yar", "Bielinskyi");
        fio.put("Jil", "Bielinskyi");
        fio.put("Jil", "Bielinskyi");
        fio.put("Jil", "Bielinskyi");
        fio.put("Jin", "Bielinskyi");
        fio.put("Jil", "Nickon");
        fio.put("Ivan", "Stepanenko");
        fio.put("David", "Simonov");

        fio.forEach((key, value) -> System.out.println(key + " - " + value));
    }
}
