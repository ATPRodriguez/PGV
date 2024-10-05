package es.ies.puerto.ejercicio7;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.util.ArrayList;

public class SuperheroesTest {

    @Test
    public void superheroesTest() throws InterruptedException {
        ByteArrayOutputStream outContent = new ByteArrayOutputStream();
        System.setOut(new PrintStream(outContent));

        ArrayList<Area> areasBatman = Superheroes.generarAreas();
        ArrayList<Area> areasSuperman = Superheroes.generarAreas();

        Thread batman = new Thread(new Superheroes("Batman", areasBatman));
        Thread superman = new Thread(new Superheroes("Superman", areasSuperman));

        batman.start();
        superman.start();

        batman.join();
        superman.join();

        String output = outContent.toString();
        Assertions.assertTrue(output.contains("Batman ha salvado la ciudad")
                || output.contains("Superman ha salvado la ciudad"));
    }
}