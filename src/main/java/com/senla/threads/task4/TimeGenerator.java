package com.senla.threads.task4;

import java.text.SimpleDateFormat;

public class TimeGenerator extends Thread {
    private final long interval;

    public TimeGenerator(long intervalInSeconds) {
        this.interval = intervalInSeconds * 1000;
    }

    public static void main(String[] args) {
        long n = 2;
        new TimeGenerator(n).start();
    }

    @Override
    public void run() {
        while (!Thread.interrupted()) {
            System.out.println("Current time is->" + new SimpleDateFormat("HH:mm:ss").format(System.currentTimeMillis()));
            try {
                Thread.sleep(interval);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        }
    }
}
