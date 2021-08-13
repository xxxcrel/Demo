package cn.qisee.netty;

import java.util.concurrent.TimeUnit;

import io.netty.channel.Channel;
import io.netty.channel.embedded.EmbeddedChannel;

public class EventLoopDemo {

    public static void main(String[] args) {
        Channel channel = new EmbeddedChannel();
        channel.eventLoop().schedule(() -> {System.out.println("hel");}, 1000L, TimeUnit.MILLISECONDS);
    }
}
