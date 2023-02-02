package com.orange.qqbot.register;

import com.alibaba.fastjson.JSONObject;
import com.orange.qqbot.core.factory.KeyWordHandlerFactory;
import com.orange.qqbot.core.CustomizeMessageHandle;
import com.orange.qqbot.core.annotation.MessageEventHandler;
import com.orange.qqbot.core.annotation.GroupMessage;
import com.orange.qqbot.core.domain.constant.KeyWord;
import com.orange.qqbot.core.domain.constant.MessageType;

/**
 * @author : yilantingfeng
 * @version : v1.0
 * @projectName : QQBot
 * @package : com.orange.qqbot.service
 * @className : TestAnnotation
 * @description:
 * @date : 2023/2/1 11:06
 */
@MessageEventHandler
public class GroupMessageEventRegister implements CustomizeMessageHandle {
    @Override
    @GroupMessage
    public void handle(JSONObject postMessage) {
        KeyWordHandlerFactory.getInvokeHandler(KeyWord.KEYWORD, MessageType.GROUP).init(postMessage).run(MessageType.GROUP);
    }
}
