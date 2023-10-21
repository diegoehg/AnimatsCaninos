package animatscaninos.elementos;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class PlatoTest {
    @Test
    public void testEquals() {
        final double centerX = 78, centerY = 96;
        Plato plato1 = PlatosFactory.getPlatoAgua(centerX, centerY),
                plato2 = PlatosFactory.getPlatoAgua(centerX, centerY);
        assertEquals(plato1, plato2);
    }
}
