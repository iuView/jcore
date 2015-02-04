package com.iview.threads;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.*;

public class FileReaderMain {

    public static int LOOP_COUNT = 100;
    public static int THREAD_POOL_SIZE = 3; // thread pool size can be small

    public static void main(String[] args) throws Exception {
        sequentialRead();
        multiThreadRead();
    }

    public static void sequentialRead() throws InterruptedException {
        long startTime = System.currentTimeMillis();

        System.out.println("starting sequentialRead");
        for (int i = 0; i < LOOP_COUNT; i++) {
            SimpleFileReader worker = new SimpleFileReader(null, null);
            worker.setFile("myProperty.properties");
            worker.simpleRead();
        }

        long endTime = System.currentTimeMillis();   //note finish time
        System.out.println("Time(ms) - " + (endTime - startTime));
    }

    public static void multiThreadRead() throws InterruptedException {
        CountDownLatch startLatch = new CountDownLatch(1);
        CountDownLatch finishLatch = new CountDownLatch(LOOP_COUNT);
        System.out.println("starting multiThread, number of processors: " + Runtime.getRuntime().availableProcessors());
        ExecutorService executor = Executors.newFixedThreadPool(THREAD_POOL_SIZE);

        for (int i = 0; i < LOOP_COUNT; i++) {
            SimpleFileReader worker = new SimpleFileReader(startLatch, finishLatch);
            worker.setFile("myProperty.properties");
            executor.execute(worker);
        }
        executor.shutdown(); // stop receiving workers
        startLatch.countDown(); // all workers can start now
        long startTime = System.currentTimeMillis();
        finishLatch.await(); // wait for all workers to finish
        long endTime = System.currentTimeMillis();   //note finish time
        System.out.println("Time(ms) - " + (endTime - startTime));
        System.out.println("Finished all threads, my thread id: " + Thread.currentThread().getId());
    }

    /**
     * the callable is something that returns a value.
     */
    @SuppressWarnings("unused")
    public static void MultiThreadReadFutureVersion() {
        ExecutorService es = Executors.newCachedThreadPool();
        List<Callable<Integer>> tasks = new ArrayList<Callable<Integer>>();

        for (int j = 1; j <= 10; j++) {
            tasks.add(new Callable<Integer>() {

                @Override
                public Integer call() throws Exception {
                    int sum = 0;
                    System.out.println("Starting Thread "
                            + Thread.currentThread().getId());

                    for (int i = 0; i < 1000000; i++) {
                        sum += i;
                    }

                    System.out.println("Stopping Thread "
                            + Thread.currentThread().getId());
                    return sum;
                }

            });
        }

        try {
            List<Future<Integer>> futures = es.invokeAll(tasks);
            int flag = 0;

            for (Future<Integer> f : futures) {
                Integer res = f.get();
                System.out.println("Sum: " + res);
                if (!f.isDone())
                    flag = 1;
            }

            if (flag == 0)
                System.out.println("SUCCESS");
            else
                System.out.println("FAILED");

        } 
        catch (InterruptedException e) {
            e.printStackTrace();
        }
        catch (ExecutionException e) {
            e.printStackTrace();
        }
    }
}
