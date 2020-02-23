package com.laizp.framedecoder.lengthfieldbasedframedecoder.server;

import com.laizp.framedecoder.lengthfieldbasedframedecoder.server.handle.SomeSocketServerHandler;
import io.netty.bootstrap.ServerBootstrap;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelPipeline;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioServerSocketChannel;
import io.netty.handler.codec.FixedLengthFrameDecoder;
import io.netty.handler.codec.LengthFieldBasedFrameDecoder;
import io.netty.handler.codec.LengthFieldPrepender;
import io.netty.handler.codec.string.StringDecoder;
import io.netty.handler.codec.string.StringEncoder;

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
                            /**
                             * lengthfieldbasedframedecoder 五个参数
                             * maxFrameLength：要解码的 Frame 的最大长度（不清楚可以用Integer.MAX_VALUE）
                             * lengthFieldOffset：长度域的偏移量
                             * lengthFieldLength：长度域的长度
                             * lengthAdjustment：要添加到长度域值中的补偿值，长度矫正值。
                             * initialBytesToStrip：从解码帧中要剥去的前面字节
                             */
                            // 接收的数据不包含长度域的数据
                            // pipeline.addLast(new LengthFieldBasedFrameDecoder(Integer.MAX_VALUE, 0, 4, 0 , 4));
                            // 包含长度域的接收处理，第四个参数，需要偏移长度域的大小
                            pipeline.addLast(new LengthFieldBasedFrameDecoder(Integer.MAX_VALUE, 0, 4, -4 , 4));
                            pipeline.addLast(new LengthFieldPrepender(4));
                            pipeline.addLast(new StringDecoder());
                            pipeline.addLast(new StringEncoder());
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
