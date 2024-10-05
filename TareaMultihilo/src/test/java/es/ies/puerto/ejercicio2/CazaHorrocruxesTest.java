package es.ies.puerto.ejercicio2;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.util.Random;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class CazaHorrocruxesTest {
    Random random;
    Region azkhaban;
    Region mansion;
    Region hogwarts;

    @BeforeEach
    public void beforeEach () {
        random = new Random();

        azkhaban = new Region("Azkhaban");
        mansion = new Region("Mansion Malfoy");
        hogwarts = new Region("Hogwarts", true);
    }

    @Test
    public void cazaHorrocruxesTest() throws InterruptedException {
        ByteArrayOutputStream outContent = new ByteArrayOutputStream();
        System.setOut(new PrintStream(outContent));

        Thread hermione = new Thread(new CazaHorrocruxes("Hermione Granger", mansion,
                (random.nextInt(10) +1) * 1000));
        Thread ron = new Thread(new CazaHorrocruxes("Ron Wisley", azkhaban,
                (random.nextInt(10) +1) * 1000));
        Thread harry = new Thread(new CazaHorrocruxes("Harry Potter", hogwarts,
                (random.nextInt(10) +1) * 1000));

        hermione.start();
        ron.start();
        harry.start();

        hermione.join();
        ron.join();
        harry.join();

        String output = outContent.toString();
        assertTrue(output.contains("se ha cansado de buscar") || output.contains("ha encontrado un horrocrux!"));
    }
}