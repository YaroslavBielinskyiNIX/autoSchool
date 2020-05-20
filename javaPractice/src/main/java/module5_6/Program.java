package module5_6;

import java.lang.reflect.Field;

public class Program {

    public static void setFields(Object object) throws NoSuchFieldException, IllegalAccessException {
        Field name = object.getClass().getDeclaredField("name");
        name.setAccessible(true);
        name.set(object, "Yaroslav");

        Field lastName = object.getClass().getDeclaredField("lastName");
        lastName.setAccessible(true);
        lastName.set(object, "Bielinskyi");
    }
}
