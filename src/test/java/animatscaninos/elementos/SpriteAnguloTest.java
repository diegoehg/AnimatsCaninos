package animatscaninos.elementos;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class SpriteAnguloTest {
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
    public void testGetAngulo(double anguloEsperado, double x, double y) {
        assertEquals(anguloEsperado, SPRITE_PRUEBA.getAngulo(x, y),
                0.0000000000005);
    }
}
