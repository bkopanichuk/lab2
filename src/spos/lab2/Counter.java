package spos.lab2;

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class Counter implements Runnable {
    private long value;
    private int maxNumber;
    private int capacity;
    private Lock lock;

    private static final int DEFAULT_MAX_NUMBER = 500;

    public Counter(long value, int capacity) {
        this(value, Counter.DEFAULT_MAX_NUMBER, capacity);
    }

    public Counter(long value, int capacity, int maxNumber) {
        this.value = value;
        this.capacity = capacity;
        this.maxNumber = maxNumber;
        lock = new ImrovedBakeryLock(capacity);
    }

    public long getAndIncrement() {
        long temp;
        lock.lock();
        try {
            if (value >= maxNumber) {
                return value;
            }
            temp = value;
            value = temp + 1;
        } finally {
            lock.unlock();
        }
        return temp;
    }

    @Override
    public void run() {
        while (getAndIncrement() < maxNumber) {
            //do nothing
        }
    }

    public long getValue() {
        return value;
    }

    public int getMaxNumber() {
        return maxNumber;
    }

    public int getCapacity() {
        return capacity;
    }
}
