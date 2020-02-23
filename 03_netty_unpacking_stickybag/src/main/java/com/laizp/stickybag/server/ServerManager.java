package com.laizp.stickybag.server;

import com.laizp.linebasedframedecoder.stickybag.server.handle.SomeSocketServerHandler;
import io.netty.bootstrap.ServerBootstrap;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelPipeline;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioServerSocketChannel;
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
                            // 因为服务器制作解码动作，所以不需要编码器
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
