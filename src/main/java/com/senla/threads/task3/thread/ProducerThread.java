package com.senla.threads.task3.thread;

import java.util.concurrent.BlockingQueue;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;

public class ProducerThread implements Runnable {
    private final String threadName;
    private final BlockingQueue<Integer> buffer;
    private final int bufferSize;
    private final Lock lock;
    private final Condition notFull;
    private final Condition notEmpty;

    public ProducerThread(String threadName, BlockingQueue<Integer> buffer, int bufferSize, Lock lock, Condition notFull, Condition notEmpty) {
        this.threadName = threadName;
        this.buffer = buffer;
        this.bufferSize = bufferSize;
        this.lock = lock;
        this.notFull = notFull;
        this.notEmpty = notEmpty;
    }

    @Override
    public void run() {
        while (true) {
            try {
                int item = (int) (Math.random() * 100);
                produce(item);
                System.out.println(threadName + "->" + item);
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            }
        }
    }

    private void produce(int item) throws InterruptedException {
        lock.lock();
        try {
            while (buffer.size() >= bufferSize) {
                notFull.await();
            }
            buffer.offer(item);
            notEmpty.signal();
        } finally {
            lock.unlock();
        }
    }
}
