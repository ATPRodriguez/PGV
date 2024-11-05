package es.ies.puerto.ejercicios.cinco;

import java.io.*;
import java.net.Socket;
import java.util.Random;

public class Cliente5{
    public static final String RESOURCES_PATH = "src/main/resources/";

    public static void main(String[] args) throws IOException {
        String host = "localhost";
        int port = 1234;

        Socket socket = new Socket(host, port);

        startServer(socket);
    }

    public static void startServer(Socket socket){
        try {
            PrintWriter out = new PrintWriter(socket.getOutputStream(), true);
            InputStream in = socket.getInputStream();

            Random random = new Random();
            int numero = random.nextInt(10)+1;
            String filename = "fichero"+numero+".txt";

            out.println(filename);

            File fichero = new File(RESOURCES_PATH + filename);

            try (FileOutputStream fileOutputStream = new FileOutputStream(fichero)){
                byte [] buffer = new byte[4096];
                int bytesRead;

                while ((bytesRead = in.read(buffer)) != -1 ){
                    fileOutputStream.write(buffer, 0, bytesRead);
                }

                fileOutputStream.flush();
                fileOutputStream.close();

                System.out.println("Fichero recibido: " + fichero.getName());

                mostrarFichero(fichero);
            }

        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    private static void mostrarFichero(File fichero) {
        try(BufferedReader bufferedReader = new BufferedReader(new FileReader(fichero))){
            String linea;
            System.out.println("Contenido: ");
            while ((linea = bufferedReader.readLine()) != null){
                System.out.println(linea);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}