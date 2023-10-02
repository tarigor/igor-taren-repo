package com.senla.threads.task4.service;

import java.text.SimpleDateFormat;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

public class ExecutorService {
    private final long interval;

    public ExecutorService(long intervalInSeconds) {
        this.interval = intervalInSeconds;
    }

    public void start() {
        ScheduledExecutorService executor = Executors.newScheduledThreadPool(1);
        executor.scheduleAtFixedRate(() -> {
            System.out.println("Current time is->" + new SimpleDateFormat("HH:mm:ss").format(System.currentTimeMillis()));
        }, 0, interval, TimeUnit.SECONDS);
    }
}
