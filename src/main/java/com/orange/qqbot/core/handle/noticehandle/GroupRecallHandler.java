package com.orange.qqbot.core.handle.noticehandle;

import com.alibaba.fastjson.JSONObject;
import com.orange.qqbot.api.GetMessage;
import com.orange.qqbot.api.SendMessage;
import com.orange.qqbot.core.factory.NoticeHandlerFactory;
import com.orange.qqbot.core.NoticeHandler;
import com.orange.qqbot.core.domain.RecallMessage;
import com.orange.qqbot.core.domain.constant.NoticeType;
import com.orange.qqbot.utils.CtrlTool;
import com.orange.qqbot.utils.MessageParser;
import org.springframework.stereotype.Component;

/**
 * @author : yilantingfeng
 * @version : v1.0
 * @projectName : QQBot
 * @package : com.orange.qqbot.handle.eventhandel
 * @className : RecallHandle
 * @description:
 * @date : 2023/1/7 17:53
 */
@Component
public class GroupRecallHandler implements NoticeHandler {
    private static JSONObject postMessage;

    @Override
    public GroupRecallHandler init(JSONObject t) {
        GroupRecallHandler.postMessage = t;
        return this;
    }

    @Override
    public void run() {
        MessageParser messageParser = new MessageParser(postMessage);
        String messageId = messageParser.getMessageId();
        String userId = messageParser.getUserId();
        String groupId = messageParser.getGroupId();
        String operatorId = messageParser.getOperatorId();
        Long time = messageParser.getTime();
        String s = CtrlTool.timestampToText(time);
        RecallMessage recallMessage = GetMessage.get(messageId);
        recallMessage.setRecallTime(s);
        StringBuilder sb = new StringBuilder();
        boolean isSelfCtrl = userId.trim().equals(operatorId.trim());
        String user = "[" + recallMessage.getSenderNickName() + "] (" + recallMessage.getSenderUserId() + ")";
        if (isSelfCtrl) {
            sb.append(user);
            sb.append("：");
            sb.append("自己撤回了一条消息");
        } else {
            sb.append("管理员撤回了：\n");
            sb.append(user);
            sb.append(" 的一条消息");
        }
        CtrlTool.getRecallMessageTemplate(recallMessage, s, sb);
        SendMessage.sendGroupMessage(sb.toString(), groupId, false);
    }

    @Override
    public void afterPropertiesSet() throws Exception {
        NoticeHandlerFactory.register(NoticeType.GROUP_RECALL, this);
    }
}
