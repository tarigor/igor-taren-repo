package com.senla.threads.task2;

import com.senla.threads.task2.thread.RunnableThread;

import java.util.concurrent.Semaphore;

public class ThreadAlternator {
    public static void main(String[] args) {
        Semaphore semaphore1 = new Semaphore(1);
        Semaphore semaphore2 = new Semaphore(0);
        Thread singleThread1 = new Thread(new RunnableThread("Thread-1", semaphore1, semaphore2));
        Thread singleThread2 = new Thread(new RunnableThread("Thread-2", semaphore2, semaphore1));
        singleThread1.start();
        singleThread2.start();
    }
}
