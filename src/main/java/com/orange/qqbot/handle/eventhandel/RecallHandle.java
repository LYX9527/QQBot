package com.orange.qqbot.handle.eventhandel;

import com.orange.qqbot.api.GetMessage;
import com.orange.qqbot.api.SendMessage;
import com.orange.qqbot.config.QBotConfig;
import com.orange.qqbot.domain.RecallMessage;
import com.orange.qqbot.utils.CtrlTool;

/**
 * @author : yilantingfeng
 * @version : v1.0
 * @projectName : QQBot
 * @package : com.orange.qqbot.handle.eventhandel
 * @className : RecallHandle
 * @description:
 * @date : 2023/1/7 17:53
 */
public class RecallHandle {
    /**
     * 群组撤回消息处理
     *
     * @param messageId  消息ID
     * @param groupId    群组ID
     * @param userId     撤回人ID
     * @param operatorId 操作人ID
     * @param timestamp  时间戳
     */
    public static void groupHandle(String messageId, String groupId, String userId, String operatorId, Long timestamp) {
        String s = CtrlTool.timestampToText(timestamp);
        RecallMessage recallMessage = GetMessage.get(messageId);
        recallMessage.setRecallTime(s);
        StringBuilder sb = new StringBuilder();
        boolean isSelfCtrl = userId.trim().equals(operatorId.trim());
        String USER = "[" + recallMessage.getSenderNickName() + "] (" + recallMessage.getSenderUserId() + ")";
        if (isSelfCtrl) {
            sb.append(USER);
            sb.append("：");
            sb.append("自己撤回了一条消息");
        } else {
            sb.append("管理员撤回了：\n");
            sb.append(USER);
            sb.append(" 的一条消息");
        }
        getRecallMessageTemplate(s, recallMessage, sb);
        SendMessage.sendGroupMessage(sb.toString(), groupId, false);
    }


    /**
     * 好友撤回消息处理
     *
     * @param messageId 消息ID
     * @param timestamp 时间戳
     */
    public static void friendHandle(String messageId, Long timestamp) {
        String s = CtrlTool.timestampToText(timestamp);
        RecallMessage recallMessage = GetMessage.get(messageId);
        recallMessage.setRecallTime(s);
        StringBuilder sb = new StringBuilder();
        String USER = "[" + recallMessage.getSenderNickName() + "] (" + recallMessage.getSenderUserId() + ")";
        sb.append(USER);
        sb.append("：\n");
        sb.append("撤回了一条消息");
        getRecallMessageTemplate(s, recallMessage, sb);
        SendMessage.sendPrivateMessage(sb.toString(), QBotConfig.getAdminQq(), false);
    }

    /**
     * 获取撤回消息模板
     *
     * @param s             时间
     * @param recallMessage 消息
     * @param sb            模板
     */
    private static void getRecallMessageTemplate(String s, RecallMessage recallMessage, StringBuilder sb) {
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
