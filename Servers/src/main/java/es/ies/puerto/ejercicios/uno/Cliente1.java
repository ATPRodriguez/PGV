package es.ies.puerto.ejercicios.uno;

import java.io.*;
import java.net.*;

import static java.lang.System.out;

public class Cliente1 {
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
            out.println(userInput);
            System.out.println("Respuesta del servidor: " + in.readLine());
        }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                socket.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}