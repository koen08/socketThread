package com.client.thread;

import com.google.gson.Gson;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;

public class MessangerThread extends Thread {
    private Log log = new Log(getClass().getName(), Thread.currentThread().getName());
    private BufferedReader bufferedReader;

    public MessangerThread(BufferedReader bufferedReader) {
        this.bufferedReader = bufferedReader;
    }

    @Override
    public void run() {
        while (!isInterrupted()) {
            try {
                String msg = new Gson().fromJson(bufferedReader.readLine(), String.class);
                log.info(msg);
            } catch (IOException e) {
                e.printStackTrace();
                interrupt();
            }
        }
    }
}
