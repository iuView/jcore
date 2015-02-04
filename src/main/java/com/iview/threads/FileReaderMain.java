package com.iview.threads;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

public class FileReaderMain {

    public static int LOOP_COUNT = 100;

    public static void main(String[] args) throws Exception {
        sequentialRead();
        multiThreadRead();
    }

    public static void sequentialRead() throws InterruptedException {
        CountDownLatch startLatch = new CountDownLatch(LOOP_COUNT + 1);
        CountDownLatch finishLatch = new CountDownLatch(LOOP_COUNT);
        long startTime = System.currentTimeMillis();

        System.out.println("starting sequentialRead");
        for (int i = 0; i < LOOP_COUNT; i++) {
            SimpleFileReader worker = new SimpleFileReader(startLatch, finishLatch);
            worker.setFile("myProperty.properties");
            worker.simpleRead();
        }

        long endTime = System.currentTimeMillis();   //note finish time
        System.out.println("Time(ms) - " + (endTime - startTime));
    }

    public static void multiThreadRead() throws InterruptedException {
        CountDownLatch startLatch = new CountDownLatch(LOOP_COUNT + 1);
        CountDownLatch finishLatch = new CountDownLatch(LOOP_COUNT);
        System.out.println("starting multiThread, number of processors: " + Runtime.getRuntime().availableProcessors());
        ExecutorService executor = Executors.newFixedThreadPool(LOOP_COUNT); // if the threadpool number is 10, then it is stuck

        for (int i = 0; i < LOOP_COUNT; i++) {
            SimpleFileReader worker = new SimpleFileReader(startLatch, finishLatch);
            worker.setFile("myProperty.properties");
            executor.execute(worker);
        }
        executor.shutdown();
        startLatch.countDown();
        startLatch.await();
        long startTime = System.currentTimeMillis();
        finishLatch.await();
        long endTime = System.currentTimeMillis();   //note finish time
        System.out.println("Time(ms) - " + (endTime - startTime));
        System.out.println("Finished all threads, my thread id: " + Thread.currentThread().getId());
    }
}
