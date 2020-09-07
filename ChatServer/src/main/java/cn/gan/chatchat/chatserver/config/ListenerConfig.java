package cn.gan.chatchat.chatserver.config;

import cn.gan.chatchat.chatserver.NettyBoot;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * 深圳依时货拉拉科技有限公司 版权所有 © Copyright 2020 <br>
 *
 * @Description: TODO
 * @CreateDate: 2020/7/28 2:56 下午 <br>
 * @Author: Gangan.chen
 */

@Configuration
public class ListenerConfig {
    @Bean
    public NettyBoot nettyBoot() {
        return new NettyBoot();
    }
}
