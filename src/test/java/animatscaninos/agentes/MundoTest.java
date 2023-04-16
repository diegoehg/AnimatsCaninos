package animatscaninos.agentes;

import animatscaninos.elementos.Plato;
import animatscaninos.elementos.PlatosFactory;

import animatscaninos.elementos.SpriteImplementation;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class MundoTest {
    private final static double X_DEFAULT = 100;

    private final static double Y_DEFAULT = 200;

    private final static double RANGE_DEFAULT = 5;

    private final static double SIDE_A = 3;

    private final static double SIDE_B = 4;

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

        Plato plato = mundo.getPlatoComidaMasCercano(new SpriteImplementation(0, 0));
        assertEquals(PlatosFactory.getPlatoComida(X_DEFAULT, Y_DEFAULT), plato);
    }

    @Test
    void gettingPlatoAguaMasCercano() {
        Mundo mundo = new Mundo();
        mundo.putPlatoAgua(X_DEFAULT + 100, Y_DEFAULT);
        mundo.putPlatoAgua(X_DEFAULT, Y_DEFAULT);
        mundo.putPlatoAgua(X_DEFAULT + 100, Y_DEFAULT);

        Plato plato = mundo.getPlatoAguaMasCercano(new SpriteImplementation(0, 0));
        assertEquals(PlatosFactory.getPlatoAgua(X_DEFAULT, Y_DEFAULT), plato);
    }

    @ParameterizedTest
    @MethodSource("getTestParametersForPlatoWithinRange")
    void testIsPlatoComidaWithinRange(boolean expected, double x, double y, double range) {
        Mundo mundo = new Mundo();
        mundo.putPlatoComida(X_DEFAULT, Y_DEFAULT);
        assertEquals(expected, mundo.isPlatoComidaWithinRange(x, y, range));
    }

    @ParameterizedTest
    @MethodSource("getTestParametersForPlatoWithinRange")
    void testIsPlatoAguaWithinRange(boolean expected, double x, double y, double range) {
        Mundo mundo = new Mundo();
        mundo.putPlatoAgua(X_DEFAULT, Y_DEFAULT);
        assertEquals(expected, mundo.isPlatoAguaWithinRange(x, y, range));
    }

    static Stream<Arguments> getTestParametersForPlatoWithinRange() {
        return Stream.of(
                Arguments.of(true, X_DEFAULT, Y_DEFAULT, RANGE_DEFAULT),

                Arguments.of(true, X_DEFAULT - RANGE_DEFAULT, Y_DEFAULT, RANGE_DEFAULT),
                Arguments.of(true, X_DEFAULT + RANGE_DEFAULT, Y_DEFAULT, RANGE_DEFAULT),
                Arguments.of(true, X_DEFAULT, Y_DEFAULT - RANGE_DEFAULT, RANGE_DEFAULT),
                Arguments.of(true, X_DEFAULT, Y_DEFAULT + RANGE_DEFAULT, RANGE_DEFAULT),

                Arguments.of(false, X_DEFAULT - RANGE_DEFAULT - 1, Y_DEFAULT, RANGE_DEFAULT),
                Arguments.of(false, X_DEFAULT + RANGE_DEFAULT + 1, Y_DEFAULT, RANGE_DEFAULT),
                Arguments.of(false, X_DEFAULT, Y_DEFAULT - RANGE_DEFAULT - 1, RANGE_DEFAULT),
                Arguments.of(false, X_DEFAULT, Y_DEFAULT + RANGE_DEFAULT + 1, RANGE_DEFAULT),

                Arguments.of(true, X_DEFAULT - SIDE_A, Y_DEFAULT - SIDE_B, RANGE_DEFAULT),
                Arguments.of(true, X_DEFAULT + SIDE_A, Y_DEFAULT + SIDE_B, RANGE_DEFAULT),
                Arguments.of(true, X_DEFAULT + SIDE_A, Y_DEFAULT - SIDE_B, RANGE_DEFAULT),
                Arguments.of(true, X_DEFAULT - SIDE_A, Y_DEFAULT + SIDE_B, RANGE_DEFAULT),

                Arguments.of(false, X_DEFAULT - SIDE_A - 1, Y_DEFAULT - SIDE_B, RANGE_DEFAULT),
                Arguments.of(false, X_DEFAULT + SIDE_A, Y_DEFAULT + SIDE_B + 1, RANGE_DEFAULT),
                Arguments.of(false, X_DEFAULT + SIDE_A + 1, Y_DEFAULT - SIDE_B, RANGE_DEFAULT),
                Arguments.of(false, X_DEFAULT - SIDE_A, Y_DEFAULT + SIDE_B + 1, RANGE_DEFAULT),

                Arguments.of(true, X_DEFAULT - SIDE_B, Y_DEFAULT - SIDE_A, RANGE_DEFAULT),
                Arguments.of(true, X_DEFAULT + SIDE_B, Y_DEFAULT + SIDE_A, RANGE_DEFAULT),
                Arguments.of(true, X_DEFAULT + SIDE_B, Y_DEFAULT - SIDE_A, RANGE_DEFAULT),
                Arguments.of(true, X_DEFAULT - SIDE_B, Y_DEFAULT + SIDE_A, RANGE_DEFAULT)
        );
    }
}
