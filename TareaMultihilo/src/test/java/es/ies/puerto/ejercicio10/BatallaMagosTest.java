package es.ies.puerto.ejercicio10;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

public class BatallaMagosTest {

    @Test
    public void batallaMagosTest() throws InterruptedException {
        ByteArrayOutputStream outContent = new ByteArrayOutputStream();
        System.setOut(new PrintStream(outContent));

        Thread gandalf = new Thread(new BatallaMagos("Gandalf"));
        Thread saruman = new Thread(new BatallaMagos("Saruman"));

        gandalf.start();
        saruman.start();

        gandalf.join();
        saruman.join();

        String output = outContent.toString();
        Assertions.assertTrue(output.contains("ha sido derrotado"));
    }
}
