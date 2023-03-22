package com.orange.qqbot.api;

import com.alibaba.fastjson.JSONObject;
import com.orange.qqbot.utils.HttpUtil;
import com.orange.qqbot.utils.RedisCache;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.UUID;
import java.util.concurrent.TimeUnit;

/**
 * @author ：yilantingfeng
 * @version : v1.0
 * @project ：QQBot
 * @package : com.orange.qqbot.api
 * @name ：GPTApi
 * @date ：2023/3/20 14:52
 * @description :
 */
@Component
public class GPTApi {
    @Autowired
    private HttpUtil httpUtil;
    @Autowired
    private RedisCache redisCache;

    public JSONObject getAnswer(String question, String senderQq, String groupId) {
        String key = "session_id:" + senderQq + "::" + groupId;
        Object cacheObject = redisCache.getCacheObject(key);
        String sessionId = null;
        if (cacheObject != null) {
            sessionId = (String) cacheObject;
        } else {
            sessionId = UUID.randomUUID().toString();
        }
        redisCache.setCacheObject(key, sessionId, 30, TimeUnit.MINUTES);
        JSONObject params = new JSONObject();
        params.put("apiKey", "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");
        params.put("sessionId", sessionId);
        params.put("content", question);
        HashMap<String, String> headers = new HashMap<>();
        JSONObject result = httpUtil
                .setBaseUrl("https://api.openai-proxy.com")
                .postJson("/v1/chat/completions", params, headers).toJsonObject();
        return result;
    }
}
