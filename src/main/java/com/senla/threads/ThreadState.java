package com.senla.threads;

import java.text.SimpleDateFormat;

public class ThreadState {
    public static void main(String[] args) {
        final Object lock = new Object();
        // Start first the thread
        Thread thread1 = new Thread(() -> {
            // NEW state
            printThreadState(Thread.currentThread(), " is NEW state");

            // Simulate BLOCKED state
            synchronized (lock) {
                try {
                    printThreadState(Thread.currentThread(), " is BLOCKED /lock/ resource for 6 second");
                    Thread.sleep(6000);
                    printThreadState(Thread.currentThread(), " unlocked the /lock/ resource");
                } catch (InterruptedException e) {
                    Thread.currentThread().interrupt();
                }
            }

            // Simulate some work in RUNNABLE state
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            }

            // Simulate WAITING state
            synchronized (ThreadState.class) {
                try {
                    Thread.sleep(8000);
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
                printThreadState(Thread.currentThread(), " is on WAITING state");
                System.out.println("");
                try {
                    ThreadState.class.wait();
                    printThreadState(Thread.currentThread(), " is out from WAITING state");
                } catch (InterruptedException e) {
                    Thread.currentThread().interrupt();
                }
            }
            printThreadState(Thread.currentThread(), " is on TERMINATED state");
        });

        // Start second the thread
        thread1.start();

        Thread thread2 = new Thread(() -> {
            printThreadState(Thread.currentThread(), " is NEW state");
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
            printThreadState(Thread.currentThread(), " is trying to acquire the /lock/ resource");
            System.out.println("");
            synchronized (lock) {
                try {
                    printThreadState(Thread.currentThread(), " is BLOCKED /lock/ resource for 2 second");
                    System.out.println("");
                    Thread.sleep(2000);

                } catch (InterruptedException e) {
                    Thread.currentThread().interrupt();
                }
            }
            printThreadState(Thread.currentThread(), " unlocked the /lock/ resource");

            printThreadState(Thread.currentThread(), " is in TIMED_WAITING state for 2 seconds");
            System.out.println("");
            try {
                Thread.sleep(2000); // Sleep for 2 seconds
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            }
            printThreadState(Thread.currentThread(), " is awake!");
            printThreadState(Thread.currentThread(), " is on TERMINATED state");
            System.out.println("");
        });

        thread2.start();
        // Sleep briefly to allow the thread to change states
        try {
            System.out.println("Start pause for 20 sec before notifyAll call");
            Thread.sleep(20000);
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }
        // Simulate a BLOCKED state by notifying the thread
        synchronized (ThreadState.class) {
            ThreadState.class.notifyAll();
            System.out.println("NotifyAll called" + " timestamp:" + new SimpleDateFormat("HH:mm:ss").format(System.currentTimeMillis()));
        }
    }

    private static void printThreadState(Thread thread, String state) {
        System.out.println("Thread " + thread.getId() + state + " timestamp:" + new SimpleDateFormat("HH:mm:ss").format(System.currentTimeMillis()));
    }
}
