package cn.gan.chatchat.chatserver.core;

import cn.gan.chatchat.chatserver.Constant;
import io.netty.bootstrap.ServerBootstrap;
import io.netty.channel.Channel;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelOption;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.nio.NioServerSocketChannel;
import io.netty.handler.codec.http.EmptyHttpHeaders;
import io.netty.handler.codec.http.websocketx.TextWebSocketFrame;
import io.netty.handler.codec.http.websocketx.WebSocketClientHandshaker;
import io.netty.handler.codec.http.websocketx.WebSocketClientHandshakerFactory;
import io.netty.handler.codec.http.websocketx.WebSocketVersion;
import org.springframework.stereotype.Component;

import java.net.InetSocketAddress;
import java.net.URI;

/**
 * 深圳依时货拉拉科技有限公司 版权所有 © Copyright 2020 <br>
 *
 * @Description: TODO
 * @CreateDate: 2020/7/28 3:52 下午 <br>
 * @Author: Gangan.chen
 */

@Component
public class WebSocketServer {

    private static class SingleWebSocketServer {
        static final WebSocketServer INSTANCE = new WebSocketServer();
    }

    public static WebSocketServer getInstance() {
        return SingleWebSocketServer.INSTANCE;
    }

    private EventLoopGroup mainGroup;
    private EventLoopGroup subGroup;
    private ServerBootstrap server;
    private ChannelFuture future;

    public WebSocketServer() {
        mainGroup = new NioEventLoopGroup();
        subGroup = new NioEventLoopGroup();
        server = new ServerBootstrap();
        server.group(mainGroup, subGroup)
                .channel(NioServerSocketChannel.class)
                .childHandler(new WebSocketServerInitializer());
    }

    public void start() {
        try {
            this.future = server.bind(8088).sync();

            Constant.channel = this.future.channel();
            System.err.println("netty websocket server 启动完毕...");

        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

}
