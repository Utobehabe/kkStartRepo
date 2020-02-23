package com.laizp.framedecoder.fixedlengthframedecoder.server.handle;

import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;

/**
 * 服务器接收客户端信息处理器
 */
public class SomeSocketServerHandler extends SimpleChannelInboundHandler {

    private int counter;

    /**
     * 当接收到客户端的数据的时候相应
     * @param ctx 上下文
     * @param msg 接收的数据
     * @throws Exception
     */
    @Override
    public void channelRead0(ChannelHandlerContext ctx, Object msg) throws Exception {
//        super.channelRead(ctx, msg);
        // 在服务器端的控制台中打印数据
        System.out.println("客户端推送第【" + ++counter + "】数据：" + msg);
    }

    @Override
    public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) throws Exception {
        // super.exceptionCaught(ctx, cause);
        ctx.channel().close();
        cause.printStackTrace();
    }
}
