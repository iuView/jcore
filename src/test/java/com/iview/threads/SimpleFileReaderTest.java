package com.iview.threads;

import com.iview.threads.IFileReader;
import com.iview.threads.SimpleFileReader;
import junit.framework.TestCase;
import org.junit.Test;

import java.util.concurrent.CountDownLatch;

public class SimpleFileReaderTest extends TestCase {

    @Test
    public void testReadFile() throws Exception {
        CountDownLatch startLatch = new CountDownLatch(1);
        CountDownLatch finishLatch = new CountDownLatch(1);
        IFileReader iFileReader = new SimpleFileReader(startLatch, finishLatch);
        iFileReader.setFile("myProperty.properties");
        iFileReader.simpleRead();
    }
}