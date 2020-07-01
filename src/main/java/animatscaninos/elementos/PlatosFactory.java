package animatscaninos.elementos;

import static java.awt.Color.green;

public class PlatosFactory {
    public static Plato getPlatoComida(double x, double y) {
        return new Plato(x, y, green);
    }
}
