package com.laizp.netty;

import com.laizp.handle.HttpServerHandle;
import io.netty.bootstrap.ServerBootstrap;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelPipeline;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioServerSocketChannel;
import io.netty.handler.codec.http.HttpServerCodec;

public class NetServer {

    public static void main(String[] args) throws Exception {

        // 客户端EventLoop、服务端EvnetLoop
        EventLoopGroup parentGroup = new NioEventLoopGroup();
        EventLoopGroup childGroup = new NioEventLoopGroup();

        try {
            ServerBootstrap bootstrap = new ServerBootstrap();
            bootstrap.group(parentGroup, childGroup)
                    .channel(NioServerSocketChannel.class)
                    .childHandler(new ChannelInitializer<SocketChannel>() {
                        @Override
                        protected void initChannel(SocketChannel socketChannel) throws Exception {
                            ChannelPipeline pipeline = socketChannel.pipeline();
                            // 将HttpServerCodec处理器放入到pipeline的最后，
                            // HttpServerCodec是HttpRequestDecoder与HttpResponseEncoder的复合体
                            // HttpRequestDecoder：http请求解码器，将Channel中的ByteBuff数据解码为HttpRequest
                            // HttpResponseEncoder：http的响应解码器，将HttpResponse对象编码为将要在Channel中发送的数据
                            pipeline.addLast("httpServerCodec", new HttpServerCodec());
                            pipeline.addLast("httpServerManualHandle", new HttpServerHandle());
                        }
                    });
            ChannelFuture channelFuture = bootstrap.bind(9090).sync();
            System.out.println("服务器已经启动");
            channelFuture.channel().closeFuture().sync();
        } finally {
            parentGroup.shutdownGracefully();
            childGroup.shutdownGracefully();
        }
    }
}
