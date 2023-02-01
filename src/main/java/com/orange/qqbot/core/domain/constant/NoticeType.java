package com.orange.qqbot.core.domain.constant;

/**
 * @author : yilantingfeng
 * @version : v1.0
 * @projectName : QQBot
 * @package : com.orange.qqbot.domain.constant
 * @className : NoticeType
 * @description:
 * @date : 2023/1/7 17:28
 */
public class NoticeType {
    /**
     * 群文件上传
     */
    public static final String GROUP_UPLOAD = "group_upload";
    /**
     * 群管理员变更
     */
    public static final String GROUP_ADMIN = "group_admin";
    /**
     * 群成员减少
     */
    public static final String GROUP_DECREASE = "group_decrease";
    /**
     * 群成员增加
     */
    public static final String GROUP_INCREASE = "group_increase";
    /**
     * 好友添加
     */
    public static final String GROUP_BAN = "group_ban";
    /**
     * 好友添加
     */
    public static final String FRIEND_ADD = "friend_add";
    /**
     * 群消息撤回
     */
    public static final String GROUP_RECALL = "group_recall";
    /**
     * 好友消息撤回
     */
    public static final String FRIEND_RECALL = "friend_recall";
    /**
     * 群名片变更
     */
    public static final String GROUP_CARD = "group_card";
    /**
     * 离线文件上传
     */

    public static final String OFFLINE_FILE = "offline_file";
    /**
     * 客户端状态变更
     */
    public static final String CLIENT_STATUS = "client_status";
    /**
     * 精华消息
     */
    public static final String ESSENCE = "essence";
    /**
     * 系统通知
     */
    public static final String NOTIFY = "notify";
}
