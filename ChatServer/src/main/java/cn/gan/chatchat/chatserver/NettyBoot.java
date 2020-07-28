package cn.gan.chatchat.chatserver;

import cn.gan.chatchat.chatserver.core.WebSocketServer;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.stereotype.Component;

/**
 * 深圳依时货拉拉科技有限公司 版权所有 © Copyright 2020 <br>
 *
 * @Description: TODO
 * @CreateDate: 2020/7/28 2:33 下午 <br>
 * @Author: Gangan.chen
 */

public class NettyBoot implements ApplicationListener<ContextRefreshedEvent> {
    @Override
    public void onApplicationEvent(ContextRefreshedEvent contextRefreshedEvent) {
        if (contextRefreshedEvent.getApplicationContext().getParent() == null) {
            //启动Netty
            try {
                WebSocketServer.getInstance().start();
            } catch (Exception exception) {
                exception.printStackTrace();
            }
            System.out.println("初始化完成");
        }
    }
}
