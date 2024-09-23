package es.ies.puerto;

import java.io.IOException;

/**
/ Escribe un programa que use ProcessBuilder para ejecutar el comando ping a una dirección IP o un dominio (por ejemplo, google.com). El programa debe capturar y mostrar la salida del proceso en la consola.
*/
public class Ejercicio1 {

    public static final String COMMAND = "ping -c 3 google.com";

    public static void main(String[] args) {
        ProcessBuilder pb = new ProcessBuilder(COMMAND.split(" "));
        try {
            Process proceso = pb.start();

            int exitCode = proceso.waitFor();
            System.out.println("Código de salida: " + exitCode);

        } catch (IOException | InterruptedException e) {
            e.printStackTrace();
        }
    }
}
