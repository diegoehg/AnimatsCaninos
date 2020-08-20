package animatscaninos.elementos;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;

import java.util.Arrays;
import java.util.Collection;

import static org.junit.Assert.assertEquals;

@RunWith(Parameterized.class)
public class SpriteDistanciaTest {
    private static final double X_PRUEBA = 100;

    private static final double Y_PRUEBA = 100;

    private static final Sprite PLATO_PRUEBA =
            new SpriteImplementation(X_PRUEBA, Y_PRUEBA);

    private double distanciaEsperada, x, y;

    @Parameterized.Parameters
    public static Collection<Double[]> getTestParameters() {
        return Arrays.asList(new Double[][]{
                {0.0, X_PRUEBA, Y_PRUEBA},
                {10.0, X_PRUEBA - 10, Y_PRUEBA},
                {10.0, X_PRUEBA, Y_PRUEBA - 10},
                {10.0, X_PRUEBA + 10, Y_PRUEBA},
                {10.0, X_PRUEBA, Y_PRUEBA + 10},
                {5.0, X_PRUEBA - 3, Y_PRUEBA - 4},
                {5.0, X_PRUEBA + 3, Y_PRUEBA + 4},
                {5.0, X_PRUEBA + 3, Y_PRUEBA - 4},
                {5.0, X_PRUEBA - 3, Y_PRUEBA + 4},
                {6.71394072062004, X_PRUEBA - 5.23, Y_PRUEBA - 4.21},
                {8.15309143822146, X_PRUEBA + 3.15, Y_PRUEBA + 7.52},
                {10.2885664696303, X_PRUEBA - 0.89, Y_PRUEBA + 10.25},
                {67.3054425733908, X_PRUEBA + 23.01, Y_PRUEBA - 63.25}
        });
    }

    public SpriteDistanciaTest(double distanciaEsperada, double x, double y) {
        this.distanciaEsperada = distanciaEsperada;
        this.x = x;
        this.y = y;
    }

    @Test
    public void testGetDistancia() {
        assertEquals(distanciaEsperada, PLATO_PRUEBA.getDistancia(x, y),
                0.0000000000005);
    }
}
