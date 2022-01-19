package com.client.thread;

import com.google.gson.Gson;

import java.io.*;
import java.net.Socket;

public class Main {
    public static void main(String[] args) throws IOException {
        Socket socket = new Socket("localhost", 8000);
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(socket.getInputStream()));
        BufferedWriter bufferedWriter = new BufferedWriter(new OutputStreamWriter(socket.getOutputStream()));
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder lineCommand = new StringBuilder();
        MessangerThread messangerThread = new MessangerThread(bufferedReader);
        messangerThread.start();
        while (!lineCommand.append(reader.readLine()).toString().equals("/exit")) {
            sendMsgToServer(lineCommand, bufferedWriter);
            lineCommand = new StringBuilder();
        }
    }

    public static void sendMsgToServer(StringBuilder msg, BufferedWriter bufferedWriter) throws IOException {
        bufferedWriter.write(new Gson().toJson(msg));
        bufferedWriter.newLine();
        bufferedWriter.flush();
    }
}
