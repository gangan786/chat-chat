package cn.gan.chatchat.chatclient.controller;

import cn.gan.chatchat.chatclient.core.WebSocketConnectServer;
import cn.gan.chatchat.chatclient.server.bo.AddressBo;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;
import java.util.concurrent.ConcurrentHashMap;

/**
 * 深圳依时货拉拉科技有限公司 版权所有 © Copyright 2020 <br>
 *
 * @Description: TODO
 * @CreateDate: 2020/7/31 8:37 下午 <br>
 * @Author: Gangan.chen
 */

@RestController
@RequestMapping("/test")
public class MessageController {

    private static WebSocketConnectServer connect;
    private static ConcurrentHashMap<String, WebSocketConnectServer> connectMap = new ConcurrentHashMap<>();

    @RequestMapping("/connect")
    public Object connect(@RequestParam(name = "host") String host, @RequestParam(name = "port") String port) {

        return host + " : " + port;

    }

    @RequestMapping("/sendMessageToUserCenter")
    public void sendMessageToUserCenter(@RequestParam(name = "message") String message) {

        if (connect != null && connect.isConnect()) {
            try {
                connect.sendMessage(message);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

    }

    @RequestMapping("/register")
    public Object register(@RequestParam(name = "text") String text) {
        connect = new WebSocketConnectServer("ws://ganganmaster.top:8088/ws");
        try {
            if (!connect.isConnect()) {
                connect.open();
                connect.sendMessage(text);
            }
        } catch (Exception exception) {
            exception.printStackTrace();
        }
        return null;

    }

    @RequestMapping("/connectToPeer")
    public Object connectToPeer(@RequestParam(name = "host") String host,
                                @RequestParam(name = "port") String port) {
        String name = host + ":" + port;
        String rsp = "connect fail";
        WebSocketConnectServer connect = connectMap.get(name);
        if (connect == null) {
            connect = new WebSocketConnectServer("ws://" + host + ":" + port + "/ws");
            try {
                connect.open();
                connectMap.put(name, connect);
                rsp = "connect success";
            } catch (Exception exception) {
                exception.printStackTrace();
            }
        }
        return rsp;
    }

    @RequestMapping("/sendMessageToPeer")
    public Object sendMessageToPeer(@RequestParam(name = "host") String host,
                                    @RequestParam(name = "port") String port,
                                    @RequestParam(name = "message") String message) {
        String name = host + ":" + port;
        String rsp = "send message fail";
        WebSocketConnectServer connect = connectMap.get(name);
        if (connect != null) {
            try {
                connect.sendMessage(message);
                rsp = "send message success";
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return rsp;

    }

}
