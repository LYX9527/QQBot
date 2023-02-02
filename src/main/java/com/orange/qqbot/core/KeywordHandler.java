package com.orange.qqbot.core;

import com.alibaba.fastjson.JSONObject;
import com.orange.qqbot.core.domain.constant.MessageType;
import org.springframework.beans.factory.InitializingBean;

public interface KeywordHandler extends InitializingBean {

    <T extends KeywordHandler> T init(JSONObject t);

    default void handlePrivate() {
    }


    default void handleGroup() {
    }


    default void run(String messageType) {
        if (MessageType.GROUP.equals(messageType)) {
            handleGroup();
        } else if (MessageType.PRIVATE.equals(messageType)) {
            handlePrivate();
        }
    }

    default String getContent() {
        return "";
    }
}
