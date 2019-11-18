package spos.lab2;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicBoolean;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;

public class ImrovedBakeryLock implements Lock {
    private AtomicBoolean[] flag;
    private AtomicInteger[] ticket;
    private int n;

    public ImrovedBakeryLock(int n) {
        this.n = n;
        flag = new AtomicBoolean[n];
        ticket = new AtomicInteger[n];
        for (int i = 0; i < n; i++) {
            flag[i] = new AtomicBoolean();
            ticket[i] = new AtomicInteger();
        }
    }

    @Override
    public void lock() {
        int i = ConcurrencyUtils.getCurrentThreadId();
        flag[i].set(true);
        ticket[i].set(findMaximumElement(ticket) + 1);
        for (int k = 0; k < n; k++) {
            while ((k != i) && flag[k].get() && (ticket[k].get() < ticket[i].get())) {
                //spin wait
            }
        }
    }

    @Override
    public void unlock() {
        flag[ConcurrencyUtils.getCurrentThreadId()].set(false);
    }

    private int findMaximumElement(AtomicInteger[] elementArray) {
        int maxValue = Integer.MIN_VALUE;
        for (AtomicInteger element : elementArray) {
            if (element.get() > maxValue) {
                maxValue = element.get();
            }
        }
        return maxValue;
    }

    @Override
    public void lockInterruptibly() throws InterruptedException {

    }

    @Override
    public boolean tryLock() {
        return false;
    }

    @Override
    public boolean tryLock(long time, TimeUnit unit) throws InterruptedException {
        return false;
    }

    @Override
    public Condition newCondition() {
        return null;
    }
}
