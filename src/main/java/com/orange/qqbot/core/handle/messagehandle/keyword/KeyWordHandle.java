package com.orange.qqbot.core.handle.messagehandle.keyword;

import com.alibaba.fastjson.JSONObject;
import com.orange.qqbot.core.factory.EventHandlerFactory;
import com.orange.qqbot.core.factory.KeyWordHandlerFactory;
import com.orange.qqbot.core.KeywordHandler;
import com.orange.qqbot.core.domain.constant.Constants;
import com.orange.qqbot.core.domain.constant.KeyWord;
import com.orange.qqbot.core.domain.constant.MessageType;
import com.orange.qqbot.utils.MessageParser;
import org.springframework.stereotype.Component;

/**
 * @author : yilantingfeng
 * @version : v1.0
 * @projectName : QQBot
 * @package : com.orange.qqbot.handle.eventhandel
 * @className : KeyWordHandle
 * @description:
 * @date : 2023/1/8 17:46
 */
@Component
public class KeyWordHandle implements KeywordHandler {

    private static JSONObject postMessage;

    @Override
    public KeyWordHandle init(JSONObject t) {
        KeyWordHandle.postMessage = t;
        return this;
    }

    @Override
    public void run(String messageType) {
        String message;
        if (MessageType.PRIVATE.equals(messageType)) {
            message = postMessage.getString(Constants.MESSAGE);
        } else {
            MessageParser messageParser = new MessageParser(postMessage);
            message = messageParser.getAtMessage();
        }
        KeywordHandler invokeHandler = KeyWordHandlerFactory.getInvokeHandler(message.trim(), messageType);
        try {
            invokeHandler.init(postMessage).run(messageType);
        } catch (Exception e) {
            EventHandlerFactory.getInvokeHandler(Constants.DEFAULT).init(postMessage).run();
        }
    }

    @Override
    public void afterPropertiesSet() {
        KeyWordHandlerFactory.register(KeyWord.KEYWORD, this);
        KeyWordHandlerFactory.register(KeyWord.KEYWORD, MessageType.PRIVATE, this);
    }
}
