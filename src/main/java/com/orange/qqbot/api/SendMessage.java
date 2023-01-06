package com.orange.qqbot.api;

import com.alibaba.fastjson.JSONObject;
import com.orange.qqbot.domain.constant.Constants;
import com.orange.qqbot.utils.OkHttpUtil;
import org.springframework.beans.factory.annotation.Value;
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

    public static void sendPrivateMessage(String message, String userId, Boolean autoEscape) {
        if (autoEscape == null) {
            autoEscape = true;
        }
        String requestUrl = Constants.HTTP + url + "/send_private_msg";
        JSONObject params = new JSONObject();
        params.put("user_id", userId);
        params.put("message", message);
        params.put("auto_escape", autoEscape);
        String s = OkHttpUtil.postJsonParams(requestUrl, params.toJSONString(), new HashMap<>());
    }

    public static void sendGroupMessage(String message, String groupId, Boolean autoEscape) {
        if (autoEscape == null) {
            autoEscape = true;
        }
        String requestUrl = Constants.HTTP + url + "/send_group_msg";
        JSONObject params = new JSONObject();
        params.put("group_id", groupId);
        params.put("message", message);
        params.put("auto_escape", autoEscape);
        String s = OkHttpUtil.postJsonParams(requestUrl, params.toJSONString(), new HashMap<>());
    }
}
