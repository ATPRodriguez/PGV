package es.ies.puerto.ejercicios.cinco;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;

import static org.mockito.Mockito.*;

public class Ejercicio5Test {

    public static final String MESSAGE_ERROR = "No se ha obtenido el resultado esperado";
    private Socket mockClientSocket;
    private Socket mockServerSocket;
    private ServerSocket mockServer;
    private ByteArrayOutputStream clientOutputStream;
    private ByteArrayInputStream clientInputStream;
    private ByteArrayOutputStream serverOutputStream;
    private ByteArrayInputStream serverInputStream;

    @BeforeEach
    public void beforeEach() throws IOException {
        mockClientSocket = mock(Socket.class);
        mockServerSocket = mock(Socket.class);
        mockServer = mock(ServerSocket.class);

        clientOutputStream = new ByteArrayOutputStream();
        serverOutputStream = new ByteArrayOutputStream();
        clientInputStream = new ByteArrayInputStream("test.txt\n".getBytes());
        serverInputStream = new ByteArrayInputStream("Contenido:".getBytes());

        when(mockServer.accept()).thenReturn(mockServerSocket);
        when(mockClientSocket.getOutputStream()).thenReturn(clientOutputStream);
        when(mockClientSocket.getInputStream()).thenReturn(serverInputStream);
        when(mockServerSocket.getOutputStream()).thenReturn(serverOutputStream);
        when(mockServerSocket.getInputStream()).thenReturn(clientInputStream);
    }

    @Test
    public void clienteTest() throws InterruptedException, IOException {
        Thread serverThread = new Thread(() -> {
            try {
                Servidor5.initServer(mockServer);
            } catch (Exception e) {
                e.printStackTrace();
            }
        });

        serverThread.start();

        Thread clientThread = new Thread(() -> {
            try {
                Cliente5.startCliente(mockClientSocket);
            } catch (Exception e) {
                e.printStackTrace();
            }
        });

        clientThread.start();

        clientThread.join(1000);
        serverThread.join(1000);

        String sentFileName = clientOutputStream.toString().trim();

        Assertions.assertTrue(sentFileName.contains(".txt"), MESSAGE_ERROR);
    }
}