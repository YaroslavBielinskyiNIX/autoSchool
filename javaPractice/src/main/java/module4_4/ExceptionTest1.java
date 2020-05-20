package module4_4;

import static java.lang.Thread.sleep;

public class ExceptionTest1 {

    public static void rest() throws InterruptedException {
        System.out.println("ExceptionTest1.sleep() called");
        sleep(3000);
    }
}
