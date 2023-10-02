package com.senla.threads.task2;

import java.util.concurrent.Semaphore;

public class SingleThread implements Runnable {
    private final String threadName;
    private final Semaphore currentSemaphore;
    private final Semaphore nextSemaphore;

    public SingleThread(String threadName, Semaphore currentSemaphore, Semaphore nextSemaphore) {
        this.threadName = threadName;
        this.currentSemaphore = currentSemaphore;
        this.nextSemaphore = nextSemaphore;
    }

    @Override
    public void run() {
        while (true) {
            try {
                currentSemaphore.acquire();
                System.out.println(threadName);
                Thread.sleep(1000);
                nextSemaphore.release();
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            }
        }
    }
}
