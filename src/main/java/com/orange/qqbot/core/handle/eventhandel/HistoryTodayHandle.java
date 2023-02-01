package com.orange.qqbot.core.handle.eventhandel;

import com.alibaba.fastjson.JSONObject;
import com.orange.qqbot.api.SendMessage;
import com.orange.qqbot.config.KeyWordHandlerFactory;
import com.orange.qqbot.core.KWHandler;
import com.orange.qqbot.core.domain.constant.Constants;
import com.orange.qqbot.core.domain.constant.KeyWord;
import com.orange.qqbot.core.domain.constant.MessageType;
import com.orange.qqbot.utils.MessageParser;
import com.orange.qqbot.utils.OkHttpUtil;

import java.util.HashMap;

/**
 * @author : yilantingfeng
 * @version : v1.0
 * @projectName : QQBot
 * @package : com.orange.qqbot.domain.handle.eventhandel
 * @className : HistoryHandle
 * @description:
 * @date : 2023/1/6 21:37
 */
public class HistoryTodayHandle implements KWHandler {
    private static JSONObject postMessage;

    @Override
    public HistoryTodayHandle init(JSONObject t) {
        HistoryTodayHandle.postMessage = t;
        return this;
    }

    @Override
    public void run(String messageType) {
        if (MessageType.GROUP.equals(messageType)) {
            handleGroup();
        } else if (MessageType.PRIVATE.equals(messageType)) {
            handlePrivate();
        }
    }

    @Override
    public void handlePrivate() {
        MessageParser messageParser = new MessageParser(postMessage);
        SendMessage.sendPrivateMessage(getContent(), messageParser.getSenderQq(), false);
    }

    @Override
    public void handleGroup() {
        SendMessage.sendGroupMessage(getContent(), postMessage.getString(Constants.GROUP_ID), false);
    }

    @Override
    public void afterPropertiesSet() throws Exception {
        KeyWordHandlerFactory.register(KeyWord.HISTORY_TODAY, this);
        KeyWordHandlerFactory.register(KeyWord.HISTORY_TODAY, MessageType.PRIVATE, this);
    }

    private String getContent() {
        String s = OkHttpUtil.get("https://api.iwyu.com/API/lsjt/", new HashMap<>(), new HashMap<>());
        String[] split = s
                .replaceAll("</br>", "")
                .replaceAll("注意：由内容过长，只显示10个列", "")
                .split(" ");
        StringBuilder sb = new StringBuilder();
        sb.append("---------------历史上的今天-------------");
        for (String value : split) {
            sb.append("\n");
            sb.append(value);
        }
        sb.append("\n");
        sb.append("----------------到底啦！---------------");
        return sb.toString();
    }
}
