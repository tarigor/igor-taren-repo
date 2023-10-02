package com.senla.threads.task3.thread;

import java.util.concurrent.BlockingQueue;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;

public class ConsumerThread implements Runnable {
    private final String threadName;
    private final BlockingQueue<Integer> buffer;
    private final Lock lock;
    private final Condition notFull;
    private final Condition notEmpty;

    public ConsumerThread(String threadName, BlockingQueue<Integer> buffer, Lock lock, Condition notFull, Condition notEmpty) {
        this.threadName = threadName;
        this.buffer = buffer;
        this.lock = lock;
        this.notFull = notFull;
        this.notEmpty = notEmpty;
    }

    @Override
    public void run() {
        while (true) {
            try {
                int item = consume();
                System.out.println(threadName + "->" + item);
                Thread.sleep(1700);
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            }
        }
    }

    private int consume() throws InterruptedException {
        lock.lock();
        try {
            while (buffer.isEmpty()) {
                notEmpty.await();
            }
            int item = buffer.poll();
            notFull.signal();
            return item;
        } finally {
            lock.unlock();
        }
    }
}
