package module5_6;

import java.lang.reflect.Field;

public class Parent {

    public String getName() throws NoSuchFieldException, IllegalAccessException {
        Field name = this.getClass().getDeclaredField("name");
        name.setAccessible(true);
        return name.getName() + " = " + name.get(this);
    }

    public String getLastName() throws NoSuchFieldException, IllegalAccessException {
        Field lastName = this.getClass().getDeclaredField("lastName");
        lastName.setAccessible(true);
        return lastName.getName() + " = " + lastName.get(this);
    }
}
