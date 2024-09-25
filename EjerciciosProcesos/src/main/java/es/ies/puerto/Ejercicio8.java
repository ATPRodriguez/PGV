package es.ies.puerto;

import java.io.IOException;

/**
 * Escribe un programa que ejecute un proceso del sistema (por ejemplo, ping) y mida el tiempo que tarda en completarse.
 * Muestra el tiempo de ejecución en la consola.
 */
public class Ejercicio8 {

    public static final String MESSAGE = "ping -c 3 www.google.com";

    public static void main(String[] args) {
        ProcessBuilder pb = new ProcessBuilder(MESSAGE.split(" "));
        long secsBeforeProcess = 0;
        long secsAfterProcess = 0;
        try {
            secsBeforeProcess = System.currentTimeMillis();
            Process process = pb.start();
            process.waitFor();
            secsAfterProcess = System.currentTimeMillis();
        } catch (IOException | InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println("El proceso tardó " + (secsAfterProcess - secsBeforeProcess) + " milisecs");
    }
}
