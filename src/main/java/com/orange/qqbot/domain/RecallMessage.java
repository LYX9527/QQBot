package com.orange.qqbot.domain;

import lombok.Data;

/**
 * @author : yilantingfeng
 * @version : v1.0
 * @projectName : QQBot
 * @package : com.orange.qqbot.domain
 * @className : RecallMessage
 * @description:
 * @date : 2023/1/7 18:29
 */
@Data
public class RecallMessage {
    /**
     * 撤回消息内容
     */
    private String recallMessage;
    /**
     * 撤回时间
     */
    private String recallTime;
    /**
     * 消息发送者
     */
    private String senderNickName;
    /**
     * 消息发送者QQ
     */
    private String senderUserId;
}
