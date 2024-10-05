package es.ies.puerto.ejercicio3;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertTrue;
import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

public class FabricaDroidesTest {

    @Test
    public void droidFactoryTest() throws InterruptedException {
        ByteArrayOutputStream outContent = new ByteArrayOutputStream();
        System.setOut(new PrintStream(outContent));

        FabricaDroides.droides.add(new Droide("1"));
        FabricaDroides.droides.add(new Droide("2"));
        FabricaDroides.droides.add(new Droide("3"));

        Thread ensamblador = new Thread(new FabricaDroides("ensamblador"));
        Thread activador = new Thread(new FabricaDroides("activador"));

        ensamblador.start();
        ensamblador.join();
        activador.start();
        activador.join();

        String output = outContent.toString();
        assertTrue(output.contains(" ha sido ensamblado") || output.contains(" ha sido activado"));
    }

}