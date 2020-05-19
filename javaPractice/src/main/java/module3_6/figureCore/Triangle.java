package module3_6.figureCore;

import static java.lang.String.format;

public class Triangle extends Figure {

    private String name;
    private int x1, y1;
    private int x2, y2;
    private int x3, y3;
    private double a, b, c, p;

    public Triangle(String name, int x1, int y1, int x2, int y2, int x3, int y3) {
        this.name = name;
        this.x1 = x1;
        this.y1 = y1;
        this.x2 = x2;
        this.y2 = y2;
        this.x3 = x3;
        this.y3 = y3;

        this.a = calculateSection(x1, y1, x2, y2);
        this.b = calculateSection(x2, y2, x3, y3);
        this.c = calculateSection(x3, y3, x1, y1);
        this.p = (a + b + c) / 2;
    }

    @Override
    public String getCoordinates() {
        return format("{%d, %d}, {%d, %d}, {%d, %d}", x1, y1, x2, y2, x3, y3);
    }

    @Override
    public double area() {
        return Math.sqrt(p * (p - a) * (p - b) * (p - c));
    }

    @Override
    public String toString() {
        return String.format("Name = %s, S = %f, a = %f, b = %f, c = %f", this.name, area(), a, b, c);
    }
}
