package com.senla.threads.task1;

public class ThreadStates {
    public static int step = 0;

    public static void main(String[] args) {

        ThreadStates lock = new ThreadStates();

        Thread thread = new java.lang.Thread(new RunnableThread("thread-1", lock));
        printThreadState(thread);
        thread.start();
        printThreadState(thread);

        synchronized (lock) {
            while (true) {
                try {
                    Thread.sleep(1000);
                    printThreadState(thread);
                    if (thread.getState() == Thread.State.BLOCKED) {
                        step = 2;
                        break;
                    }
                } catch (InterruptedException e) {
                    Thread.currentThread().interrupt();
                }
            }
            lock.notify();
        }

        try {
            Thread.sleep(100);
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }

        synchronized (lock) {
            while (true) {
                try {
                    Thread.sleep(1000);
                    printThreadState(thread);
                    if (thread.getState() == Thread.State.WAITING) {
                        step = 3;
                        break;
                    }
                } catch (InterruptedException e) {
                    Thread.currentThread().interrupt();
                }
            }
            lock.notify();
        }

        try {
            Thread.sleep(100);
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }

        synchronized (lock) {
            while (true) {
                try {
                    Thread.sleep(1000);
                    printThreadState(thread);
                    if (thread.getState() == Thread.State.TIMED_WAITING) {
                        step = 4;
                        break;
                    }
                } catch (InterruptedException e) {
                    Thread.currentThread().interrupt();
                }
            }
            lock.notify();
        }

        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }

        printThreadState(thread);
    }

    private static void printThreadState(Thread thread) {
        System.out.println("Thread " + thread.getName() + " is in state: " + thread.getState());
    }

}
