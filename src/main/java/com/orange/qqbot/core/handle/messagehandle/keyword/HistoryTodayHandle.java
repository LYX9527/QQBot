package com.orange.qqbot.core.handle.messagehandle.keyword;

import com.alibaba.fastjson.JSONObject;
import com.orange.qqbot.api.SendMessage;
import com.orange.qqbot.core.factory.KeyWordHandlerFactory;
import com.orange.qqbot.core.KeywordHandler;
import com.orange.qqbot.core.domain.constant.Constants;
import com.orange.qqbot.core.domain.constant.KeyWord;
import com.orange.qqbot.core.domain.constant.MessageType;
import com.orange.qqbot.utils.MessageParser;
import com.orange.qqbot.utils.OkHttpUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import java.util.HashMap;

/**
 * @author : yilantingfeng
 * @version : v1.0
 * @projectName : QQBot
 * @package : com.orange.qqbot.domain.handle.eventhandel
 * @className : HistoryHandle
 * @description:
 * @date : 2023/1/6 21:37
 * @description : 历史上的今天处理内置类
 */
@Component
public class HistoryTodayHandle implements KeywordHandler {
    private static final Logger logger = LoggerFactory.getLogger(HistoryTodayHandle.class);
    private static JSONObject postMessage;

    @Override
    public void afterPropertiesSet() {
        KeyWordHandlerFactory.register(KeyWord.HISTORY_TODAY, this);
        KeyWordHandlerFactory.register(KeyWord.HISTORY_TODAY, MessageType.PRIVATE, this);
    }

    @Override
    public HistoryTodayHandle init(JSONObject t) {
        HistoryTodayHandle.postMessage = t;
        return this;
    }

    @Override
    public void handlePrivate() {
        MessageParser messageParser = new MessageParser(postMessage);
        String senderQq = messageParser.getSenderQq();
        SendMessage.sendPrivateMessage(getContent(), senderQq, false);
        logger.info("发送历史上的今天成功,在私聊《" + senderQq + "》");
    }

    @Override
    public void handleGroup() {
        String groupId = postMessage.getString(Constants.GROUP_ID);
        SendMessage.sendGroupMessage(getContent(), groupId, false);
        logger.info("发送历史上的今天成功,在群《" + groupId + "》");
    }

    @Override
    public String getContent() {
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
