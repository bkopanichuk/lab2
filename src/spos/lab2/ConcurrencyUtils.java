package spos.lab2;

public final class ConcurrencyUtils {
    private ConcurrencyUtils() {
    }

    public static int getCurrentThreadId(int capacity) {
        return (int) (Thread.currentThread().getId() % capacity);
    }

    public static int getCurrentThreadId() {
        return Integer.parseInt(Thread.currentThread().getName());
    }
}

