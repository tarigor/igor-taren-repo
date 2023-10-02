package com.senla.threads.task3;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class ProducerConsumer {
    private static final int BUFFER_SIZE = 5;
    private static final BlockingQueue<Integer> buffer = new ArrayBlockingQueue<>(BUFFER_SIZE);
    private static final Lock lock = new ReentrantLock();
    private static final Condition notFull = lock.newCondition();
    private static final Condition notEmpty = lock.newCondition();

    public static void main(String[] args) {
        Thread producerThread = new Thread(new ProducerThread("Thread-producer", buffer, BUFFER_SIZE, lock, notFull, notEmpty));
        Thread consumerThread = new Thread(new ConsumerThread("Thread-consumer", buffer, lock, notFull, notEmpty));
        producerThread.start();
        consumerThread.start();
    }
}
