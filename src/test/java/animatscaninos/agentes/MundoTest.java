package animatscaninos.agentes;

import animatscaninos.elementos.Plato;
import animatscaninos.elementos.PlatosFactory;

import animatscaninos.elementos.SpriteImplementation;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class MundoTest {
    private final static double X_DEFAULT = 100;

    private final static double Y_DEFAULT = 200;

    @Test
    void addingPlatoComida() {
        Mundo mundo = new Mundo();
        mundo.putPlatoComida(X_DEFAULT, Y_DEFAULT);
        assertEquals(1, mundo.getNumeroPlatosComida());
    }

    @Test
    void addingPlatoAgua() {
        Mundo mundo = new Mundo();
        mundo.putPlatoAgua(X_DEFAULT, Y_DEFAULT);
        assertEquals(1, mundo.getNumeroPlatosAgua());
    }

    @Test
    void removingPlatoComidaCoordenadas() {
        Mundo mundo = new Mundo();
        mundo.putPlatoComida(X_DEFAULT, Y_DEFAULT);
        mundo.removePlatoComida(X_DEFAULT, Y_DEFAULT);
        assertEquals(0, mundo.getNumeroPlatosComida());
    }

    @Test
    void removinPlatoComidaInstancia() {
        Mundo mundo = new Mundo();
        mundo.putPlatoComida(X_DEFAULT, Y_DEFAULT);

        Plato plato = PlatosFactory.getPlatoComida(X_DEFAULT, Y_DEFAULT);
        mundo.removePlatoComida(plato);

        assertEquals(0, mundo.getNumeroPlatosComida());
    }

    @Test
    void removingPlatoAguaCoordenadas() {
        Mundo mundo = new Mundo();
        mundo.putPlatoAgua(X_DEFAULT, Y_DEFAULT);
        mundo.removePlatoAgua(X_DEFAULT, Y_DEFAULT);
        assertEquals(0, mundo.getNumeroPlatosAgua());
    }

    @Test
    void removingPlatoAguaInstancia() {
        Mundo mundo = new Mundo();
        mundo.putPlatoAgua(X_DEFAULT, Y_DEFAULT);

        Plato plato = PlatosFactory.getPlatoAgua(X_DEFAULT, Y_DEFAULT);
        mundo.removePlatoAgua(plato);

        assertEquals(0, mundo.getNumeroPlatosAgua());
    }

    @Test
    void gettingPlatoComidaMasCercano() {
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
    void gettingPlatoComidaMasCercanoConSprite() {
        Mundo mundo = new Mundo();
        mundo.putPlatoComida(X_DEFAULT + 100, Y_DEFAULT);
        mundo.putPlatoComida(X_DEFAULT, Y_DEFAULT);
        mundo.putPlatoComida(X_DEFAULT + 100, Y_DEFAULT);

        Plato plato = mundo.getPlatoComidaMasCercano(new SpriteImplementation(0, 0));
        assertEquals(PlatosFactory.getPlatoComida(X_DEFAULT, Y_DEFAULT), plato);
    }

    @Test
    void gettingPlatoAguaMasCercano() {
        Mundo mundo = new Mundo();
        mundo.putPlatoAgua(X_DEFAULT + 100, Y_DEFAULT);
        mundo.putPlatoAgua(X_DEFAULT, Y_DEFAULT);
        mundo.putPlatoAgua(X_DEFAULT + 100, Y_DEFAULT);

        Plato plato = mundo.getPlatoAguaMasCercano(0, 0);
        assertEquals(
                PlatosFactory.getPlatoAgua(X_DEFAULT, Y_DEFAULT),
                plato);
    }

    @Test
    void gettingPlatoAguaMasCercanoConSprite() {
        Mundo mundo = new Mundo();
        mundo.putPlatoAgua(X_DEFAULT + 100, Y_DEFAULT);
        mundo.putPlatoAgua(X_DEFAULT, Y_DEFAULT);
        mundo.putPlatoAgua(X_DEFAULT + 100, Y_DEFAULT);

        Plato plato = mundo.getPlatoAguaMasCercano(new SpriteImplementation(0, 0));
        assertEquals(PlatosFactory.getPlatoAgua(X_DEFAULT, Y_DEFAULT), plato);
    }
}
