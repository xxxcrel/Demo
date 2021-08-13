package cn.qisee.nio;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;
import java.nio.channels.ServerSocketChannel;
import java.nio.channels.SocketChannel;
import java.util.Iterator;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class NioDemo {

    static ExecutorService executor = Executors.newFixedThreadPool(20);

    public static void main(String[] args) throws IOException {
        ServerSocketChannel serverChannel = ServerSocketChannel.open();
        Selector selector = Selector.open();
        serverChannel.bind(new InetSocketAddress(23333));
        serverChannel.configureBlocking(false);
        serverChannel.register(selector, SelectionKey.OP_ACCEPT, serverChannel);
        executor.execute(() -> {
            while (true) {
                try {
                    int cnt = selector.select();
                    System.out.println("cnt: " + cnt);
                    Iterator<SelectionKey> keys = cnt > 0 ? selector.selectedKeys().iterator() : null;
                    while (keys != null && keys.hasNext()) {
                        System.out.println("keys not null");
                        keys.next();
                        executor.execute(() -> {
                            SocketChannel socket = null;
                            try {
                                socket = serverChannel.accept();
                                if (socket != null) {
                                    System.out.println("accept a new connection");
                                    socket.close();
                                }
                            } catch (IOException e) {
                                e.printStackTrace();
                            }
                        });
                        keys.remove();
                    }
                } catch (IOException e) {
                    e.printStackTrace();
                }

            }
        });
    }

}
