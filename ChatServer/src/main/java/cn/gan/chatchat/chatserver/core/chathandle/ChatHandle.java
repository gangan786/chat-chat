package cn.gan.chatchat.chatserver.core.chathandle;

import com.alibaba.fastjson.JSON;
import io.netty.channel.Channel;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;
import io.netty.handler.codec.http.websocketx.TextWebSocketFrame;

import java.net.InetSocketAddress;
import java.net.SocketAddress;
import java.util.HashMap;

/**
 * 深圳依时货拉拉科技有限公司 版权所有 © Copyright 2020 <br>
 *
 * @Description: TODO
 * @CreateDate: 2020/7/28 3:47 下午 <br>
 * @Author: Gangan.chen
 */

public class ChatHandle extends SimpleChannelInboundHandler<TextWebSocketFrame> {
    @Override
    protected void channelRead0(ChannelHandlerContext channelHandlerContext, TextWebSocketFrame textWebSocketFrame) throws Exception {
        //客户端发送过来的消息
        String content = textWebSocketFrame.text();
        System.out.println(content);
        Channel currentChannel = channelHandlerContext.channel();
        InetSocketAddress remoteAddress = (InetSocketAddress) currentChannel.remoteAddress();
        System.out.println("hostName："+remoteAddress.getHostName());
        System.out.println("address："+remoteAddress.getAddress());
        System.out.println("hostString："+remoteAddress.getHostString());
        System.out.println("port："+remoteAddress.getPort());
        HashMap<String, String> message = new HashMap<>();
        message.put("hostName", remoteAddress.getHostName());
        message.put("address", remoteAddress.getAddress().toString());
        message.put("hostString", remoteAddress.getHostString());
        message.put("port", String.valueOf(remoteAddress.getPort()));
        String rsp = JSON.toJSONString(message);

        channelHandlerContext.channel().writeAndFlush(rsp);
    }

}
