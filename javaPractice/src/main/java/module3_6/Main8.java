package module3_6;

import module3_6.figureCore.Circle;
import module3_6.figureCore.Figure;
import module3_6.figureCore.Square;
import module3_6.figureCore.Triangle;

import java.util.ArrayList;
import java.util.List;

import static java.lang.Math.random;

public class Main8 {

    public static void main(String[] args) {
        List<Figure> defaultFigures = new ArrayList<>(3);
        defaultFigures.add(new Circle("Circle", 0, 0, 0, 4));
        defaultFigures.add(new Square("Square", 0, 0, 0, 4));
        defaultFigures.add(new Triangle("Triggered", 0, 0, 0, 4, 4, 0));

        Figure[] figures = new Figure[10];
        for (int i = 0; i < 10; i++) figures[i] = defaultFigures.get((int) (random() * 3));

        for (int i = 9; i > 0; i--) {
            for (int j = 0; j < i; j++) {
                if (figures[j].area() > figures[j + 1].area()) {
                    Figure tmp = figures[j];
                    figures[j] = figures[j + 1];
                    figures[j + 1] = tmp;
                }
            }
        }

        for (int i = 0; i < 10; i++) System.out.println(figures[i].toString());
    }
}
