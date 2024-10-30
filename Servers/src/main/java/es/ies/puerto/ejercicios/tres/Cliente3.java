package es.ies.puerto.ejercicios.tres;

import es.ies.puerto.ejercicios.uno.Cliente1;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;

public class Cliente3 extends Cliente1 {
    public static void main(String[] args) {
        String host = "localhost";
        int port = 1234;
        Socket socket = new Socket();

        try {
            socket = new Socket(host, port);
            PrintWriter out = new PrintWriter(socket.getOutputStream(), true);
            BufferedReader in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            BufferedReader console = new BufferedReader(new InputStreamReader(System.in));

            String userInput;
            while ((userInput = console.readLine()) != null) {
                if (userInput.equalsIgnoreCase("salir")) {
                    throw new Exception();
                }
                out.println(userInput);
                System.out.println("Respuesta del servidor: " + in.readLine());
            }
        } catch (Exception e) {
            System.out.println("Saliendo...");
        } finally {
            try {
                socket.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}