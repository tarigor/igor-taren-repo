package com.senla.threads.task2;

public class ThreadAlternator implements Runnable {
    private final String threadName;
    private final Object lock;

    public ThreadAlternator(String threadName, Object lock) {
        this.threadName = threadName;
        this.lock = lock;
    }

    public static void main(String[] args) {
        Object lock = new Object();
        Thread thread1 = new Thread(new ThreadAlternator("Thread-1", lock));
        Thread thread2 = new Thread(new ThreadAlternator("Thread-2", lock));
        thread1.start();
        thread2.start();
    }

    @Override
    public void run() {
        while (true) {
            synchronized (lock) {
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
                System.out.println(threadName);
                lock.notify();
                try {
                    lock.wait();
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
            }
        }
    }
}
