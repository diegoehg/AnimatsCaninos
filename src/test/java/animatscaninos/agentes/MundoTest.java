package animatscaninos.agentes;

import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class MundoTest {
    private final static double X_DEFAULT = 100;

    private final static double Y_DEFAULT = 200;

    @Test
    public void addingPlatoComida() {
        Mundo mundo = new Mundo();
        mundo.putPlatoComida(X_DEFAULT, Y_DEFAULT);
        assertEquals(1, mundo.getNumeroPlatosComida());
    }

    @Test
    public void addingPlatoAgua() {
        Mundo mundo = new Mundo();
        mundo.putPlatoAgua(X_DEFAULT, Y_DEFAULT);
        assertEquals(1, mundo.getNumeroPlatosAgua());
    }

    @Test
    public void removingPlatoComida() {
        Mundo mundo = new Mundo();
        mundo.putPlatoComida(X_DEFAULT, Y_DEFAULT);
        mundo.removePlatoComida(X_DEFAULT, Y_DEFAULT);
        assertEquals(0, mundo.getNumeroPlatosComida());
    }

    @Test
    public void removingPlatoAgua() {
        Mundo mundo = new Mundo();
        mundo.putPlatoAgua(X_DEFAULT, Y_DEFAULT);
        mundo.removePlatoAgua(X_DEFAULT, Y_DEFAULT);
        assertEquals(0, mundo.getNumeroPlatosAgua());
    }
}
