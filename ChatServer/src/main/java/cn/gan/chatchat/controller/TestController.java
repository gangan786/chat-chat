package cn.gan.chatchat.controller;

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

    @RequestMapping("/helloWord")
    public Object test() {
        return "hello word";
    }

}
