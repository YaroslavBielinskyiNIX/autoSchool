package module3_6.figureCore;

import static java.lang.Math.PI;
import static java.lang.Math.pow;
import static java.lang.String.format;

public class Circle extends Figure {

    private int xCenter, yCenter;
    private int xCircuit, yCircuit;
    private String name;

    public Circle(String name, int xCenter, int yCenter, int xCircuit, int yCircuit) {
        this.name = name;
        this.xCenter = xCenter;
        this.yCenter = yCenter;
        this.xCircuit = xCircuit;
        this.yCircuit = yCircuit;
    }

    @Override
    public String getCoordinates() {
        return format("{%d, %d}, {%d, %d}", xCenter, yCenter, xCircuit, yCircuit);
    }

    @Override
    public double area() {
        return (PI * pow(calculateSection(xCenter, yCenter, xCircuit, yCircuit), 2));
    }

    @Override
    public String toString() {
        return String.format("Name = %s, S = %f, R = %f", this.name, area(), calculateSection(xCenter, yCenter, xCircuit, yCircuit));
    }
}
