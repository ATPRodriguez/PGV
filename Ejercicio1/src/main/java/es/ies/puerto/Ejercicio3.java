package es.ies.puerto;

import java.io.File;
import java.io.IOException;

/**
 * Escribe un programa que ejecute un comando del sistema (por ejemplo, ls o dir), capture su salida y la redirija a un archivo de texto.
 *
 * Objetivos:
 *
 * Redirigir la salida de un proceso a un archivo.
 * Usar ProcessBuilder.redirectOutput() o manejar flujos de salida manualmente.
 */


public class Ejercicio3 {
    public static void main(String[] args) {
        ProcessBuilder pb = new ProcessBuilder("echo \"Soy un comando redirigido\"".split(" "));
        File file = new File("ex3.txt");
        try {
            Process proceso = pb.redirectOutput(file).start();

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
