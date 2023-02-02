package com.orange.qqbot.api;

import com.alibaba.fastjson.JSONObject;
import com.orange.qqbot.config.QBotConfig;
import com.orange.qqbot.core.domain.constant.Api;
import com.orange.qqbot.core.domain.constant.Constants;
import com.orange.qqbot.utils.OkHttpUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

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

public class SendMessage {
    private static final Logger logger = LoggerFactory.getLogger(SendMessage.class);

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
        String requestUrl = Constants.HTTP + QBotConfig.getUrl() + Api.SEND_PRIVATE_MSG;
        JSONObject params = new JSONObject();
        params.put(Constants.USER_ID, userId);
        params.put(Constants.MESSAGE, message);
        params.put(Constants.AUTO_ESCAPE, autoEscape);
        String s = OkHttpUtil.postJsonParams(requestUrl, params.toJSONString(), new HashMap<>());
        System.out.println(s);
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
        String requestUrl = Constants.HTTP + QBotConfig.getUrl() + Api.SEND_GROUP_MSG;
        JSONObject params = new JSONObject();
        params.put(Constants.GROUP_ID, groupId);
        params.put(Constants.MESSAGE, message);
        params.put(Constants.AUTO_ESCAPE, autoEscape);
        String s = OkHttpUtil.postJsonParams(requestUrl, params.toJSONString(), new HashMap<>());
        System.out.println(s);
    }
}
