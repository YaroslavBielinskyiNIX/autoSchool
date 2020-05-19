package module3_6.figureCore;

import static java.lang.Math.pow;
import static java.lang.Math.sqrt;

public abstract class Figure {

    public abstract String getCoordinates();
    public abstract double area();
    public abstract String toString();

    public double calculateSection(int x1, int y1, int x2, int y2) {
        return (sqrt(pow((x2 - x1), 2) + pow((y2 - y1), 2)));
    }
}
