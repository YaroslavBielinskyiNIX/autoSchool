package module4_4;

import static java.lang.Thread.sleep;

public class ExceptionTest2 {

    public static void rest() {
        System.out.println("ExceptionTest2.sleep() called");

        try {
            sleep(3000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
