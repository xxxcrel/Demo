package beer.cheese.netty;

import java.net.InetSocketAddress;
import java.util.stream.IntStream;

import org.jetbrains.annotations.NotNull;

import io.netty.bootstrap.Bootstrap;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioSocketChannel;
import io.netty.handler.codec.string.StringEncoder;

public class RequestEchoServer {
    public static void main(String[] args) throws InterruptedException {
        Bootstrap boot = new Bootstrap();
        boot.group(new NioEventLoopGroup());
        boot.channel(NioSocketChannel.class);
        boot.handler(new ChannelInitializer<SocketChannel>() {
            @Override
            protected void initChannel(@NotNull SocketChannel ch) throws Exception {
                ch.pipeline().addLast(new StringEncoder());
            }
        });
        IntStream.rangeClosed(1, 10).parallel().forEach(i -> {
            ChannelFuture future = null;
            try {
                future = boot.connect(new InetSocketAddress("localhost", 5147)).sync();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            future.channel().writeAndFlush("hello! i'm " + i);
            future.channel().close();
        });
    }
}
