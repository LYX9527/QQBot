package com.orange.qqbot.core.handle.eventhandel;

import com.alibaba.fastjson.JSONObject;
import com.orange.qqbot.config.KeyWordHandlerFactory;
import com.orange.qqbot.core.KWHandler;
import com.orange.qqbot.core.domain.constant.Constants;
import com.orange.qqbot.core.domain.constant.KeyWord;
import com.orange.qqbot.core.domain.constant.MessageType;
import com.orange.qqbot.utils.MessageParser;

/**
 * @author : yilantingfeng
 * @version : v1.0
 * @projectName : QQBot
 * @package : com.orange.qqbot.handle.eventhandel
 * @className : KeyWordHandle
 * @description:
 * @date : 2023/1/8 17:46
 */
public class KeyWordHandle implements KWHandler {

    private static JSONObject postMessage;

    public static void handle(String message, String groupId) {
        String keyword = message.trim();
        switch (keyword) {
            case KeyWord.TIME -> NowTimeHandle.handle(groupId);
            case KeyWord.HOT_SEARCH -> HotSearchHandle.handle(groupId);
            default -> System.out.println("未知关键字");
        }
    }

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
        KWHandler invokeHandler = KeyWordHandlerFactory.getInvokeHandler(message, messageType);
        try {
            invokeHandler.init(postMessage).run(messageType);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    @Override
    public void afterPropertiesSet() throws Exception {
        KeyWordHandlerFactory.register(KeyWord.KEYWORD, this);
        KeyWordHandlerFactory.register(KeyWord.KEYWORD, MessageType.PRIVATE, this);
    }
}
