package com.senla.threads.task1;

import static com.senla.threads.task1.ThreadStates.step;

public class Thread implements Runnable {
    private String threadName;
    private Object lock;

    public Thread(String threadName, Object lock) {
        this.threadName = threadName;
        this.lock = lock;
    }

    @Override
    public void run() {
        synchronized (lock) {
            if (step < 1) {
                try {
                    lock.wait();
                } catch (InterruptedException e) {
                    java.lang.Thread.currentThread().interrupt();
                }
            }
        }

        synchronized (lock) {
            if (step == 2) {
                try {
                    lock.wait();
                } catch (InterruptedException e) {
                    java.lang.Thread.currentThread().interrupt();
                }
            }
        }

        synchronized (lock) {
            if (step == 3) {
                try {
                    lock.wait(2000);
                } catch (InterruptedException e) {
                    java.lang.Thread.currentThread().interrupt();
                }
            }
        }
    }
}
