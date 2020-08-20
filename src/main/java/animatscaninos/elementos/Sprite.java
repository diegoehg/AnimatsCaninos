package animatscaninos.elementos;

import java.awt.Color;
import java.awt.geom.RectangularShape;

public interface Sprite {
    RectangularShape getContorno();

    Color getColor();

    default double getDistancia(double x, double y) {
        return Math.hypot(
                getContorno().getCenterX() - x,
                getContorno().getCenterY() - y);
    }

    default double getAngulo(double x, double y) {
        return Math.atan2(
                getContorno().getCenterY() - y,
                getContorno().getCenterX() - x);
    }
}
