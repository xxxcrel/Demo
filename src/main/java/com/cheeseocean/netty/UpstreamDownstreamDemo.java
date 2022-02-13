package com.cheeseocean.netty;

import org.jetbrains.annotations.NotNull;

import io.netty.bootstrap.ServerBootstrap;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelOutboundHandlerAdapter;
import io.netty.channel.ChannelPromise;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.nio.NioServerSocketChannel;
import io.netty.channel.socket.nio.NioSocketChannel;

/**
 * Netty的ChannelHandler执行顺序
 * In1 -> Out1 -> In2 -> In3 -> Out2 -> Out3 -> In4
 * 当有读事件发生时， pipeline中的InboundHandler按添加顺序执行
 * 当我们在In4中调用write方法时，OutboundHandler的执行顺序将从该In4向前搜索
 * 因此OutboundHandler的执行顺序为Out3 -> Out2 -> Out1,
 * **如果你在In3中就调用了write， 那么Out2和Out3将不会执行
 */
public class UpstreamDownstreamDemo {
    public static void main(String[] args) throws InterruptedException{
        NioEventLoopGroup boss = new NioEventLoopGroup();
        NioEventLoopGroup worker = new NioEventLoopGroup();
        ServerBootstrap bootstrap = new ServerBootstrap();
        try {
            bootstrap.channel(NioServerSocketChannel.class)
                    .group(boss, worker)
                    .childHandler(new ChannelInitializer<NioSocketChannel>() {
                        @Override
                        protected void initChannel(@NotNull NioSocketChannel ch) throws Exception {
                            ch.pipeline().addLast(new ChannelInboundHandlerAdapter() {
                                @Override
                                public void channelRead(@NotNull ChannelHandlerContext ctx, @NotNull Object msg) throws Exception {
                                    System.out.println("(byte->obj)");
                                    super.channelRead(ctx, msg);
                                }
                            }).addLast(new ChannelInboundHandlerAdapter() {
                                @Override
                                public void channelRead(@NotNull ChannelHandlerContext ctx, @NotNull Object msg) throws Exception {
                                    System.out.println("(obj->mqtt)");
                                    ctx.writeAndFlush(msg);
                                }
                            }).addLast(new ChannelOutboundHandlerAdapter() {
                                @Override
                                public void write(ChannelHandlerContext ctx, Object msg, ChannelPromise promise) throws Exception {
                                    System.out.println("(obj->byte)");
                                    super.write(ctx, msg, promise);
                                }
                            }).addLast(new ChannelOutboundHandlerAdapter() {
                                @Override
                                public void write(ChannelHandlerContext ctx, Object msg, ChannelPromise promise) throws Exception {
                                    System.out.println("(mqtt->obj)");
                                    super.write(ctx, msg, promise);
                                }
                            });
                        }
                    });
            ChannelFuture future = bootstrap.bind(23333).sync();
            future.channel().closeFuture().sync();
        } finally {
            boss.shutdownGracefully();
            worker.shutdownGracefully();
        }
    }
}
