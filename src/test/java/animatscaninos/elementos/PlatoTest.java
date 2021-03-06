package animatscaninos.elementos;

import org.junit.Test;

import static org.junit.Assert.assertTrue;

public class PlatoTest {
    @Test
    public void testEquals() {
        final double centerX = 78, centerY = 96;
        Plato plato1 = PlatosFactory.getPlatoAgua(centerX, centerY),
                plato2 = PlatosFactory.getPlatoAgua(centerX, centerY);
        assertTrue(plato1.equals(plato2));
    }
}
