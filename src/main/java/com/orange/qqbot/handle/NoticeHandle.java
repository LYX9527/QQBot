package com.orange.qqbot.handle;

import com.alibaba.fastjson.JSONObject;
import com.orange.qqbot.domain.constant.Constants;
import com.orange.qqbot.domain.constant.NoticeType;
import com.orange.qqbot.handle.eventhandel.RecallHandle;

/**
 * @author : yilantingfeng
 * @version : v1.0
 * @projectName : QQBot
 * @package : com.orange.qqbot.handle
 * @className : NoticeHandle
 * @description:
 * @date : 2023/1/7 17:23
 */
public class NoticeHandle {
    private static JSONObject postMessage;

    public NoticeHandle init(JSONObject postMessage) {
        NoticeHandle.postMessage = postMessage;
        return this;
    }

    public void run() {
        System.out.println("-----------------------通知消息开始--------------------------");
        String noticeType = postMessage.getString(Constants.NOTICE_TYPE);
        switch (noticeType) {
            case NoticeType.GROUP_UPLOAD -> groupUpload();
            case NoticeType.GROUP_ADMIN -> groupAdmin();
            case NoticeType.GROUP_DECREASE -> groupDecrease();
            case NoticeType.GROUP_INCREASE -> groupIncrease();
            case NoticeType.GROUP_BAN -> groupBan();
            case NoticeType.FRIEND_ADD -> friendAdd();
            case NoticeType.GROUP_RECALL -> groupRecall();
            case NoticeType.FRIEND_RECALL -> friendRecall();
            case NoticeType.GROUP_CARD -> groupCard();
            case NoticeType.OFFLINE_FILE -> offlineFile();
            case NoticeType.CLIENT_STATUS -> clientStatus();
            case NoticeType.ESSENCE -> essence();
            case NoticeType.NOTIFY -> notifyHandle();
            default -> defaultHandle();
        }
        System.out.println("-----------------------通知消息结束--------------------------");
    }


    private void groupUpload() {
        System.out.println("群文件上传");
    }

    private void groupAdmin() {
        System.out.println("群管理员变动");
    }

    private void groupDecrease() {
        System.out.println("群成员减少");
    }

    private void groupIncrease() {
        System.out.println("群成员增加");
    }

    private void friendAdd() {
        System.out.println("好友添加");
    }

    private void groupBan() {
        System.out.println("群禁言");
    }

    private void groupRecall() {
        System.out.println(postMessage);
        String messageId = postMessage.getString(Constants.MESSAGE_ID);
        String userId = postMessage.getString(Constants.USER_ID);
        String groupId = postMessage.getString(Constants.GROUP_ID);
        String operatorId = postMessage.getString(Constants.OPERATOR_ID);
        Long time = postMessage.getLong(Constants.TIME);
        RecallHandle.groupHandle(messageId, groupId, userId, operatorId, time);
    }

    private void friendRecall() {
        Long time = postMessage.getLong(Constants.TIME);
        String messageId = postMessage.getString(Constants.MESSAGE_ID);
        RecallHandle.friendHandle(messageId, time);
        System.out.println("好友消息撤回");
    }

    private void groupCard() {
        System.out.println("群名片变更");
    }

    private void offlineFile() {
        System.out.println("离线文件上传");
    }

    private void clientStatus() {
        System.out.println("客户端状态变更");
    }

    private void essence() {
        System.out.println("精华消息");
    }

    private void defaultHandle() {
        System.out.println("未知通知类型");
    }

    private void notifyHandle() {
        System.out.println("通知");
    }
}
