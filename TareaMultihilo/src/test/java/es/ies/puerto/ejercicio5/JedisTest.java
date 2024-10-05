package es.ies.puerto.ejercicio5;

import org.junit.jupiter.api.Test;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

import static org.junit.jupiter.api.Assertions.assertTrue;

public class JedisTest {

    @Test
    public void jedisTest() throws InterruptedException {
        ByteArrayOutputStream outContent = new ByteArrayOutputStream();
        System.setOut(new PrintStream(outContent));

        Thread obiwan = new Thread(new Jedis("Obi Wan Kenobi"));
        Thread anakin = new Thread(new Jedis("Anakin"));

        obiwan.start();
        anakin.start();

        obiwan.join();
        anakin.join();

        String output = outContent.toString();
        assertTrue(output.contains(" ha encontrado todas las pistas!"));
    }

}