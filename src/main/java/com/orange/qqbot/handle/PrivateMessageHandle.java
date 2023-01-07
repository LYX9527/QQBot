package com.orange.qqbot.handle;

import com.alibaba.fastjson.JSONObject;

/**
 * @author : yilantingfeng
 * @version : v1.0
 * @projectName : QQBot
 * @package : com.orange.qqbot.handle
 * @className : PrivateMessageHandle
 * @description:
 * @date : 2023/1/7 17:17
 */
public class PrivateMessageHandle {
    private static JSONObject postMessage;

    public PrivateMessageHandle init(JSONObject postMessage) {
        PrivateMessageHandle.postMessage = postMessage;
        return this;
    }

    public void run() {
        System.out.println("-----------------------私聊消息开始--------------------------");
        System.out.println(postMessage);
        System.out.println("-----------------------私聊消息结束--------------------------");
    }
}
