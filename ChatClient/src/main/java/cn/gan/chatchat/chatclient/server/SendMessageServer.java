package cn.gan.chatchat.chatclient.server;

/**
 * 深圳依时货拉拉科技有限公司 版权所有 © Copyright 2020 <br>
 *
 * @Description: TODO
 * @CreateDate: 2020/7/31 8:07 下午 <br>
 * @Author: Gangan.chen
 */

public interface SendMessageServer {
    void connect(String url);
    void sendMessage(String host,String port);
}
