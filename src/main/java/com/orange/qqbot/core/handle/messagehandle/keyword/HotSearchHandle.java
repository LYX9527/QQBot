package com.orange.qqbot.core.handle.messagehandle.keyword;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
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
 * @className : HotSearch
 * @description:
 * @date : 2023/1/6 20:56
 * @description : 热搜处理内置类
 */
@Component
public class HotSearchHandle implements KeywordHandler {
    private static final Logger logger = LoggerFactory.getLogger(HotSearchHandle.class);

    private static JSONObject postMessage;

    @Override
    public HotSearchHandle init(JSONObject t) {
        HotSearchHandle.postMessage = t;
        return this;
    }

    @Override
    public void afterPropertiesSet() throws Exception {
        KeyWordHandlerFactory.register(KeyWord.HOT_SEARCH, this);
        KeyWordHandlerFactory.register(KeyWord.HOT_SEARCH, MessageType.PRIVATE, this);
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
        String senderQq = messageParser.getSenderQq();
        SendMessage.sendPrivateMessage(getContent(), senderQq, false);
        logger.info("发送热搜成功,在私聊《" + senderQq + "》");
    }

    @Override
    public void handleGroup() {
        String groupId = postMessage.getString(Constants.GROUP_ID);
        SendMessage.sendGroupMessage(getContent(), groupId, false);
        logger.info("发送热搜成功,在群《" + groupId + "》");
    }

    @Override
    public String getContent() {
        String s = OkHttpUtil.get("https://api.iwyu.com/API/weibo/", new HashMap<>(), new HashMap<>());
        JSONObject jsonObject = JSON.parseArray(s).getJSONObject(0);
        JSONArray data = jsonObject.getJSONArray("data");
        StringBuilder sb = new StringBuilder();
        sb.append("----------------实时热搜---------------");
        for (int i = 0; i < data.size(); i++) {
            JSONObject info = data.getJSONObject(i);
            String realpos = info.getString("realpos");
            if (realpos != null && !"".equals(realpos)) {
                sb.append("\n");
                sb.append(realpos);
                sb.append("、");
                sb.append(info.getString("word"));
                sb.append("\uD83D\uDD25");
                sb.append(" ");
                sb.append(info.getString("num"));
            }
        }
        sb.append("\n");
        sb.append("----------------到底啦！---------------");
        return sb.toString();
    }
}
