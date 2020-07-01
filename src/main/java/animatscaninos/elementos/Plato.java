package animatscaninos.elementos;

import java.awt.Color;
import java.awt.geom.Ellipse2D;

/**
 * Características de un plato.
 *
 * @author Diego Hdez.
 */
class Plato {
    final static Color COLOR_AGUA = Color.blue;

    final static Color COLOR_COMIDA = Color.green;

    private final static double DIMENSION_PLATO = 30;

    private Ellipse2D contorno;

    private Color color;

    Plato(double centerX, double centerY, Color color) {
        contorno = new Ellipse2D.Double(
                centerX - (DIMENSION_PLATO /2),
                centerY - (DIMENSION_PLATO /2),
                DIMENSION_PLATO,
                DIMENSION_PLATO);
        this.color = color;
    }

    public Ellipse2D getContorno() {
        return contorno;
    }

    public Color getColor() {
        return color;
    }
}
