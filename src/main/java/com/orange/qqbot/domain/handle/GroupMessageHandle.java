package com.orange.qqbot.domain.handle;

import com.alibaba.fastjson.JSONObject;
import com.orange.qqbot.api.SendMessage;
import com.orange.qqbot.domain.constant.Constants;

/**
 * @author : yilantingfeng
 * @version : v1.0
 * @projectName : QQBot
 * @package : com.orange.qqbot.domain.handle
 * @className : GroupMessagehandle
 * @description:
 * @date : 2023/1/6 15:05
 */
public class GroupMessageHandle {

    private static JSONObject postMessage;

    public GroupMessageHandle init(JSONObject postMessage) {
        GroupMessageHandle.postMessage = postMessage;
        return this;
    }

    public void run() {
        System.out.println("-----------------------消息开始--------------------------");
        System.out.println(postMessage);
        JSONObject sender = postMessage.getJSONObject("sender");
        String nickname = sender.getString("nickname");
        String card = sender.getString("card");
        String rawMessage = postMessage.getString("raw_message");
        // 匹配rawMessage中的CQ码中的at
        String atqq = rawMessage.replaceAll(".*\\[CQ:at,qq=(\\d+)\\].*", "$1");
        String qq = SendMessage.getQq();
        if (qq.equals(atqq)) {
            String message = rawMessage.replaceAll("\\[CQ:at,qq=" + qq + "\\]", "");
            SendMessage.sendGroupMessage("@" + nickname + " " + message, postMessage.getString(Constants.GROUP_ID), false);
        }
        System.out.println(card + "(" + nickname + "):" + rawMessage);
        System.out.println("-----------------------消息结束--------------------------");
    }
}
