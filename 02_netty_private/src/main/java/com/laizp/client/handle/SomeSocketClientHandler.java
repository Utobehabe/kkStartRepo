package com.laizp.client.handle;

import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;

import java.time.LocalDateTime;
import java.util.concurrent.TimeUnit;

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
public class SomeSocketClientHandler extends SimpleChannelInboundHandler {

    /**
     * 接收服务器响应数据
     * @param ctx
     * @param msg
     * @throws Exception
     */
    protected void channelRead0(ChannelHandlerContext ctx, Object msg) throws Exception {
        System.out.println(ctx.channel().remoteAddress() +", "+msg);
        ctx.channel().writeAndFlush("from client: "+ LocalDateTime.now());
        TimeUnit.MILLISECONDS.sleep(500);
    }

    @Override
    public void channelActive(ChannelHandlerContext ctx) throws Exception {
//        super.channelActive(ctx);
        ctx.channel().writeAndFlush("from client: push first date");
    }

    @Override
    public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) throws Exception {
//        super.exceptionCaught(ctx, cause);
        ctx.close();
        cause.printStackTrace();
    }
}
