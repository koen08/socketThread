package com.server.thread;

import java.util.Date;

public class Log {
    private final String nameClass;
    private final String nameThread;
    private final Date date;

    public Log(String nameClass, String nameThread) {
        this.nameClass = nameClass;
        this.nameThread = nameThread;
        this.date = new Date();
    }

    public void info(String msg) {
        System.out.println(getCorrectFormat(date.toString()) + getCorrectFormat(nameClass) +
               getCorrectFormat(nameThread) + getCorrectFormat("LOG") + ": " + msg);
    }

    public String getCorrectFormat(String str) {
        return "[" + str + "] ";
    }
}
