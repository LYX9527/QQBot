package com.orange.qqbot.core.handle.noticehandle;

import com.alibaba.fastjson.JSONObject;
import com.orange.qqbot.api.GetMessage;
import com.orange.qqbot.api.SendMessage;
import com.orange.qqbot.core.factory.NoticeHandlerFactory;
import com.orange.qqbot.config.QBotConfig;
import com.orange.qqbot.core.NoticeHandler;
import com.orange.qqbot.core.domain.RecallMessage;
import com.orange.qqbot.core.domain.constant.Constants;
import com.orange.qqbot.core.domain.constant.NoticeType;
import com.orange.qqbot.utils.CtrlTool;
import org.springframework.stereotype.Component;

/**
 * @author : yilantingfeng
 * @version : v1.0
 * @projectName : QQBot
 * @package : com.orange.qqbot.core.handle.noticehandle
 * @className : FriendRecallHandle
 * @description:
 * @date : 2023/2/2 19:22
 */
@Component
public class FriendRecallHandler implements NoticeHandler {
    private static JSONObject postMessage;

    @Override
    public FriendRecallHandler init(JSONObject t) {
        FriendRecallHandler.postMessage = t;
        return this;
    }

    @Override
    public void run() {
        Long time = postMessage.getLong(Constants.TIME);
        String messageId = postMessage.getString(Constants.MESSAGE_ID);
        String s = CtrlTool.timestampToText(time);
        RecallMessage recallMessage = GetMessage.get(messageId);
        recallMessage.setRecallTime(s);
        StringBuilder sb = new StringBuilder();
        String USER = "[" + recallMessage.getSenderNickName() + "] (" + recallMessage.getSenderUserId() + ")";
        sb.append(USER);
        sb.append("：\n");
        sb.append("撤回了一条消息");
        CtrlTool.getRecallMessageTemplate(recallMessage, s, sb);
        SendMessage.sendPrivateMessage(sb.toString(), QBotConfig.getAdminQq(), false);
    }

    @Override
    public void afterPropertiesSet() throws Exception {
        NoticeHandlerFactory.register(NoticeType.FRIEND_RECALL, this);
    }
}
