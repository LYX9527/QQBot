package com.orange.qqbot.core.domain.constant;

/**
 * @author : yilantingfeng
 * @version : v1.0
 * @projectName : QQBot
 * @package : com.orange.qqbot.domain.constant
 * @className : PostType
 * @description:
 * @date : 2023/1/6 14:57
 */
public class PostType {
    /**
     * 消息, 例如, 群聊消息
     */
    public static final String MESSAGE = "message";
    /**
     * 通知, 例如, 群成员增加
     */
    public static final String NOTICE = "notice";
    /**
     * 请求, 例如, 好友申请
     */
    public static final String REQUEST = "request";
    /**
     * 元事件, 例如, 连接测试 心跳包
     */
    public static final String META_EVENT = "meta_event";

}
