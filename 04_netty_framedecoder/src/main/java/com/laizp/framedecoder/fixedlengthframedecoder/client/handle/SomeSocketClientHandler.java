package com.laizp.framedecoder.fixedlengthframedecoder.client.handle;

import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;

/**
 * 客户端接收服务器端数据处理
 *
 * SimpleChannelInboundHandler中的channelRead()方法会自动释放接收到的来自于对方的msg所占有的所有资源。
 * ChannelInboundHandlerAdapter 中的 channelRead()方法不会自动释放接收到的来自于对方的msg
 * 1、若对方没有向自己发送数据，则自定义处理器建议继承自ChannelInboundHandlerAdapter。
 * 因为若继承自 SimpleChannelInboundHandler 需要重写channelRead0()方法。
 * 而重写该方法的目的是对来自于对方的数据进行处理。因为对方根本就没有发送数据，所以也就没有必要重写 channelRead0()方法。
 * 2、若对方向自己发送了数据，而自己又需要将该数据再发送给对方，则自定义处理器建议继承自 ChannelInboundHandlerAdapter。
 * 因为 write()方法的执行是异步的，且SimpleChannelInboundHandler 中的 channelRead()方法会自动释放掉来自于对方的 msg。
 * 若 write()方法中正在处理 msg，而此时 SimpleChannelInboundHandler 中的 channelRead()方法执行完毕了，将 msg 给释放了。此时就会报错。
 */
public class SomeSocketClientHandler extends ChannelInboundHandlerAdapter {

    private String message = getShortMessage();

    /**
     * 接收服务器响应数据
     * @param ctx
     * @param msg
     * @throws Exception
     */
    @Override
    public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {
        System.out.println(ctx.channel().remoteAddress() +", "+msg);
    }

    @Override
    public void channelActive(ChannelHandlerContext ctx) throws Exception {
//        super.channelActive(ctx);
        for (int i = 0; i < 100; i++) {
            ctx.channel().writeAndFlush(message);
        }
    }

    @Override
    public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) throws Exception {
//        super.exceptionCaught(ctx, cause);
        cause.printStackTrace();
        ctx.close();
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
