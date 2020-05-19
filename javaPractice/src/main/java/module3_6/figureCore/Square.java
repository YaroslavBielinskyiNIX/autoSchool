package module3_6.figureCore;

import static java.lang.String.format;

public class Square extends Figure {

    private String name;
    private int x1, y1;
    private int x2, y2;

    public Square(String name, int x1, int y1, int x2, int y2) {
        this.name = name;
        this.x1 = x1;
        this.y1 = y1;
        this.x2 = x2;
        this.y2 = y2;
    }

    @Override
    public String getCoordinates() {
        return format("{%d, %d}, {%d, %d}", x1, y1, x2, y2);
    }

    @Override
    public double area() {
        return Math.pow(calculateSection(x1, y1, x2, y2), 2);
    }

    @Override
    public String toString() {
        return format("Name = %s, S = %f, side = %f", this.name, area(), calculateSection(x1, y1, x2, y2),  calculateSection(x1, y1, x2, y2));
    }
}
