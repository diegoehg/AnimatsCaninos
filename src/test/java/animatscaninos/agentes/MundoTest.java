package animatscaninos.agentes;

import animatscaninos.elementos.Plato;
import animatscaninos.elementos.PlatosFactory;

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
    public void removingPlatoComidaCoordenadas() {
        Mundo mundo = new Mundo();
        mundo.putPlatoComida(X_DEFAULT, Y_DEFAULT);
        mundo.removePlatoComida(X_DEFAULT, Y_DEFAULT);
        assertEquals(0, mundo.getNumeroPlatosComida());
    }

    @Test
    public void removinPlatoComidaInstancia() {
        Mundo mundo = new Mundo();
        mundo.putPlatoComida(X_DEFAULT, Y_DEFAULT);

        Plato plato = PlatosFactory.getPlatoComida(X_DEFAULT, Y_DEFAULT);
        mundo.removePlatoComida(plato);

        assertEquals(0, mundo.getNumeroPlatosComida());
    }

    @Test
    public void removingPlatoAguaCoordenadas() {
        Mundo mundo = new Mundo();
        mundo.putPlatoAgua(X_DEFAULT, Y_DEFAULT);
        mundo.removePlatoAgua(X_DEFAULT, Y_DEFAULT);
        assertEquals(0, mundo.getNumeroPlatosAgua());
    }

    @Test
    public void removingPlatoAguaInstancia() {
        Mundo mundo = new Mundo();
        mundo.putPlatoAgua(X_DEFAULT, Y_DEFAULT);

        Plato plato = PlatosFactory.getPlatoAgua(X_DEFAULT, Y_DEFAULT);
        mundo.removePlatoAgua(plato);

        assertEquals(0, mundo.getNumeroPlatosAgua());
    }

    @Test
    public void gettingPlatoComidaMasCercano() {
        Mundo mundo = new Mundo();
        mundo.putPlatoComida(X_DEFAULT + 100, Y_DEFAULT);
        mundo.putPlatoComida(X_DEFAULT, Y_DEFAULT);
        mundo.putPlatoComida(X_DEFAULT + 100, Y_DEFAULT);

        Plato plato = mundo.getPlatoComidaMasCercano(0, 0);
        assertEquals(
                PlatosFactory.getPlatoComida(X_DEFAULT, Y_DEFAULT),
                plato);
    }

    @Test
    public void gettingPlatoAguaMasCercano() {
        Mundo mundo = new Mundo();
        mundo.putPlatoAgua(X_DEFAULT + 100, Y_DEFAULT);
        mundo.putPlatoAgua(X_DEFAULT, Y_DEFAULT);
        mundo.putPlatoAgua(X_DEFAULT + 100, Y_DEFAULT);

        Plato plato = mundo.getPlatoAguaMasCercano(0, 0);
        assertEquals(
                PlatosFactory.getPlatoAgua(X_DEFAULT, Y_DEFAULT),
                plato);
    }
}
