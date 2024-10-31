package es.ies.puerto.ejercicios.cinco;

import es.ies.puerto.ejercicios.uno.Servidor1;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;

public class Servidor5 extends Servidor1 {
    public static void main(String[] args) throws IOException {
        int port = 1234;
        ServerSocket serverSocket = new ServerSocket(port);
        System.out.println("Servidor escuchando en el puerto " + port);
        System.out.println("Escriba 'obtener fichero' para recibir un fichero");

        while (true) {
            Socket clientSocket = serverSocket.accept();
            System.out.println("Cliente conectado: " + clientSocket.getInetAddress());

            PrintWriter out = new PrintWriter(clientSocket.getOutputStream(), true);
            BufferedReader in = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));

            String message;
            while ((message = in.readLine()) != null) {
                if (message.equalsIgnoreCase("salir")) {
                    out.println("Saliendo...");
                } else if (message.equalsIgnoreCase("obtener fichero"))
                if (!correctFormat(message)) {
                    out.println("Se deben introducir dos valores numericos y ningun caracter alfabetico");
                } else {
                    String[] numeros = message.split(" ");
                    int numero1 = Integer.parseInt(numeros[0]);
                    int numero2 = Integer.parseInt(numeros[1]);

                    out.println("Eco: " + (numero1 + numero2));
                }
            }

            clientSocket.close();
        }
    }

    public static boolean correctFormat(String string) {
        if (string == null) {
            return false;
        }
        String[] numeros = string.split(" ");
        if (!(numeros.length == 2)) {
            return false;
        }
        try {
            Float.parseFloat(numeros[0]);
            Float.parseFloat(numeros[1]);
        } catch (NumberFormatException e) {
            return false;
        }

        return true;
    }
}