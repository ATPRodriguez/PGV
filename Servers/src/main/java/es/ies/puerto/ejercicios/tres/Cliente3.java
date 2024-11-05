package es.ies.puerto.ejercicios.tres;

import es.ies.puerto.ejercicios.abstracts.ClienteAbstract;
import java.io.IOException;
import java.net.Socket;

public class Cliente3 extends ClienteAbstract {
    public static void main(String[] args) throws IOException {
        Socket socket = new Socket("localhost", 1234);
        startClient(socket);
    }

}