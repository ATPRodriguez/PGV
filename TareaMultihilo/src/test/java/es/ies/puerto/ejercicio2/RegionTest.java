package es.ies.puerto.ejercicio2;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class RegionTest {
    public static final String ERROR_MSG = "NO SE HA OBTENIDO EL RESULTADO ESPERADO";
    Region region;
    String nombreTest;
    @BeforeEach
    public void beforeEach() {
        region = new Region();
        nombreTest = "nombreTest";
    }

    @Test
    public void constructoresTest() {
        Assertions.assertNotNull(region);
        region = new Region(nombreTest);
        Assertions.assertNotNull(region);
        region = new Region(nombreTest, true);
        Assertions.assertNotNull(region);
        region = new Region(nombreTest, false);
    }

    @Test
    public void getterSetterTest() {
        region = new Region(nombreTest);
        region.setHorrocrux(true);
        Assertions.assertEquals(region.getNombre(), nombreTest, ERROR_MSG);
        Assertions.assertEquals(region.getHorrocrux(), true);
    }
}
