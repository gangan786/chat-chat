package cn.gan.chatchat.chatclient.config;

import cn.gan.chatchat.chatclient.core.NettyBoot;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * 深圳依时货拉拉科技有限公司 版权所有 © Copyright 2020 <br>
 *
 * @Description: TODO
 * @CreateDate: 2020/7/31 8:39 下午 <br>
 * @Author: Gangan.chen
 */

@Configuration
public class ListenerConfig {

    @Bean
    public NettyBoot nettyBoot() {
        return new NettyBoot();
    }

}
