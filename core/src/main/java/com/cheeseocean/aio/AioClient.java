package com.cheeseocean.aio;

import java.io.IOException;
import java.nio.channels.AsynchronousServerSocketChannel;

public class AioClient {
    public static void main(String[] args) throws IOException {
        AsynchronousServerSocketChannel server = AsynchronousServerSocketChannel.open();
    }
}
