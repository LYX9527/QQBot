package com.orange.qqbot.register;

import com.alibaba.fastjson.JSONObject;
import com.orange.qqbot.core.factory.KeyWordHandlerFactory;
import com.orange.qqbot.core.CustomizeMessageHandle;
import com.orange.qqbot.core.annotation.MessageEventHandler;
import com.orange.qqbot.core.annotation.PrivateMessage;
import com.orange.qqbot.core.domain.constant.KeyWord;
import com.orange.qqbot.core.domain.constant.MessageType;

/**
 * @author : yilantingfeng
 * @version : v1.0
 * @projectName : QQBot
 * @package : com.orange.qqbot.service
 * @className : PrivateMessageHandle
 * @description:
 * @date : 2023/2/1 18:39
 */
@MessageEventHandler
public class PrivateMessageEventRegister implements CustomizeMessageHandle {
    @Override
    @PrivateMessage
    public void handle(JSONObject postMessage) {
        KeyWordHandlerFactory.getInvokeHandler(KeyWord.KEYWORD, MessageType.PRIVATE).init(postMessage).run(MessageType.PRIVATE);
    }
}
