package spos.lab2;

import java.util.Scanner;

public final class Main {
    public static void main(String[] args) {
        int maxNumber = 0;
        int threadCounts[] = null;
        int retryCount = 0;

        Scanner in = new Scanner(System.in);

        System.out.println("1 - default parameters, 2 - custom: ");
        int tmp = in.nextInt();

        if (tmp == 2) {
            System.out.println("Enter number of threads: ");
            int t = in.nextInt();
            threadCounts = new int[t];
            for (int i = 0; i < t; i++) {
                threadCounts[i] = i + 1;
            }
            if (threadCounts == null) {
                threadCounts = AlgorithmRunner.DEFAULT_THREAD_COUNTS;
            }

            System.out.println("Enter maxNumber for the loop: ");
            maxNumber = in.nextInt();
            if (maxNumber <= 0) {
                maxNumber = AlgorithmRunner.DEFAULT_MAX_NUMBER;
            }

            System.out.println("Enter number of retry count to test each thread: ");
            retryCount = in.nextInt();
            if (retryCount <= 0) {
                retryCount = AlgorithmRunner.DEFAULT_RETRY_COUNT;
            }
        } else if (tmp == 1) {
            threadCounts = AlgorithmRunner.DEFAULT_THREAD_COUNTS;
            maxNumber = AlgorithmRunner.DEFAULT_MAX_NUMBER;
            retryCount = AlgorithmRunner.DEFAULT_RETRY_COUNT;
        } else {
            System.exit(0);
        }

        AlgorithmRunner algorithmRunner = new AlgorithmRunner(threadCounts, maxNumber, retryCount);

        algorithmRunner.runStatistics();
    }

}
