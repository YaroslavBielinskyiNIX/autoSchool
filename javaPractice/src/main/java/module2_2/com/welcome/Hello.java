package module2_2.com.welcome;

public class Hello {
    private String name;

    public void setupName(String name) {
        this.name = name;
    }

    public void welcome() {
        System.out.println("Hello, " + this.name);
    }

    public void byeBay() {
        System.out.println("Bye, " + this.name);
    }
}
