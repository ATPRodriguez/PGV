package es.ies.puerto;

import java.io.IOException;

/**
 * Escribe un programa que ejecute un comando que tenga una alta probabilidad de fallar (por ejemplo, un comando con un nombre incorrecto).
 * Captura el error generado y muéstralo en la consola.
 */
public class Ejercicio5 {

    public static final String COMMAND = "pang -c www.google.com";

    public static void main(String[] args) {
        ProcessBuilder pb = new ProcessBuilder(COMMAND.split(" "));
        try {
            Process process = pb.start();
        } catch(IOException e) {
            e.printStackTrace();
        }
    }
}
