package com.orange.qqbot.handle.messagehandle;

import com.alibaba.fastjson.JSONObject;
import com.orange.qqbot.config.MessageHandlerFactory;
import com.orange.qqbot.config.QBotConfig;
import com.orange.qqbot.domain.constant.Constants;
import com.orange.qqbot.domain.constant.MessageType;
import com.orange.qqbot.handle.Handler;
import com.orange.qqbot.handle.eventhandel.KeyWordHandle;
import org.springframework.stereotype.Component;

/**
 * @author : yilantingfeng
 * @version : v1.0
 * @projectName : QQBot
 * @package : com.orange.qqbot.domain.handle
 * @className : GroupMessagehandle
 * @description:
 * @date : 2023/1/6 15:05
 */
@Component
public class GroupMessageHandle implements Handler {

    private static JSONObject postMessage;

    public GroupMessageHandle init(JSONObject postMessage) {
        GroupMessageHandle.postMessage = postMessage;
        return this;
    }

    public void run() {
        System.out.println("-----------------------群消息开始--------------------------");
        JSONObject sender = postMessage.getJSONObject("sender");
        String nickname = sender.getString("nickname");
        String userId = sender.getString("user_id");
        String card = sender.getString("card");
        String rawMessage = postMessage.getString("raw_message");
        // 匹配rawMessage中的CQ码中的at
        String atQq = rawMessage.replaceAll(".*\\[CQ:at,qq=(\\d+)\\].*", "$1");
        String qq = QBotConfig.getBotqq();
        String groupId = postMessage.getString(Constants.GROUP_ID);
        if (qq.equals(atQq)) {
            String message = rawMessage.replaceAll("\\[CQ:at,qq=" + qq + "\\]", "");
            KeyWordHandle.handle(message, groupId);
        }
        System.out.println(card + "(" + nickname + "):" + rawMessage);
        System.out.println("-----------------------消息结束--------------------------");
    }

    @Override
    public void afterPropertiesSet() {
        MessageHandlerFactory.register(MessageType.GROUP, this);
    }
}
