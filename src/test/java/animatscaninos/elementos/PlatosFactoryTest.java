package animatscaninos.elementos;

import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

public class PlatosFactoryTest {
    private final static double X_DEFAULT = 10;

    private final static double Y_DEFAULT = 20;

    private final static double DELTA_DEFAULT = 0.5;

    @Test
    public void gettingPlatoComida() {
        Plato plato = PlatosFactory.getPlatoComida(X_DEFAULT, Y_DEFAULT);
        assertTrue(plato.getContorno().contains(X_DEFAULT, Y_DEFAULT));
        assertEquals(X_DEFAULT, plato.getContorno().getCenterX(), DELTA_DEFAULT);
        assertEquals(Y_DEFAULT, plato.getContorno().getCenterY(), DELTA_DEFAULT);
        assertEquals(Plato.COLOR_COMIDA, plato.getColor());
    }

    @Test
    public void gettingPlatoAgua() {
        Plato plato = PlatosFactory.getPlatoAgua(X_DEFAULT, Y_DEFAULT);
        assertTrue(plato.getContorno().contains(X_DEFAULT, Y_DEFAULT));
        assertEquals(X_DEFAULT, plato.getContorno().getCenterX(), DELTA_DEFAULT);
        assertEquals(Y_DEFAULT, plato.getContorno().getCenterY(), DELTA_DEFAULT);
        assertEquals(Plato.COLOR_AGUA, plato.getColor());
    }
}
