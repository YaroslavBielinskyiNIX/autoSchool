package module4_4;

public class Main10 {

    public static void main(String[] args) throws InterruptedException {
        ExceptionTest1.rest();
        ExceptionTest2.rest();

        try {
            int zero = 5/0;
        } catch (ArithmeticException e) {
            System.out.println("You shall not fall!");
        }
    }
}
