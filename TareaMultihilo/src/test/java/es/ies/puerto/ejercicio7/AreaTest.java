package es.ies.puerto.ejercicio7;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class AreaTest {
    Area area;
    String nombreTest;

    @BeforeEach
    public void beforeEach() {
        area = new Area();
        nombreTest = "nombreTest";
    }

    @Test
    public void constructoresTest() {
        Assertions.assertNotNull(area);
        area = new Area(nombreTest);
        Assertions.assertNotNull(area);
    }

    @Test
    public void getterSetterTest() {
        area = new Area();
        area.setSaved(true);
        Assertions.assertEquals(area.isSaved(), true);
    }

    @Test
    public void equalsTest() {
        Area region1 = new Area(nombreTest);
        Area region2 = new Area(nombreTest);
        Area region3 = new Area("nombre");
        Assertions.assertEquals(region1, region2);
        Assertions.assertNotEquals(region1, region3);
        Assertions.assertNotEquals(region1, "nombre");
    }
}
