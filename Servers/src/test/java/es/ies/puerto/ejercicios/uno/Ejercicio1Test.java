package es.ies.puerto.ejercicios.uno;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;

import static org.mockito.Mockito.*;

public class Ejercicio1Test {

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

        when(mockServer.accept()).thenReturn(mockServerSocket);


        clientInputStream = new ByteArrayInputStream("test\nparar\n".getBytes());
        clientOutputStream = new ByteArrayOutputStream();

        serverInputStream = new ByteArrayInputStream("Received: test\n".getBytes());
        serverOutputStream = new ByteArrayOutputStream();

        when(mockClientSocket.getOutputStream()).thenReturn(clientOutputStream);
        when(mockClientSocket.getInputStream()).thenReturn(serverInputStream);

        when(mockServerSocket.getOutputStream()).thenReturn(serverOutputStream);
        when(mockServerSocket.getInputStream()).thenReturn(clientInputStream);
    }

    @Test
    public void clientTest() throws InterruptedException {
        Thread serverThread = new Thread(() -> {
                Servidor1.startServer(mockServer);
        });
        serverThread.start();

        Thread clientThread = new Thread(() -> {
            Cliente1.startClient(mockClientSocket);
        });

        clientThread.start();

        clientThread.join(1000);
        serverThread.join(1000);
    }
}