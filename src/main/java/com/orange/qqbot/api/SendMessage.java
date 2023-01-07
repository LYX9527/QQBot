package com.orange.qqbot.api;

import com.alibaba.fastjson.JSONObject;
import com.orange.qqbot.domain.constant.Api;
import com.orange.qqbot.domain.constant.Constants;
import com.orange.qqbot.utils.OkHttpUtil;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

import java.util.HashMap;

/**
 * @author : yilantingfeng
 * @version : v1.0
 * @projectName : QQBot
 * @package : com.orange.qqbot.api
 * @className : SendMessage
 * @description:
 * @date : 2023/1/6 17:05
 */
@Component
@ConfigurationProperties(prefix = "qbot")
public class SendMessage {
    private static String url;

    private static String qq;

    public static String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        SendMessage.url = url;
    }

    public static String getQq() {
        return qq;
    }

    public void setQq(String qq) {
        SendMessage.qq = qq;
    }

    /**
     * 发送私聊消息
     *
     * @param message    消息内容
     * @param userId     用户id
     * @param autoEscape 是否转义
     */
    public static void sendPrivateMessage(String message, String userId, Boolean autoEscape) {
        if (autoEscape == null) {
            autoEscape = true;
        }
        String requestUrl = Constants.HTTP + url + Api.SEND_PRIVATE_MSG;
        JSONObject params = new JSONObject();
        params.put("user_id", userId);
        params.put("message", message);
        params.put("auto_escape", autoEscape);
        String s = OkHttpUtil.postJsonParams(requestUrl, params.toJSONString(), new HashMap<>());
    }

    /**
     * 发送群消息
     *
     * @param message    消息内容
     * @param groupId    群id
     * @param autoEscape 是否转义
     */
    public static void sendGroupMessage(String message, String groupId, Boolean autoEscape) {
        if (autoEscape == null) {
            autoEscape = true;
        }
        String requestUrl = Constants.HTTP + url + Api.SEND_GROUP_MSG;
        JSONObject params = new JSONObject();
        params.put("group_id", groupId);
        params.put("message", message);
        params.put("auto_escape", autoEscape);
        String s = OkHttpUtil.postJsonParams(requestUrl, params.toJSONString(), new HashMap<>());
    }
}
