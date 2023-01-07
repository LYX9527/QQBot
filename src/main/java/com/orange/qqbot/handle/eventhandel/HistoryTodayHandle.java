package com.orange.qqbot.handle.eventhandel;

import com.orange.qqbot.api.SendMessage;
import com.orange.qqbot.utils.OkHttpUtil;

import java.util.HashMap;

/**
 * @author : yilantingfeng
 * @version : v1.0
 * @projectName : QQBot
 * @package : com.orange.qqbot.domain.handle.eventhandel
 * @className : HistoryHandle
 * @description:
 * @date : 2023/1/6 21:37
 */
public class HistoryTodayHandle {

    public static void handle(String... groupIds) {
        String s = OkHttpUtil.get("https://api.iwyu.com/API/lsjt/", new HashMap<>(), new HashMap<>());
        String[] split = s
                .replaceAll("</br>", "")
                .replaceAll("注意：由内容过长，只显示10个列", "")
                .split(" ");
        StringBuilder sb = new StringBuilder();
        sb.append("---------------历史上的今天-------------");
        for (String value : split) {
            sb.append("\n");
            sb.append(value);
        }
        sb.append("\n");
        sb.append("----------------到底啦！---------------");
        String sendContent = sb.toString();
        for (String groupId : groupIds) {
            SendMessage.sendGroupMessage(sendContent, groupId, false);
        }
    }
}
