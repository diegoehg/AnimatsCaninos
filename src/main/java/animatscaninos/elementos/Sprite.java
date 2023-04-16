package animatscaninos.elementos;

import java.awt.Color;
import java.awt.geom.RectangularShape;

public interface Sprite {
    RectangularShape getContorno();

    Color getColor();

    default double getDistancia(Sprite sprite) {
        return Math.hypot(
                getContorno().getCenterX() - sprite.getContorno().getCenterX(),
                getContorno().getCenterY() - sprite.getContorno().getCenterY());
    }

    default double getAngulo(Sprite sprite) {
        return Math.atan2(
                getContorno().getCenterY() - sprite.getContorno().getCenterY(),
                getContorno().getCenterX() - sprite.getContorno().getCenterX());
    }
}
