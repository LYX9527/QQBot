package com.orange.qqbot.handle.messagehandle;

import com.alibaba.fastjson.JSONObject;
import com.orange.qqbot.config.MessageHandlerFactory;
import com.orange.qqbot.domain.constant.MessageType;
import com.orange.qqbot.handle.Handler;
import org.springframework.stereotype.Component;

/**
 * @author : yilantingfeng
 * @version : v1.0
 * @projectName : QQBot
 * @package : com.orange.qqbot.handle
 * @className : PrivateMessageHandle
 * @description:
 * @date : 2023/1/7 17:17
 */
@Component
public class PrivateMessageHandle implements Handler {
    private static JSONObject postMessage;

    public PrivateMessageHandle init(JSONObject postMessage) {
        PrivateMessageHandle.postMessage = postMessage;
        return this;
    }

    public void run() {
        System.out.println("-----------------------私聊消息开始--------------------------");
        System.out.println(postMessage);
        System.out.println("-----------------------私聊消息结束--------------------------");
    }

    @Override
    public void afterPropertiesSet() throws Exception {
        MessageHandlerFactory.register(MessageType.PRIVATE, this);
    }
}
