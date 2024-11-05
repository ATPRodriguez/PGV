package es.ies.puerto.ejercicios.abstracts;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;

public abstract class ServidorAbstract {
    public static void startServer(ServerSocket serverSocket){
        while (true) {
            Socket clientSocket = null;
            try {
                clientSocket = serverSocket.accept();
                System.out.println("Cliente conectado: " + clientSocket.getInetAddress());

                PrintWriter out = new PrintWriter(clientSocket.getOutputStream(), true);
                BufferedReader in = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));

                String message;

                while ((message = in.readLine()) != null) {
                    if (message.equalsIgnoreCase("Parar")) {
                        System.out.println("Cerrando Servidor...");
                        return;
                    }
                    System.out.println("Recibido: " + message);
                    out.println("Respuesta: " + message);
                }

            } catch (IOException e) {
                System.out.println("Saliendo...");
                e.printStackTrace();

            } finally {
                try {
                    clientSocket.close();

                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }
}
