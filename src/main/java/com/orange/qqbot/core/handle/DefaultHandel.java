package com.orange.qqbot.core.handle;

import com.alibaba.fastjson.JSONObject;
import com.orange.qqbot.config.MessageHandlerFactory;
import com.orange.qqbot.core.Handler;
import com.orange.qqbot.core.domain.constant.Constants;
import org.springframework.stereotype.Component;

/**
 * @author : yilantingfeng
 * @version : v1.0
 * @projectName : QQBot
 * @package : com.orange.qqbot.handle
 * @className : DefaultHandel
 * @description:
 * @date : 2023/1/31 13:15
 */
@Component
public class DefaultHandel implements Handler {
    private static JSONObject postMessage;

    @Override
    public DefaultHandel init(JSONObject t) {
        DefaultHandel.postMessage = t;
        return this;
    }

    @Override
    public void run() {
    }

    @Override
    public void afterPropertiesSet() throws Exception {
        MessageHandlerFactory.register(Constants.DEFAULT, this);
    }

}
