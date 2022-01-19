package com.server.thread;

import com.google.gson.Gson;

import java.io.*;
import java.net.Socket;
import java.util.List;

public class ClientServiceThread extends Thread {
    private final BufferedReader bufferedReader;
    private final BufferedWriter bufferedWriter;
    private final Socket socket;
    private final Server server;

    public ClientServiceThread(Server server, Socket socket) throws IOException {
        this.server = server;
        bufferedReader = new BufferedReader(new InputStreamReader(socket.getInputStream()));
        bufferedWriter = new BufferedWriter(new OutputStreamWriter(socket.getOutputStream()));
        this.socket = socket;
    }

    @Override
    public void run() {
        while (!isInterrupted()) {
            try {
                String msg = new Gson().fromJson(bufferedReader.readLine(), String.class);
                sendMsgAll(msg);
            } catch (IOException e) {
                e.printStackTrace();
                close();
                interrupt();
            }
        }
    }

    private void close() {
        try {
            socket.close();
            bufferedWriter.close();
            bufferedReader.close();
            interrupt();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void sendMsgAll(String msg) throws IOException {
        for (int i = 0; i < server.size(); i++) {
            server.getUserThread(i).sendMsg(msg);
        }
    }

    private void sendMsg(String msg) throws IOException {
        bufferedWriter.write(new Gson().toJson(msg));
        bufferedWriter.newLine();
        bufferedWriter.flush();
    }

}
