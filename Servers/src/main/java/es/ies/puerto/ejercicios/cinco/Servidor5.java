package es.ies.puerto.ejercicios.cinco;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

public class Servidor5 {

    public static void main(String[] args) throws IOException {
        int port = 1234;
        ServerSocket serverSocket = new ServerSocket(port);
        initServer(serverSocket);
    }


    public static void initServer(ServerSocket serverSocket){
        try {
            System.out.println("Iniciando server");
            while (true){
                Socket clientSocket = serverSocket.accept();
                new Thread(new FileHandler(clientSocket)).start();
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}