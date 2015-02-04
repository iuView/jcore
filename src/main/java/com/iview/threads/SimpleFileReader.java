package com.iview.threads;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;
import java.util.concurrent.CountDownLatch;

public class SimpleFileReader implements IFileReader, Runnable {

    String file;

    private final CountDownLatch startLatch;
    private final CountDownLatch finishLatch;

    @SuppressWarnings("unused")
    public String getFile() {
        return file;
    }

    @Override
    public void setFile(String file) {
        this.file = file;
    }

    public SimpleFileReader(CountDownLatch startLatch, CountDownLatch finishLatch) {
        this.startLatch = startLatch;
        this.finishLatch = finishLatch;
    }

    @Override
    public void readFile() {

        try {
            startLatch.await();
            Properties prop = new Properties();
            InputStream inputStream = getClass().getClassLoader().getResourceAsStream(file);

            if (inputStream != null) {
                prop.load(inputStream);
                System.out.println("In readFile, thread id: " + Thread.currentThread().getId());
            } else {
                System.out.println("file not found");
            }
        }
        catch (IOException ioe) {
            ioe.printStackTrace();
        }
        catch (InterruptedException ie) {
            ie.printStackTrace();
        }
        finally {
            finishLatch.countDown();
        }
    }

    @Override
    public void simpleRead() {
        try {
            Properties prop = new Properties();
            InputStream inputStream = getClass().getClassLoader().getResourceAsStream(file);

            if (inputStream != null) {
                prop.load(inputStream);
                //System.out.println("In readFile, thread id: " + Thread.currentThread().getId());
            } else {
                System.out.println("file not found");
            }
        }
        catch (IOException ioe) {
            ioe.printStackTrace();
        }
    }


    @Override
    public void run() {
        readFile();
    }
}
