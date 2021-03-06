package animatscaninos.elementos;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;

import java.util.Arrays;
import java.util.Collection;

import static org.junit.Assert.assertEquals;

@RunWith(Parameterized.class)
public class PlatoAnguloTest {
    private static final double X_PRUEBA = 100;

    private static final double Y_PRUEBA = 100;

    private static final Plato PLATO_PRUEBA =
            PlatosFactory.getPlatoAgua(X_PRUEBA, Y_PRUEBA);

    private double anguloEsperado, x, y;

    @Parameterized.Parameters
    public static Collection<Double[]> getTestParameters() {
        return Arrays.asList(new Double[][]{
                {0.0, X_PRUEBA, Y_PRUEBA},
                {0.0, X_PRUEBA - 10, Y_PRUEBA},
                {Math.PI, X_PRUEBA + 10, Y_PRUEBA},
                {Math.PI/2, X_PRUEBA, Y_PRUEBA - 10},
                {-Math.PI/2, X_PRUEBA, Y_PRUEBA + 10},
                {Math.PI/4, X_PRUEBA - 10 , Y_PRUEBA - 10},
                {-Math.PI/4, X_PRUEBA - 10 , Y_PRUEBA + 10},
                {3 * Math.PI/4, X_PRUEBA + 10 , Y_PRUEBA - 10},
                {-3 * Math.PI/4, X_PRUEBA + 10 , Y_PRUEBA + 10}
        });
    }

    public PlatoAnguloTest(double anguloEsperado, double x, double y) {
        this.anguloEsperado = anguloEsperado;
        this.x = x;
        this.y = y;
    }

    @Test
    public void testGetAngulo() {
        assertEquals(anguloEsperado, PLATO_PRUEBA.getAngulo(x, y),
                0.0000000000005);
    }
}
