package es.ies.puerto.ejercicios.cinco;

import java.io.*;
import java.net.Socket;

public class IntercambiarFicheros extends Thread{
    private Socket socket;
    public static final String PATH ="/src/main/resources";
    public IntercambiarFicheros(Socket socket) {
        this.socket = socket;
    }

    @Override
    public void run() {
        try (BufferedReader inputReader = new BufferedReader(new InputStreamReader(socket.getInputStream()))) {
            OutputStream outputStream = socket.getOutputStream();

            String filename = inputReader.readLine();
            System.out.println("Recibiendo fichero: " + filename);
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                socket.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    public void manejarFicheros(OutputStream outputStream, String filename) {
        File file = new File(PATH+filename+".txt");
        if (!checkFile(file)) {
            return;
        }

        try (FileInputStream fileInputStream = new FileInputStream(file)) {
            byte[] buffer = new byte[4096];
            int bytesRead;

            while ((bytesRead = fileInputStream.read(buffer)) != -1) {
                outputStream.write(buffer, 0, bytesRead);
            }
            outputStream.flush();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public boolean checkFile(File file) {
        return file.exists() && file.isFile();
    }
}
