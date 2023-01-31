package com.orange.qqbot.handle;

import com.alibaba.fastjson.JSONObject;
import com.orange.qqbot.config.MessageHandlerFactory;
import com.orange.qqbot.domain.constant.Constants;
import com.orange.qqbot.domain.constant.PostType;
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
public class MessageHandle implements Handler {
    private static JSONObject postMessage;

    @Override
    public MessageHandle init(JSONObject t) {
        MessageHandle.postMessage = t;
        return this;
    }

    @Override
    public void run() {
        String messageType = postMessage.getString(Constants.MESSAGE_TYPE);
        Handler invokeHandler = MessageHandlerFactory.getInvokeHandler(messageType);
        try {
            invokeHandler.init(postMessage).run();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public void afterPropertiesSet() throws Exception {
        MessageHandlerFactory.register(PostType.MESSAGE, this);
    }
}
