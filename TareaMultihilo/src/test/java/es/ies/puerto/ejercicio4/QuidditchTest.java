package es.ies.puerto.ejercicio4;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

import static org.junit.jupiter.api.Assertions.assertTrue;

public class QuidditchTest {

    @Test
    public void quidditchTest() throws InterruptedException {
        ByteArrayOutputStream outContent = new ByteArrayOutputStream();
        System.setOut(new PrintStream(outContent));

        Thread jugador1 = new Thread(new Quidditch("jugador1", false));
        Thread jugador2 = new Thread(new Quidditch("jugador2", false));
        Thread jugador3 = new Thread(new Quidditch("jugador3", false));

        jugador1.start();
        jugador2.start();
        jugador3.start();

        jugador1.join();
        jugador2.join();
        jugador3.join();

        String output = outContent.toString();
        assertTrue(output.contains("ha ganado"));
    }
}