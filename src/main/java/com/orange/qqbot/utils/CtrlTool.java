package com.orange.qqbot.utils;

import com.orange.qqbot.core.domain.RecallMessage;

/**
 * @author : yilantingfeng
 * @version : v1.0
 * @projectName : QQBot
 * @package : com.orange.qqbot.utils
 * @className : CtrlTool
 * @description:
 * @date : 2023/1/7 18:22
 */
public class CtrlTool {
    public static String timestampToText(Long time) {
        return new java.text.SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new java.util.Date(time * 1000));
    }

    /**
     * 获取撤回消息模板
     *
     * @param s             时间
     * @param recallMessage 消息
     * @param sb            模板
     */
    public static void getRecallMessageTemplate(RecallMessage recallMessage, String s, StringBuilder sb) {
        sb.append("\n");
        sb.append("撤回时间：");
        sb.append(s);
        sb.append("\n");
        sb.append("撤回内容：\n");
        sb.append("------------以下为撤回内容----------\n");
        sb.append(recallMessage.getRecallMessage());
        sb.append("\n");
        sb.append("------------以上为撤回内容----------\n");
    }
}
