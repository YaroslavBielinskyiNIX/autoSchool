package module5_6;

import static module5_6.Program.setFields;

public class Main13 {

    public static void main(String[] args) throws NoSuchFieldException, IllegalAccessException {
        Parent childOne = new ChildOne();
        Parent childTwo = new ChildTwo();

        setFields(childOne);
        setFields(childTwo);

        System.out.println(childOne.getName());
        System.out.println(childOne.getLastName());
    }
}
