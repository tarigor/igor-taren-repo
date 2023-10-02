package com.senla.threads.task4;

import com.senla.threads.task4.service.ExecutorService;

public class TimeGenerator extends Thread {
    public static void main(String[] args) {
        long interval = 2;
        new ExecutorService(interval).start();
    }
}
