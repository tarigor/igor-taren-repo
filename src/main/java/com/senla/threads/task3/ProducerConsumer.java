package com.senla.threads.task3;

import java.util.LinkedList;

public class ProducerConsumer {
    private static final int BUFFER_SIZE = 8;
    private static LinkedList<Integer> buffer = new LinkedList<>();

    public static void main(String[] args) {
        new Thread(() -> {
            while (true) {
                synchronized (buffer) {
                    while (buffer.size() >= BUFFER_SIZE) {
                        try {
                            buffer.wait();
                        } catch (InterruptedException e) {
                            throw new RuntimeException(e);
                        }
                    }
                    int item = (int) (Math.random() * 100);
                    buffer.add(item);
                    System.out.println("Produced number->" + item);
                    buffer.notify();
                }
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
            }
        }).start();

        new Thread(() -> {
            while (true) {
                synchronized (buffer) {
                    while (buffer.isEmpty()) {
                        try {
                            buffer.wait();
                        } catch (InterruptedException e) {
                            throw new RuntimeException(e);
                        }
                    }
                    int item = buffer.poll();
                    System.out.println("Consumed number->" + item);
                    buffer.notify();
                }
                try {
                    Thread.sleep(1700);
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
            }
        }).start();
    }
}
