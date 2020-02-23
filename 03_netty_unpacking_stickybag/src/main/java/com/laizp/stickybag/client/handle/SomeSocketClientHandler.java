package com.laizp.stickybag.client.handle;

import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;

import java.time.LocalDateTime;
import java.util.concurrent.TimeUnit;

/**
 * 客户端接收服务器端数据处理
 */
public class SomeSocketClientHandler extends SimpleChannelInboundHandler {

    private String message = getLongMessage();

    /**
     * 接收服务器响应数据
     * @param ctx
     * @param msg
     * @throws Exception
     */
    protected void channelRead0(ChannelHandlerContext ctx, Object msg) throws Exception {
        ctx.channel().writeAndFlush("from client: "+ LocalDateTime.now());
    }

    /**
     * 客户端
     * 管道一响应就触发该方法执行
     * @param ctx
     * @throws Exception
     */
    @Override
    public void channelActive(ChannelHandlerContext ctx) throws Exception {
//        super.channelActive(ctx);
        ctx.channel().writeAndFlush(message);
    }

    @Override
    public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) throws Exception {
//        super.exceptionCaught(ctx, cause);
        ctx.close();
        cause.printStackTrace();
    }

    private String getShortMessage() {
        return "Hello World";
    }

    private String getLongMessage() {
        return "Netty is a NIO client server framework " +
                "which enables quick and easy development of network applications " +
                "such as protocol servers and clients. It greatly simplifies and " +
                "streamlines network programming such as TCP and UDP socket server." +
                "'Quick and easy' doesn't mean that a resulting application will " +
                "suffer from a maintainability or a performance issue. Netty has " +
                "this guide and play with Netty.In other words, Netty is an NIO " +
                "framework that enables quick and easy development of network " +
                "as protocol servers and clients. It greatly simplifies and network " +
                "programming such as TCP and UDP socket server development.'Quick " +
                "not mean that a resulting application will suffer from a maintain" +
                "performance issue. Netty has been designed carefully with the expe " +
                "from the implementation of a lot of protocols such as FTP, SMTP, " +
                " binary and text-based legacy protocols. As a result, Netty has " +
                "a way to achieve of development, performance, stability, without " +
                "which enables quick and easy development of network applications " +
                "such as protocol servers and clients. It greatly simplifies and " +
                "streamlines network programming such as TCP and UDP socket server." +
                "'Quick and easy' doesn't mean that a resulting application will " +
                "suffer from a maintainability or a performance issue. Netty has " +
                "this guide and play with Netty.In other words, Netty is an NIO " +
                "framework that enables quick and easy development of network " +
                "as protocol servers and clients. It greatly simplifies and network " +
                "programming such as TCP and UDP socket server development.'Quick " +
                "not mean that a resulting application will suffer from a maintain" +
                "performance issue. Netty has been designed carefully with the expe " +
                "from the implementation of a lot of protocols such as FTP, SMTP, " +
                " binary and text-based legacy protocols. As a result, Netty has " +
                "a way to achieve of development, performance, stability, without " +
                "which enables quick and easy development of network applications " +
                "such as protocol servers and clients. It greatly simplifies and " +
                "streamlines network programming such as TCP and UDP socket server." +
                "'Quick and easy' doesn't mean that a resulting application will " +
                "suffer from a maintainability or a performance issue. Netty has " +
                "this guide and play with Netty.In other words, Netty is an NIO " +
                "framework that enables quick and easy development of network " +
                "as protocol servers and clients. It greatly simplifies and network " +
                "programming such as TCP and UDP socket server development.'Quick " +
                "not mean that a resulting application will suffer from a maintain" +
                "performance issue. Netty has been designed carefully with the expe " +
                "from the implementation of a lot of protocols such as FTP, SMTP, " +
                " binary and text-based legacy protocols. As a result, Netty has " +
                "a way to achieve of development, performance, stability, without " +
                "which enables quick and easy development of network applications " +
                "such as protocol servers and clients. It greatly simplifies and " +
                "framework that enables quick and easy development of network " +
                "as protocol servers and clients. It greatly simplifies and network " +
                "programming such as TCP and UDP socket server development.'Quick " +
                "not mean that a resulting application will suffer from a maintain" +
                "performance issue. Netty has been designed carefully with the expe " +
                "from the implementation of a lot of protocols such as FTP, SMTP, " +
                " binary and text-based legacy protocols. As a result, Netty has " +
                "a way to achieve of development, performance, stability, without " +
                "which enables quick and easy development of network applications " +
                "such as protocol servers and clients. It greatly simplifies and " +
                "framework that enables quick and easy development of network " +
                "as protocol servers and clients. It greatly simplifies and network " +
                "programming such as TCP and UDP socket server development.'Quick " +
                "not mean that a resulting application will suffer from a maintain" +
                "performance issue. Netty has been designed carefully with the expe " +
                "from the implementation of a lot of protocols such as FTP, SMTP, " +
                " binary and text-based legacy protocols. As a result, Netty has " +
                "a way to achieve of development, performance, stability, without " +
                "which enables quick and easy development of network applications " +
                "such as protocol servers and clients. It greatly simplifies and " +
                "streamlines network programming such as TCP and UDP socket server." +
                "'Quick and easy' doesn't mean that a resulting application will " +
                "suffer from a maintainability or a performance issue. Netty has " +
                "this guide and play with Netty.In other words, Netty is an NIO " +
                "framework that enables quick and easy development of network " +
                "as protocol servers and clients. It greatly simplifies and network " +
                "programming such as TCP and UDP socket server development.'Quick " +
                "not mean that a resulting application will suffer from a maintain" +
                "performance issue. Netty has been designed carefully with the expe " +
                "from the implementation of a lot of protocols such as FTP, SMTP, " +
                " binary and text-based legacy protocols. As a result, Netty has " +
                "a way to achieve of development, performance, stability, without " +
                "a compromise.=====================================================";
    }
}
