package com.laizp.framedecoder.lengthfieldbasedframedecoder.client;

import com.laizp.framedecoder.lengthfieldbasedframedecoder.client.handle.SomeSocketClientHandler;
import io.netty.bootstrap.Bootstrap;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelPipeline;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioSocketChannel;
import io.netty.handler.codec.LengthFieldBasedFrameDecoder;
import io.netty.handler.codec.LengthFieldPrepender;
import io.netty.handler.codec.string.StringDecoder;
import io.netty.handler.codec.string.StringEncoder;

/**
 * 客户端启动类
 */
public class ClientManager {

    /**
     * 客户端服务启动
     * @param args
     */
    public static void main(String[] args) throws InterruptedException {

        // 获取一个EventLoopGroup
        NioEventLoopGroup group = new NioEventLoopGroup();

        try {
            // 创建客户端bootstrap
            Bootstrap bootstrap = new Bootstrap();
            bootstrap.group(group)
                    .channel(NioSocketChannel.class)
                    .handler(new ChannelInitializer<SocketChannel>() {
                        /**
                         * channel初始化的时候触发
                         * @param socketChannel
                         * @throws Exception
                         */
                        protected void initChannel(SocketChannel socketChannel) throws Exception {
                            ChannelPipeline pipeline = socketChannel.pipeline();
                            // 长度域的数据不推送给对方
                            // pipeline.addLast(new LengthFieldPrepender(4));
                            // 包含长度域的数据推送
                            pipeline.addLast(new LengthFieldPrepender(4, true));
                            pipeline.addLast(new LengthFieldBasedFrameDecoder(Integer.MAX_VALUE, 0, 4, 0, 4));
                            pipeline.addLast(new StringEncoder());
                            pipeline.addLast(new StringDecoder());
                            pipeline.addLast(new SomeSocketClientHandler());

                        }
                    });

            ChannelFuture channelFuture = bootstrap.connect("localhost", 8888).sync();
            System.out.println("客户端已经启动");
            channelFuture.channel().closeFuture().sync();
        } finally {
            group.shutdownGracefully();
        }
    }
}
