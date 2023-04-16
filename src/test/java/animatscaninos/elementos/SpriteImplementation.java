package animatscaninos.elementos;

import java.awt.Color;
import java.awt.geom.Rectangle2D;
import java.awt.geom.RectangularShape;

public class SpriteImplementation implements Sprite {

    private RectangularShape contorno;

    public SpriteImplementation(double x, double y) {
        final double dimension = 4;

        contorno = new Rectangle2D.Double(
                x - dimension/2,
                y - dimension/2,
                dimension,
                dimension);
    }

    public RectangularShape getContorno() {
        return contorno;
    }

    public Color getColor() {
        return Color.WHITE;
    }
}
