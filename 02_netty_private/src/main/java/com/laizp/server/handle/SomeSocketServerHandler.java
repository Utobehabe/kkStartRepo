package com.laizp.server.handle;

import io.netty.channel.ChannelHandler;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;

import java.util.UUID;
import java.util.concurrent.TimeUnit;

/**
 * 服务器接收客户端信息处理器
 */
public class SomeSocketServerHandler extends ChannelInboundHandlerAdapter {

    /**
     * 当接收到客户端的数据的时候相应
     * @param ctx 上下文
     * @param msg 接收的数据
     * @throws Exception
     */
    @Override
    public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {
//        super.channelRead(ctx, msg);
        // 在服务器端的控制台中打印数据
        System.out.println(ctx.channel().remoteAddress()+", "+msg);
        // 向客户端发送一个随机数
        ctx.channel().writeAndFlush("from server: "+ UUID.randomUUID());
        // 间隔500毫秒发送一次
        TimeUnit.MILLISECONDS.sleep(500);
    }

    @Override
    public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) throws Exception {
        // super.exceptionCaught(ctx, cause);
        ctx.channel().close();
        cause.printStackTrace();
    }
}
