package es.ies.puerto;

import java.io.IOException;

/**
/Escribe un programa que ejecute tres comandos diferentes de forma secuencial (por ejemplo, ping, ls o dir, y echo). Cada proceso debe esperar a que el anterior termine antes de iniciarse.
/
/Objetivos:
/
/Ejecutar procesos secuenciales.
/Manejar el méeodo waitFor() para esperar que un proceso termine antes de iniciar el siguiente.
*/
public class Ejercicio2 {

    public static final String COMMAND1 = "ping -c 3 google.com";
    public static final String COMMAND2 = "ls -l /home";
    public static final String COMMAND3 = "echo \"Soy el comando 3\"";

    public static void main(String[] args) {
        ProcessBuilder pb = new ProcessBuilder(COMMAND1.split(" "));
        ProcessBuilder pb2 = new ProcessBuilder(COMMAND2.split(" "));
        ProcessBuilder pb3 = new ProcessBuilder(COMMAND3.split(" "));
        try {
            Process proceso = pb.start();
            Process proceso2 = pb2.start();
            Process proceso3 = pb3.start();

            int exitCode = proceso.waitFor();
            int exitCode2 = proceso2.waitFor();
            int exitCode3 = proceso3.waitFor();
            System.out.println("Código de salida: " + exitCode);
            System.out.println("Código de salida: " + exitCode2);
            System.out.println("Código de salida: " + exitCode3);

        } catch (IOException | InterruptedException e) {
            e.printStackTrace();
        }
    }
}
