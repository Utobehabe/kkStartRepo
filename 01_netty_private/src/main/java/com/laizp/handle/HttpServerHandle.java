package com.laizp.handle;

import io.netty.buffer.ByteBuf;
import io.netty.buffer.Unpooled;
import io.netty.channel.ChannelFutureListener;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;
import io.netty.handler.codec.http.*;
import io.netty.util.CharsetUtil;

public class HttpServerHandle extends ChannelInboundHandlerAdapter {
    /**
     *
     * @param ctx
     * @param msg
     * @throws Exception
     */
    @Override
    public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {

        System.out.println("msg class type:"+ msg.getClass());
        if (msg instanceof HttpRequest) {
            HttpRequest httpRequest = (HttpRequest) msg;
            String methodName = httpRequest.method().name();
            String uri = httpRequest.uri();
            System.out.println("method:"+ methodName+"\nuri:"+uri);
            // 业务处理
            // 在相应页面中显示“Hello World!”

            if ("/favicon.ico".equals(uri)) {
                // 如果请求的是ico数据，则跳过处理
                return;
            }

            // 用给相应的内容转换为ByteBuf来分配内存空间
            ByteBuf buff = Unpooled.copiedBuffer("hello world!", CharsetUtil.UTF_8);
            DefaultFullHttpResponse httpResponse = new DefaultFullHttpResponse(
                    HttpVersion.HTTP_1_1, HttpResponseStatus.OK, buff
            );
            // 获取相应头设置相应相应内容的信息。
            HttpHeaders headers = httpResponse.headers();
            headers.set(HttpHeaderNames.CONTENT_TYPE, "text/plain");
            headers.set(HttpHeaderNames.CONTENT_LENGTH, buff.readableBytes());

            // 将数据写入并且刷新缓存读取
            ctx.writeAndFlush(httpResponse).addListener(ChannelFutureListener.CLOSE);
        }
    }

    /**
     * 当netty请求发生异常的时候会触发
     * @param ctx
     * @param cause
     * @throws Exception
     */
    @Override
    public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) throws Exception {
        cause.printStackTrace();
        ctx.close();
    }
}
