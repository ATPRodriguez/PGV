package es.ies.puerto.ejercicio8;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.util.Random;

public class ThorHulkTest {

    @Test
    public void ThorHulkTest() throws InterruptedException {
        ByteArrayOutputStream outContent = new ByteArrayOutputStream();
        System.setOut(new PrintStream(outContent));

        Random random = new Random();
        long tiempoCompeticion = ((random.nextInt(10) + 5) * 1000);
        Thread thor = new Thread(new ThorHulk("Thor", tiempoCompeticion));
        Thread hulk = new Thread(new ThorHulk("Hulk", tiempoCompeticion));

        thor.start();
        hulk.start();

        thor.join();
        hulk.join();

        String output = outContent.toString();
        Assertions.assertTrue(output.contains("Hulk ha ganado la competicion")
                || output.contains("Thor ha ganado la competicion")
                || output.contains("Thor y Hulk han levantado las mismas pesas"));
    }
}
