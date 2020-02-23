package com.laizp.framedecoder.linebasedframedecoder.server;

import com.laizp.framedecoder.linebasedframedecoder.server.handle.SomeSocketServerHandler;
import io.netty.bootstrap.ServerBootstrap;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelPipeline;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioServerSocketChannel;
import io.netty.handler.codec.LineBasedFrameDecoder;
import io.netty.handler.codec.string.StringDecoder;

/**
 * netty服务启动类
 */
public class ServerManager {
    /**
     * 服务启动类
     * @param args
     */
    public static void main(String[] args) throws InterruptedException {
        // 1、获取连接管理组和事件处理管理组
        NioEventLoopGroup parentGroup = new NioEventLoopGroup();
        NioEventLoopGroup childGroup = new NioEventLoopGroup();

        try {
            // bootStrap服务管理类
            ServerBootstrap bootstrap = new ServerBootstrap();
            bootstrap.group(parentGroup, childGroup)
                    .channel(NioServerSocketChannel.class)
                    .childHandler(new ChannelInitializer<SocketChannel>() {
                        protected void initChannel(SocketChannel socketChannel) throws Exception {
                            ChannelPipeline pipeline = socketChannel.pipeline();
                            // 客户端推送数据需要推送分隔符才能使用LineBasedFrameDecoder解码
                            pipeline.addLast(new LineBasedFrameDecoder(5120));
                            pipeline.addLast(new StringDecoder());
                            pipeline.addLast(new SomeSocketServerHandler());
                        }
                    });

            // bootstrap绑定端口
            ChannelFuture channelFuture = bootstrap.bind(8888).sync();
            System.out.println("服务器已经启动");
            channelFuture.channel().closeFuture().sync();
        } finally {
            parentGroup.shutdownGracefully();
            childGroup.shutdownGracefully();
        }
    }
}
