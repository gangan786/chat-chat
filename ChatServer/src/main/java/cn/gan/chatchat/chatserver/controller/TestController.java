package cn.gan.chatchat.chatserver.controller;

import cn.gan.chatchat.chatserver.NettyBoot;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

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
        String name = nettyBoot.toString();
        System.out.println(name);
        System.out.println("fuck");
        return "hello word fuck fuck";
    }

}
