package com.orange.qqbot.domain.handle.eventhandel;

import cn.hutool.core.date.ChineseDate;
import com.orange.qqbot.api.SendMessage;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

/**
 * @author : yilantingfeng
 * @version : v1.0
 * @projectName : QQBot
 * @package : com.orange.qqbot.domain.handle.eventhandel
 * @className : NowTimeHandle
 * @description:
 * @date : 2023/1/6 20:19
 */
public class NowTimeHandle {
    public static void handle(String groupId) {
        ChineseDate chineseDate = new ChineseDate(LocalDate.now());
        StringBuilder sb = new StringBuilder();
        String s = LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyy年MM月dd日 HH:mm:ss"));
        sb.append("现在是：\n");
        sb.append(s);
        sb.append(" 星期");
        int value = LocalDateTime.now().getDayOfWeek().getValue();
        switch (value) {
            case 1 -> sb.append("一");
            case 2 -> sb.append("二");
            case 3 -> sb.append("三");
            case 4 -> sb.append("四");
            case 5 -> sb.append("五");
            case 6 -> sb.append("六");
            case 7 -> sb.append("日");
        }
        sb.append("\n");
        sb.append("农历:");
        String ch = chineseDate.toString();
        sb.append(ch);
        SendMessage.sendGroupMessage(sb.toString(), groupId, false);
    }
}
