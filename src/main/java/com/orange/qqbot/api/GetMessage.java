package com.orange.qqbot.api;

import com.alibaba.fastjson.JSONObject;
import com.orange.qqbot.config.QBotConfig;
import com.orange.qqbot.domain.RecallMessage;
import com.orange.qqbot.domain.constant.Api;
import com.orange.qqbot.domain.constant.Constants;
import com.orange.qqbot.utils.OkHttpUtil;

import java.util.HashMap;

/**
 * @author : yilantingfeng
 * @version : v1.0
 * @projectName : QQBot
 * @package : com.orange.qqbot.api
 * @className : GetMessage
 * @description:
 * @date : 2023/1/7 17:58
 */
public class GetMessage {
    public static RecallMessage get(String messageId) {
        String url = Constants.HTTP + QBotConfig.getUrl() + Api.GET_MSG;
        JSONObject params = new JSONObject();
        params.put(Constants.MESSAGE_ID, messageId);
        String s = OkHttpUtil.postJsonParams(url, params.toJSONString(), new HashMap<>());
        JSONObject jsonObject = JSONObject.parseObject(s);
        JSONObject data = jsonObject.getJSONObject("data");
        RecallMessage recallMessage = new RecallMessage();
        recallMessage.setRecallMessage(data.getString(Constants.MESSAGE));
        recallMessage.setSenderNickName(data.getJSONObject(Constants.SENDER).getString(Constants.NICKNAME));
        recallMessage.setSenderUserId(data.getJSONObject(Constants.SENDER).getString(Constants.USER_ID));
        return recallMessage;
    }
}
