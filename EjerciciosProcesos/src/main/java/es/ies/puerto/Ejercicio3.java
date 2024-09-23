package es.ies.puerto;

import java.io.File;
import java.io.IOException;
import java.io.OutputStream;

/**
 * Escribe un programa que ejecute un comando del sistema (por ejemplo, ls o dir), capture su salida y la redirija a un archivo de texto.
 *
 * Objetivos:
 *
 * Redirigir la salida de un proceso a un archivo.
 * Usar ProcessBuilder.redirectOutput() o manejar flujos de salida manualmente.
 */


public class Ejercicio3 {

    public static final String COMMAND = "ls -l";

    public static void main(String[] args) {
        ProcessBuilder pb = new ProcessBuilder(COMMAND.split(" "));
        File file = new File("ex3.txt");
        pb.redirectOutput(file);
        pb.redirectErrorStream(true);
        try {
            Process process = pb.start();

            int exitCode = process.waitFor();
            System.out.println("El codigo de error es: " + exitCode);
        } catch (IOException | InterruptedException e) {
            e.printStackTrace();
        }
    }
}
