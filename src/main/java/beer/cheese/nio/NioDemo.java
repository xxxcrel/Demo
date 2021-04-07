package beer.cheese.nio;

import java.io.IOException;
import java.net.SocketOption;
import java.nio.channels.Selector;
import java.nio.channels.ServerSocketChannel;

public class NioDemo {
    public static void main(String[] args) throws IOException {
        ServerSocketChannel serverChannel = ServerSocketChannel.open();
        Selector selector = Selector.open();
        serverChannel.configureBlocking(false);
        serverChannel.register(selector, 0, new Object());

    }
}
