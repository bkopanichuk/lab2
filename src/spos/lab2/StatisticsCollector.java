package spos.lab2;

public class StatisticsCollector {

    private int threadCount;
    private int maxNumber;
    private long elapsedTime;
    private int currentTry;

    public StatisticsCollector() {
    }

    public StatisticsCollector(int threadCount, int maxNumber, long elapsedTime, int currentTry) {
        this.threadCount = threadCount;
        this.maxNumber = maxNumber;
        this.elapsedTime = elapsedTime;
        this.currentTry = currentTry;
    }

    public int getThreadCount() {
        return threadCount;
    }

    public void setThreadCount(int threadCount) {
        this.threadCount = threadCount;
    }

    public int getMaxNumber() {
        return maxNumber;
    }

    public void setMaxNumber(int maxNumber) {
        this.maxNumber = maxNumber;
    }

    public long getElapsedTime() {
        return elapsedTime;
    }

    public void setElapsedTime(long elapsedTime) {
        this.elapsedTime = elapsedTime;
    }

    public int getCurrentTry() {
        return currentTry;
    }

    @Override
    public String toString() {
        return "currentTry = " + (currentTry + 1) + ", threadCount = " + threadCount + ", maxNumber = " + maxNumber + ", elapsedTime = " + elapsedTime + " (ns)";
    }
}
