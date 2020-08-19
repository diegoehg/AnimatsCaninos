package animatscaninos.agentes;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;

import java.util.Arrays;
import java.util.Collection;

import static org.junit.Assert.assertEquals;

@RunWith(Parameterized.class)
public class MundoPlatoWithinRangeTest {
    private final static double X_DEFAULT = 100;

    private final static double Y_DEFAULT = 100;

    private final static double RANGE_DEFAULT = 5;

    private final static double SIDE_A = 3;

    private final static double SIDE_B = 4;

    private boolean expected;

    private double x, y, range;

    @Parameterized.Parameters
    public static Collection<Object[]> getTestParameters() {
        return Arrays.asList(new Object[][]{
                {true, X_DEFAULT, Y_DEFAULT, RANGE_DEFAULT},

                {true, X_DEFAULT - RANGE_DEFAULT, Y_DEFAULT, RANGE_DEFAULT},
                {true, X_DEFAULT + RANGE_DEFAULT, Y_DEFAULT, RANGE_DEFAULT},
                {true, X_DEFAULT, Y_DEFAULT - RANGE_DEFAULT, RANGE_DEFAULT},
                {true, X_DEFAULT, Y_DEFAULT + RANGE_DEFAULT, RANGE_DEFAULT},

                {false, X_DEFAULT - RANGE_DEFAULT - 1, Y_DEFAULT, RANGE_DEFAULT},
                {false, X_DEFAULT + RANGE_DEFAULT + 1, Y_DEFAULT, RANGE_DEFAULT},
                {false, X_DEFAULT, Y_DEFAULT - RANGE_DEFAULT - 1, RANGE_DEFAULT},
                {false, X_DEFAULT, Y_DEFAULT + RANGE_DEFAULT + 1, RANGE_DEFAULT},

                {true, X_DEFAULT - SIDE_A, Y_DEFAULT - SIDE_B, RANGE_DEFAULT},
                {true, X_DEFAULT + SIDE_A, Y_DEFAULT + SIDE_B, RANGE_DEFAULT},
                {true, X_DEFAULT + SIDE_A, Y_DEFAULT - SIDE_B, RANGE_DEFAULT},
                {true, X_DEFAULT - SIDE_A, Y_DEFAULT + SIDE_B, RANGE_DEFAULT},

                {false, X_DEFAULT - SIDE_A - 1, Y_DEFAULT - SIDE_B, RANGE_DEFAULT},
                {false, X_DEFAULT + SIDE_A, Y_DEFAULT + SIDE_B + 1, RANGE_DEFAULT},
                {false, X_DEFAULT + SIDE_A + 1, Y_DEFAULT - SIDE_B, RANGE_DEFAULT},
                {false, X_DEFAULT - SIDE_A, Y_DEFAULT + SIDE_B + 1, RANGE_DEFAULT},

                {true, X_DEFAULT - SIDE_B, Y_DEFAULT - SIDE_A, RANGE_DEFAULT},
                {true, X_DEFAULT + SIDE_B, Y_DEFAULT + SIDE_A, RANGE_DEFAULT},
                {true, X_DEFAULT + SIDE_B, Y_DEFAULT - SIDE_A, RANGE_DEFAULT},
                {true, X_DEFAULT - SIDE_B, Y_DEFAULT + SIDE_A, RANGE_DEFAULT},
        });
    }

    public MundoPlatoWithinRangeTest(boolean expected, double x, double y, double range) {
        this.expected = expected;
        this.x = x;
        this.y = y;
        this.range = range;
    }

    @Test
    public void testIsPlatoComidaWithinRange() {
        Mundo mundo = new Mundo();
        mundo.putPlatoComida(X_DEFAULT, Y_DEFAULT);
        assertEquals(expected, mundo.isPlatoComidaWithinRange(x, y, range));
    }
}
