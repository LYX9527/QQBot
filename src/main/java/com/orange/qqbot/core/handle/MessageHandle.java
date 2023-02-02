package com.orange.qqbot.core.handle;

import com.alibaba.fastjson.JSONObject;
import com.orange.qqbot.core.factory.EventHandlerFactory;
import com.orange.qqbot.core.factory.MessageHandlerFactory;
import com.orange.qqbot.core.MessageHandler;
import com.orange.qqbot.core.domain.constant.Constants;
import com.orange.qqbot.core.domain.constant.PostType;
import org.springframework.stereotype.Component;

/**
 * @author : yilantingfeng
 * @version : v1.0
 * @projectName : QQBot
 * @package : com.orange.qqbot.handle
 * @className : MessageHandle
 * @description:
 * @date : 2023/1/31 13:46
 */
@Component
public class MessageHandle implements MessageHandler {
    private static JSONObject postMessage;

    @Override
    public MessageHandle init(JSONObject t) {
        MessageHandle.postMessage = t;
        return this;
    }

    @Override
    public void run() {
        String messageType = postMessage.getString(Constants.MESSAGE_TYPE);
        MessageHandler invokeMessageHandler = MessageHandlerFactory.getInvokeHandler(messageType);
        try {
            invokeMessageHandler.init(postMessage).run();
        } catch (Exception e) {
            MessageHandlerFactory.getInvokeHandler(Constants.DEFAULT).init(postMessage).run();
        }
    }

    @Override
    public void afterPropertiesSet() {
        EventHandlerFactory.register(PostType.MESSAGE, this);
    }
}
