package com.orange.qqbot.core;

import com.alibaba.fastjson.JSONObject;
import org.springframework.beans.factory.InitializingBean;

public interface KWHandler extends InitializingBean {
    <T extends KWHandler> T init(JSONObject t);

    void run(String messageType);

    default void handlePrivate() {
    }

    default void handleGroup() {
    }
}
