package com.server.thread;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.List;

public class Server {
    private Log log = new Log(getClass().getName(), Thread.currentThread().getName());
    private final List<ClientServiceThread> connectedList = new ArrayList<>();
    protected ServerSocket serverSocket;

    public Server() throws IOException {
        serverSocket = new ServerSocket(8000);
    }

    public void start() throws IOException {
        Socket clientSocket;
        while ((clientSocket = serverSocket.accept()) != null) {
            log.info("Client found...");
            ClientServiceThread clientServiceThread = new ClientServiceThread(this, clientSocket);
            clientServiceThread.start();
            connectedList.add(clientServiceThread);
        }
    }

    public ClientServiceThread getUserThread(int index) {
        return connectedList.get(index);
    }

    public int size() {
        return connectedList.size();
    }
}
