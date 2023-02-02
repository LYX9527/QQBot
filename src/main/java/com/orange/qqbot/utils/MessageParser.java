package com.orange.qqbot.utils;

import com.alibaba.fastjson.JSONObject;
import com.orange.qqbot.config.QBotConfig;
import com.orange.qqbot.core.domain.constant.Constants;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Optional;

/**
 * @author : yilantingfeng
 * @version : v1.0
 * @projectName : QQBot
 * @package : com.orange.qqbot.utils
 * @className : MessageParser
 * @description:
 * @date : 2023/2/1 13:00
 */
public class MessageParser {
    private static final Logger logger = LoggerFactory.getLogger(MessageParser.class);
    private static JSONObject postMessage;

    public MessageParser(JSONObject postMessage) {
        MessageParser.postMessage = postMessage;
    }

    /**
     * 获取@的qq号
     *
     * @return qq号
     */
    public String getAtQq() {
        return Optional.ofNullable(getRawMessage())
                .map(message -> message.replaceAll(".*\\[CQ:at,qq=(\\d+)\\].*", "$1"))
                .orElseGet(() -> {
                    logger.error("getAtQq error");
                    return "";
                });
    }

    /**
     * 获取发消息的昵称
     *
     * @return 昵称
     */
    public String getSenderNickname() {
        JSONObject sender = getSender();
        return Optional.ofNullable(sender)
                .map(s -> s.getString(Constants.NICKNAME))
                .orElseGet(() -> {
                    logger.error("getSenderNickname error");
                    return "";
                });
    }

    /**
     * 获取发消息的qq号
     *
     * @return qq号
     */
    public String getSenderQq() {
        JSONObject sender = getSender();
        return Optional.ofNullable(sender)
                .map(s -> s.getString(Constants.USER_ID))
                .orElseGet(() -> {
                    logger.error("getSenderQq error");
                    return "";
                });
    }

    /**
     * 获取发消息的群名片
     *
     * @return 群名片
     */
    public String getSenderCard() {
        JSONObject sender = getSender();
        return Optional.ofNullable(sender)
                .map(s -> s.getString(Constants.CARD))
                .orElseGet(() -> {
                    logger.error("getSenderCard error");
                    return "";
                });
    }

    /**
     * 获取@机器人的消息
     *
     * @return 消息
     */
    public String getAtMessage() {
        return getAtMessage(QBotConfig.getBotqq());
    }

    /**
     * 获取@某qq的消息
     *
     * @return 消息
     */
    public String getAtMessage(String qq) {
        return Optional.ofNullable(getRawMessage())
                .map(message -> message.replaceAll("\\[CQ:at,qq=" + qq + "\\]", ""))
                .orElseGet(() -> {
                    logger.error("getAtMessage error");
                    return "";
                });
    }

    /**
     * 获取发消息的群号
     *
     * @return 群号
     */
    public String getGroupId() {
        String groupId = postMessage.getString(Constants.GROUP_ID);
        return Optional.ofNullable(groupId)
                .orElseGet(() -> {
                    logger.error("getGroupQq error");
                    return "";
                });
    }

    /**
     * 判断所@的qq是否是机器人
     *
     * @return true or false
     */
    public boolean isAtBot() {
        return QBotConfig.getBotqq().equals(getAtQq());
    }

    /**
     * 获取发送的信息
     *
     * @return 信息
     */
    public String getRawMessage() {
        return postMessage.getString(Constants.RAW_MESSAGE);
    }

    /**
     * 获取发消息qq的信息
     *
     * @return info
     */
    private JSONObject getSender() {
        JSONObject sender = postMessage.getJSONObject(Constants.SENDER);
        return Optional.ofNullable(sender)
                .orElseGet(() -> {
                    logger.error("getSender error");
                    return new JSONObject();
                });
    }

    /**
     * 获取消息id
     *
     * @return 消息id
     */
    public String getMessageId() {
        String messageId = postMessage.getString(Constants.MESSAGE_ID);
        return Optional.ofNullable(messageId)
                .orElseGet(() -> {
                    logger.error("getMessageId error");
                    return "";
                });
    }

    /**
     * 获取操作撤回消息的qq号
     *
     * @return qq号
     */
    public String getOperatorId() {
        String operatorId = postMessage.getString(Constants.OPERATOR_ID);
        return Optional.ofNullable(operatorId)
                .orElseGet(() -> {
                    logger.error("getOperatorId error");
                    return "";
                });
    }

    /**
     * 获取消息归属qq号
     *
     * @return qq号
     */
    public String getUserId() {
        String userId = postMessage.getString(Constants.USER_ID);
        return Optional.ofNullable(userId)
                .orElseGet(() -> {
                    logger.error("getUserId error");
                    return "";
                });
    }

    /**
     * 获取消息发生时间
     *
     * @return 时间
     */
    public Long getTime() {
        Long time = postMessage.getLong(Constants.TIME);
        return Optional.ofNullable(time)
                .orElseGet(() -> {
                    logger.error("getTime error");
                    return 0L;
                });
    }
}
