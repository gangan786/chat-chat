package cn.gan.chatchat.chatclient.core.handle;

import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;
import io.netty.handler.codec.http.websocketx.TextWebSocketFrame;

/**
 * 深圳依时货拉拉科技有限公司 版权所有 © Copyright 2020 <br>
 *
 * @Description: TODO
 * @CreateDate: 2020/7/31 8:24 下午 <br>
 * @Author: Gangan.chen
 */

public class MessageHandle extends SimpleChannelInboundHandler<TextWebSocketFrame> {

    @Override
    protected void channelRead0(ChannelHandlerContext ctx, TextWebSocketFrame msg) throws Exception {
        String message = msg.text();
        System.out.println(message);
    }
}
