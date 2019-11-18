package spos.lab2;

import java.util.Arrays;
import java.util.concurrent.TimeUnit;

public class AlgorithmRunner {

    private int[] threadCounts;
    private int maxNumber;
    private int retryCount;

    public static final int[] DEFAULT_THREAD_COUNTS = {1, 2, 3, 4, 5, 6, 7, 8};
    public static final int DEFAULT_MAX_NUMBER = 1000;
    public static final int DEFAULT_RETRY_COUNT = 1;

    public AlgorithmRunner() {
        this(DEFAULT_THREAD_COUNTS, DEFAULT_MAX_NUMBER, DEFAULT_RETRY_COUNT);
    }

    public AlgorithmRunner(int maxNumber) {
        this(DEFAULT_THREAD_COUNTS, maxNumber, DEFAULT_RETRY_COUNT);
    }

    public AlgorithmRunner(int maxNumber, int retryCount) {
        this(DEFAULT_THREAD_COUNTS, maxNumber, retryCount);
    }

    public AlgorithmRunner(int[] threadCounts) {
        this(threadCounts, DEFAULT_MAX_NUMBER, DEFAULT_RETRY_COUNT);
    }

    public AlgorithmRunner(int[] threadCounts, int maxNumber, int retryCount) {
        //Security with defensive copying instead of storing passed array directly
        this.threadCounts = Arrays.copyOf(threadCounts, threadCounts.length);
        this.maxNumber = maxNumber;
        this.retryCount = retryCount;
    }

    public void runStatistics() {
        StatisticsCollector statisticsCollector;

        for (int threadCount : threadCounts) {
            for (int currentTry = 0; currentTry < retryCount; currentTry++) {
                Counter counter = new Counter(0, threadCount, maxNumber);
                long elapsedTime = runTest(counter, threadCount);
                statisticsCollector = new StatisticsCollector(threadCount, counter.getMaxNumber(), elapsedTime, currentTry);
                System.out.println(String.valueOf(statisticsCollector));
            }
            System.out.println("\n");
        }
    }

    public long runTest(Counter counter, int threadCount){
        Thread[] threads = new Thread[threadCount];
        long threadNumber = 0;

        long startTime = System.nanoTime();

        for (int i = 0; i < threadCount; i++) {
            threads[i] = new Thread(counter);
            //more safer way compared to module of thread id approach
            threads[i].setName(String.valueOf(threadNumber++));
        }

        for (Thread thread : threads) {
            thread.start();
        }

        for (Thread thread : threads) {
            try {
                thread.join();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

        long finishTime = System.nanoTime();

        return TimeUnit.NANOSECONDS.toNanos(finishTime - startTime);
    }
}