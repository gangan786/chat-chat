package cn.gan.chatchat.chatclient.core;

import io.netty.bootstrap.ServerBootstrap;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelOption;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.nio.NioServerSocketChannel;
import org.springframework.stereotype.Component;

/**
 * 深圳依时货拉拉科技有限公司 版权所有 © Copyright 2020 <br>
 *
 * @Description: TODO
 * @CreateDate: 2020/9/1 5:34 下午 <br>
 * @Author: Gangan.chen
 */

@Component
public class WebSocketReceiveServer {

    private static class SingleWebSocketReceiveServer {
        static final WebSocketReceiveServer INSTANCE = new WebSocketReceiveServer();
    }

    public static WebSocketReceiveServer getInstance() {
        return SingleWebSocketReceiveServer.INSTANCE;
    }

    private EventLoopGroup mainGroup;
    private EventLoopGroup subGroup;
    private ServerBootstrap server;
    private ChannelFuture future;
    private String localPort;

    private WebSocketReceiveServer() {
        mainGroup = new NioEventLoopGroup();
        subGroup = new NioEventLoopGroup();
        server = new ServerBootstrap();
        server.group(mainGroup, subGroup)
                .channel(NioServerSocketChannel.class)
                .option(ChannelOption.SO_REUSEADDR,true)
                .childHandler(new WebSocketReceiveServerInitializer());
        localPort = System.getProperty("localPort");
    }

    public void start() {
        try {
            this.future = server.bind(Integer.parseInt(localPort)).sync();
            System.err.println("netty websocket server 启动完毕...");

        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

}
