package es.ies.puerto.ejercicios.cuatro;

import es.ies.puerto.ejercicios.abstracts.ClienteAbstract;
import java.net.Socket;
import java.io.IOException;

public class ChatClient extends ClienteAbstract {
    public static void main(String[] args) throws IOException {
        System.out.println("Chat client initializing...");
        Socket socket = new Socket("localhost", 12344);
        startClientThread(socket);
    }
}