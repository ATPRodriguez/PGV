package es.ies.puerto.ejercicio6;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

import static org.junit.jupiter.api.Assertions.assertTrue;

public class TardisTest {

    @Test
    public void tardisTest() throws InterruptedException {
        ByteArrayOutputStream outContent = new ByteArrayOutputStream();
        System.setOut(new PrintStream(outContent));

        Thread thread1 = new Thread(new Tardis("Epoca Prehistorica"));
        Thread thread2 = new Thread(new Tardis("Epoca Antigua"));

        thread1.start();
        thread2.start();

        thread1.join();
        thread2.join();

        String output = outContent.toString();
        assertTrue(output.contains("La TARDIS ha llegado a la "));
    }

}