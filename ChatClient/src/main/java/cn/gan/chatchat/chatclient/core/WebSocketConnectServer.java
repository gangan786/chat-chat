package cn.gan.chatchat.chatclient.core;

import cn.gan.chatchat.chatclient.core.handle.ConnectHandler;
import io.netty.bootstrap.Bootstrap;
import io.netty.channel.*;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioSocketChannel;
import io.netty.handler.codec.http.EmptyHttpHeaders;
import io.netty.handler.codec.http.HttpClientCodec;
import io.netty.handler.codec.http.HttpObjectAggregator;
import io.netty.handler.codec.http.websocketx.CloseWebSocketFrame;
import io.netty.handler.codec.http.websocketx.TextWebSocketFrame;
import io.netty.handler.codec.http.websocketx.WebSocketClientHandshakerFactory;
import io.netty.handler.codec.http.websocketx.WebSocketVersion;

import java.io.IOException;
import java.net.URI;

/**
 * 深圳依时货拉拉科技有限公司 版权所有 © Copyright 2020 <br>
 *
 * @Description: TODO
 * @CreateDate: 2020/7/31 8:51 下午 <br>
 * @Author: Gangan.chen
 */

public class WebSocketConnectServer {

    private final URI uri;
    private Channel ch;
    private static final EventLoopGroup group = new NioEventLoopGroup();
    private WebSocketConnectServer webSocketConnectServer;
    private boolean isConnect = false;



    public WebSocketConnectServer(final String uri) {
        this.uri = URI.create(uri);
    }

    public void open() throws Exception {
        Bootstrap b = new Bootstrap();
        String protocol = uri.getScheme();

        String localPort = System.getProperty("localPort");

        if (!"ws".equals(protocol)) {
            throw new IllegalArgumentException("Unsupported protocol: " + protocol);
        }

        final ConnectHandler handler =
                new ConnectHandler(
                        WebSocketClientHandshakerFactory.newHandshaker(
                                uri, WebSocketVersion.V13, null, false, EmptyHttpHeaders.INSTANCE, 1280000)
                );

        b.group(group)
                .channel(NioSocketChannel.class)
                .option(ChannelOption.SO_REUSEADDR,true)
                .handler(new ChannelInitializer<SocketChannel>() {
                    @Override
                    public void initChannel(SocketChannel ch) throws Exception {
                        ChannelPipeline pipeline = ch.pipeline();
                        pipeline.addLast("http-codec", new HttpClientCodec());
                        pipeline.addLast("aggregator", new HttpObjectAggregator(65536));
                        pipeline.addLast("ws-handler", handler);
                    }
                })
                .bind(Integer.parseInt(localPort));

        ch = b.connect(uri.getHost(), uri.getPort()).sync().channel();

        handler.handshakeFuture().sync();
        isConnect = true;
    }

    public boolean isConnect() {
        return isConnect;
    }

    public void close() throws InterruptedException {
        ch.writeAndFlush(new CloseWebSocketFrame());
        ch.closeFuture().sync();
    }

    public void sendMessage(final String text) throws IOException {
        ch.writeAndFlush(new TextWebSocketFrame(text));
    }

}
