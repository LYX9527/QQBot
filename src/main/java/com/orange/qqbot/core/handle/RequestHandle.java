package com.orange.qqbot.core.handle;

import com.alibaba.fastjson.JSONObject;
import com.orange.qqbot.config.MessageHandlerFactory;
import com.orange.qqbot.core.MessageHandler;
import com.orange.qqbot.core.domain.constant.PostType;
import org.springframework.stereotype.Component;

/**
 * @author : yilantingfeng
 * @version : v1.0
 * @projectName : QQBot
 * @package : com.orange.qqbot.handle
 * @className : RequestHandle
 * @description:
 * @date : 2023/1/31 13:13
 */
@Component
public class RequestHandle implements MessageHandler {
    private static JSONObject postMessage;

    @Override
    public RequestHandle init(JSONObject t) {
        RequestHandle.postMessage = t;
        return this;
    }

    @Override
    public void run() {
        System.out.println("请求");
        System.out.println(postMessage);
    }

    @Override
    public void afterPropertiesSet() {
        MessageHandlerFactory.register(PostType.REQUEST, this);
    }
}
