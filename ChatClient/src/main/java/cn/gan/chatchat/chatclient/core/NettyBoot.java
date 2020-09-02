package cn.gan.chatchat.chatclient.core;

import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextRefreshedEvent;


/**
 * 深圳依时货拉拉科技有限公司 版权所有 © Copyright 2020 <br>
 *
 * @Description: TODO
 * @CreateDate: 2020/7/31 8:11 下午 <br>
 * @Author: Gangan.chen
 */


public class NettyBoot implements ApplicationListener<ContextRefreshedEvent> {

    @Override
    public void onApplicationEvent(ContextRefreshedEvent contextRefreshedEvent) {
        WebSocketReceiveServer instance = WebSocketReceiveServer.getInstance();
        instance.start();
    }
}
