package es.ies.puerto;

import java.io.IOException;

/**
/ Escribe un programa que use ProcessBuilder para ejecutar el comando ping a una dirección IP o un dominio (por ejemplo, google.com). El programa debe capturar y mostrar la salida del proceso en la consola.
*/
public class Ejercicio1 {

    public static final String COMMAND = "ping -c 3 google.com";

    public String ejercicio1() {
        ProcessBuilder pb = new ProcessBuilder(COMMAND.split(" "));
        int exitCode = 404;
        try {
            Process proceso = pb.start();

            exitCode = proceso.waitFor();

        } catch (IOException | InterruptedException e) {
            e.printStackTrace();
        }
        return "Código de salida: " + exitCode;
    }
}
