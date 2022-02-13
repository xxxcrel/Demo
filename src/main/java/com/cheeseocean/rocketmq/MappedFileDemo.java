package com.cheeseocean.rocketmq;

import java.io.IOException;
import java.io.RandomAccessFile;
import java.nio.ByteBuffer;
import java.nio.MappedByteBuffer;
import java.nio.channels.FileChannel;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicInteger;

import com.cheeseocean.juc.ReentrantLock;

public class MappedFileDemo {
    String fileName = System.getProperty("user.home") + "/mappedFile";
    int fileSize = 65536;
    FileChannel fileChannel;
    MappedByteBuffer mappedByteBuffer;
    AtomicInteger wrotePos = new AtomicInteger(0);
    ReentrantLock lock = new ReentrantLock();
    public static void main(String[] args) throws IOException, InterruptedException {
        MappedFileDemo demo = new MappedFileDemo();
        demo.init();
        ExecutorService executor = Executors.newFixedThreadPool(2);
        executor.execute(() -> {
            demo.appendMessage("hello".getBytes());
        });
        executor.execute(() -> {
            demo.appendMessage("world".getBytes());
        });
        executor.awaitTermination(6, TimeUnit.SECONDS);
        System.out.println(demo.getMessage());
    }

    public void init() throws IOException{
        fileChannel = new RandomAccessFile(fileName, "rw").getChannel();
        mappedByteBuffer = fileChannel.map(FileChannel.MapMode.READ_WRITE, 0, fileSize);
    }

    public void appendMessage(byte[] bytes){
        lock.lock();
        int currentPos = this.wrotePos.get();
        System.out.println("wrote pos: " + currentPos + "- byte length: " + bytes.length);
        if (currentPos < this.fileSize) {
            ByteBuffer byteBuffer = mappedByteBuffer.slice();
            byteBuffer.put(bytes);
            System.out.println("after wrote" + this.wrotePos.addAndGet(bytes.length));
        }
        lock.unlock();
    }

    public String getMessage() {
        byte[] buffer = new byte[128];
        mappedByteBuffer.get(buffer);
        return new String(buffer);
    }
}
