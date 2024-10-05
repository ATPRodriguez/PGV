package es.ies.puerto.ejercicio9;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

public class MilleniumFalconTest {

    @Test
    public void milleniumFalconTest() throws InterruptedException {
        ByteArrayOutputStream outContent = new ByteArrayOutputStream();
        System.setOut(new PrintStream(outContent));

        Thread hanSolo = new Thread(new MilleniumFalcon("Han Solo"));
        Thread chewbacca = new Thread(new MilleniumFalcon("Chewbacca"));

        hanSolo.start();
        chewbacca.start();

        hanSolo.join();
        chewbacca.join();

        String output = outContent.toString();
        Assertions.assertTrue(output.contains("la nave ha sido destruida")
                || output.contains("El Halcon milenario ha escapado del campo de batalla"));
    }
}
