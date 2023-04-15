package animatscaninos.elementos;

import java.awt.Color;
import java.awt.geom.Ellipse2D;
import java.awt.geom.RectangularShape;

/**
 * Características de un plato.
 *
 * @author Diego Hdez.
 */
public class Plato implements Sprite {
    final static Color COLOR_AGUA = Color.blue;

    final static Color COLOR_COMIDA = Color.green;

    public final static double DIMENSION_PLATO = 30;

    private final Ellipse2D contorno;

    private final Color color;

    Plato(double centerX, double centerY, Color color) {
        contorno = new Ellipse2D.Double(
                centerX - (DIMENSION_PLATO /2),
                centerY - (DIMENSION_PLATO /2),
                DIMENSION_PLATO,
                DIMENSION_PLATO);
        this.color = color;
    }

    public RectangularShape getContorno() {
        return contorno;
    }

    public Color getColor() {
        return color;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Plato plato = (Plato) o;

        if (!contorno.equals(plato.contorno)) return false;
        return color.equals(plato.color);
    }

    @Override
    public int hashCode() {
        int result = contorno.hashCode();
        result = 31 * result + color.hashCode();
        return result;
    }
}
