package es.ies.puerto.ejercicios.tres;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.List;

public class Servidor3 {
    public static void main(String[] args) throws IOException {
        int port = 1234;
        ServerSocket serverSocket = new ServerSocket(port);
        System.out.println("Server escuchando en el puerto: " + port);

        startCalculatorServer(serverSocket);
    }

    public static void startCalculatorServer(ServerSocket serverSocket){
        while (true) {
            Socket clientSocket = null;
            try {
                clientSocket = serverSocket.accept();
                System.out.println("Cliente conectado: " + clientSocket.getInetAddress());

                PrintWriter out = new PrintWriter(clientSocket.getOutputStream(), true);
                BufferedReader in = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));

                String mensaje;


                while ((mensaje = in.readLine()) != null) {
                    String [] messageSplit = mensaje.split(" ");
                    int counter = 0;
                    List<Float> resultsList  = new ArrayList<>();

                    for (String s : messageSplit) {
                        if (esDigito(s)) {
                            resultsList.add(Float.parseFloat(s));
                            counter++;
                        } else {
                            break;
                        }
                    }

                    if (counter == messageSplit.length){
                        float result = calcular(resultsList);

                        System.out.println("Recibido: " + result );
                        out.println("Respuesta: " + result);
                    } else {
                        System.out.println("Recibido: " + mensaje);
                        out.println("Respuesta: " + mensaje);
                    }


                }

            } catch (IOException e) {
                e.printStackTrace();

            } finally {
                try {
                    if (clientSocket != null){
                        clientSocket.close();
                    }

                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    public static boolean esDigito(String mensaje) {
        if (mensaje == null) {
            return false;
        }

        try {
            Float.parseFloat(mensaje);
        } catch (NumberFormatException e) {
            return false;
        }
        return true;
    }

    public static float calcular(List<Float> resultados){
        float resultado = 0;
        for (Float index : resultados){
            resultado += index;
        }

        return resultado;
    }
}