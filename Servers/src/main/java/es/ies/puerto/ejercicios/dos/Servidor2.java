package es.ies.puerto.ejercicios.dos;

import es.ies.puerto.ejercicios.abstracts.ServidorAbstract;
import java.io.IOException;
import java.net.ServerSocket;

public class Servidor2 extends ServidorAbstract {
    public static void main(String[] args) throws IOException {
        int port = 1234;
        ServerSocket serverSocket = new ServerSocket(port);
        System.out.println("Server escuchando en el puerto: " + port);
        startServer(serverSocket);
    }
}