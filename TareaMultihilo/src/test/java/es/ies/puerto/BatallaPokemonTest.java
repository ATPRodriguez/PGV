package es.ies.puerto;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class BatallaPokemonTest {

    @Test
    public void batallaPokemonTest() throws InterruptedException {
        ByteArrayOutputStream outContent = new ByteArrayOutputStream();
        System.setOut(new PrintStream(outContent));

        Thread pikachu = new Thread(new BatallaPokemon("Pikachu"));
        Thread charmander = new Thread(new BatallaPokemon("Charmander"));

        pikachu.start();
        charmander.start();

        pikachu.join();
        charmander.join();

        String output = outContent.toString();
        assertTrue(output.contains("Pikachu ha sido derrotado!") || output.contains("Charmander ha sido derrotado!"));
    }
}