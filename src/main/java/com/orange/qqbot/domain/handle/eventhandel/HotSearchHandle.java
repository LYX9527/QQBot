package com.orange.qqbot.domain.handle.eventhandel;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.orange.qqbot.api.SendMessage;
import com.orange.qqbot.domain.constant.CQ;
import com.orange.qqbot.utils.OkHttpUtil;

import java.util.HashMap;

/**
 * @author : yilantingfeng
 * @version : v1.0
 * @projectName : QQBot
 * @package : com.orange.qqbot.domain.handle.eventhandel
 * @className : HotSearch
 * @description:
 * @date : 2023/1/6 20:56
 */
public class HotSearchHandle {

    public static void handle(String groupId) {
        String s = OkHttpUtil.get("https://api.iwyu.com/API/weibo/", new HashMap<>(), new HashMap<>());
        JSONObject jsonObject = JSON.parseArray(s).getJSONObject(0);
        JSONArray data = jsonObject.getJSONArray("data");
        StringBuilder sb = new StringBuilder();
        sb.append("----------------实时热搜---------------");
        for (int i = 0; i < data.size(); i++) {
            JSONObject info = data.getJSONObject(i);
            String realpos = info.getString("realpos");
            if (realpos != null && !"".equals(realpos)) {
                sb.append("\n");
                sb.append(realpos);
                sb.append("、");
                sb.append(info.getString("word"));
                sb.append("\uD83D\uDD25");
                sb.append(" ");
                sb.append(info.getString("num"));
            }
        }
        sb.append("\n");
        sb.append("----------------到底啦！---------------");
        SendMessage.sendGroupMessage(sb.toString(), groupId, false);
    }
}
