package com.orange.qqbot.domain.handle;

import com.alibaba.fastjson.JSONObject;
import com.orange.qqbot.domain.constant.Constants;

import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;

/**
 * @author : yilantingfeng
 * @version : v1.0
 * @projectName : QQBot
 * @package : com.orange.qqbot.domain.handle
 * @className : MetaEvenHandle
 * @description:
 * @date : 2023/1/6 15:25
 */
public class MetaEvenHandle {
    private static JSONObject postMessage;

    public MetaEvenHandle init(JSONObject postMessage) {
        MetaEvenHandle.postMessage = postMessage;
        return this;
    }

    public void run() {
        Long timestamp = postMessage.getLong("time");
        Boolean aBoolean = postMessage.getJSONObject("status").getBoolean("online");
        // 时间戳转LocalDateTime
        LocalDateTime localDateTime = LocalDateTime.ofInstant(Instant.ofEpochMilli(timestamp * 1000), ZoneId.systemDefault());
        String time = localDateTime.format(DateTimeFormatter.ofPattern(Constants.DATE_TIME_FORMATTER));
        if (!aBoolean) {
            System.out.println("当前时间：" + time + "，机器人当前状态：" + "离线");
        }
    }
}
