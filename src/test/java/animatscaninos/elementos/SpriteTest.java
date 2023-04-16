package animatscaninos.elementos;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class SpriteTest {
    private static final double X_PRUEBA = 100;

    private static final double Y_PRUEBA = 100;

    private static final Sprite SPRITE_PRUEBA =
            new SpriteImplementation(X_PRUEBA, Y_PRUEBA);

    static Stream<Arguments> testGetAngulo() {
        return Stream.of(
                Arguments.of(0.0, X_PRUEBA, Y_PRUEBA),
                Arguments.of(0.0, X_PRUEBA - 10, Y_PRUEBA),
                Arguments.of(Math.PI, X_PRUEBA + 10, Y_PRUEBA),
                Arguments.of(Math.PI / 2, X_PRUEBA, Y_PRUEBA - 10),
                Arguments.of(-Math.PI / 2, X_PRUEBA, Y_PRUEBA + 10),
                Arguments.of(Math.PI / 4, X_PRUEBA - 10, Y_PRUEBA - 10),
                Arguments.of(-Math.PI / 4, X_PRUEBA - 10, Y_PRUEBA + 10),
                Arguments.of(3 * Math.PI / 4, X_PRUEBA + 10, Y_PRUEBA - 10),
                Arguments.of(-3 * Math.PI / 4, X_PRUEBA + 10, Y_PRUEBA + 10)
        );
    }

    @ParameterizedTest
    @MethodSource
    void testGetAngulo(double anguloEsperado, double x, double y) {
        assertEquals(anguloEsperado, SPRITE_PRUEBA.getAngulo(x, y),
                0.0000000000005);
    }

    static Stream<Arguments> testGetDistancia() {
        return Stream.of(
                Arguments.of(0.0, X_PRUEBA, Y_PRUEBA),
                Arguments.of(10.0, X_PRUEBA - 10, Y_PRUEBA),
                Arguments.of(10.0, X_PRUEBA, Y_PRUEBA - 10),
                Arguments.of(10.0, X_PRUEBA + 10, Y_PRUEBA),
                Arguments.of(10.0, X_PRUEBA, Y_PRUEBA + 10),
                Arguments.of(5.0, X_PRUEBA - 3, Y_PRUEBA - 4),
                Arguments.of(5.0, X_PRUEBA + 3, Y_PRUEBA + 4),
                Arguments.of(5.0, X_PRUEBA + 3, Y_PRUEBA - 4),
                Arguments.of(5.0, X_PRUEBA - 3, Y_PRUEBA + 4),
                Arguments.of(6.71394072062004, X_PRUEBA - 5.23, Y_PRUEBA - 4.21),
                Arguments.of(8.15309143822146, X_PRUEBA + 3.15, Y_PRUEBA + 7.52),
                Arguments.of(10.2885664696303, X_PRUEBA - 0.89, Y_PRUEBA + 10.25),
                Arguments.of(67.3054425733908, X_PRUEBA + 23.01, Y_PRUEBA - 63.25)
        );
    }

    @ParameterizedTest
    @MethodSource
    void testGetDistancia(double distanciaEsperada, double x, double y) {
        assertEquals(distanciaEsperada, SPRITE_PRUEBA.getDistancia(x, y),
                0.0000000000005);
    }

    static Stream<Arguments> testGetDistanciaSprite() {
        return Stream.of(
                Arguments.of(0.0, new SpriteImplementation(X_PRUEBA, Y_PRUEBA)),
                Arguments.of(10.0, new SpriteImplementation(X_PRUEBA - 10, Y_PRUEBA)),
                Arguments.of(10.0, new SpriteImplementation(X_PRUEBA, Y_PRUEBA - 10)),
                Arguments.of(10.0, new SpriteImplementation(X_PRUEBA + 10, Y_PRUEBA)),
                Arguments.of(10.0, new SpriteImplementation(X_PRUEBA, Y_PRUEBA + 10)),
                Arguments.of(5.0, new SpriteImplementation(X_PRUEBA - 3, Y_PRUEBA - 4)),
                Arguments.of(5.0, new SpriteImplementation(X_PRUEBA + 3, Y_PRUEBA + 4)),
                Arguments.of(5.0, new SpriteImplementation(X_PRUEBA + 3, Y_PRUEBA - 4)),
                Arguments.of(5.0, new SpriteImplementation(X_PRUEBA - 3, Y_PRUEBA + 4)),
                Arguments.of(6.71394072062004, new SpriteImplementation(X_PRUEBA - 5.23, Y_PRUEBA - 4.21)),
                Arguments.of(8.15309143822146, new SpriteImplementation(X_PRUEBA + 3.15, Y_PRUEBA + 7.52)),
                Arguments.of(10.2885664696303, new SpriteImplementation(X_PRUEBA - 0.89, Y_PRUEBA + 10.25)),
                Arguments.of(67.3054425733908, new SpriteImplementation(X_PRUEBA + 23.01, Y_PRUEBA - 63.25))
        );
    }

    @ParameterizedTest
    @MethodSource
    void testGetDistanciaSprite(double distanciaEsperada, Sprite spriteTest) {
        assertEquals(distanciaEsperada, SPRITE_PRUEBA.getDistancia(spriteTest), 0.0000000000005);
    }
}
