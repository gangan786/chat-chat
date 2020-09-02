package cn.gan.chatchat.chatserver.controller;

import cn.gan.chatchat.chatserver.Constant;
import cn.gan.chatchat.chatserver.NettyBoot;
import io.netty.channel.Channel;
import io.netty.channel.socket.nio.NioServerSocketChannel;
import io.netty.handler.codec.http.websocketx.TextWebSocketFrame;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.net.InetSocketAddress;
import java.net.URI;

/**
 * 深圳依时货拉拉科技有限公司 版权所有 © Copyright 2020 <br>
 *
 * @Description: TODO
 * @CreateDate: 2020/7/26 11:08 下午 <br>
 * @Author: Gangan.chen
 */

@RestController
@RequestMapping("/test")
public class TestController {

    @Autowired
    private NettyBoot nettyBoot;

    @RequestMapping("/helloWord")
    public Object test() {

        URI uri = URI.create("ws://ganganmaster.top:8088/ws");
        Channel channel = Constant.channel;

        //构建连接
        InetSocketAddress remoteAddress = new InetSocketAddress("ws://ganganmaster.top:8088/ws", 8088);
        Channel friendChannel = null;
        try {
            friendChannel = channel.connect(remoteAddress).sync().channel();
            friendChannel.writeAndFlush(new TextWebSocketFrame("妈的智障"));
        } catch (InterruptedException e) {
            e.printStackTrace();
        }


        return "hello word fuck fuck";
    }

}
