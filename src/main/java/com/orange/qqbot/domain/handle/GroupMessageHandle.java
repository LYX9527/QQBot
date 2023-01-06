package com.orange.qqbot.domain.handle;

import com.alibaba.fastjson.JSONObject;

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
        System.out.println(card + "(" + nickname + "):" + rawMessage);
        System.out.println("-----------------------消息结束--------------------------");
    }
}
