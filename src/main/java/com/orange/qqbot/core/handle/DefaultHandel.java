package com.orange.qqbot.core.handle;

import com.alibaba.fastjson.JSONObject;
import com.orange.qqbot.config.MessageHandlerFactory;
import com.orange.qqbot.core.Handler;
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
    @Override
    public <T extends Handler> T init(JSONObject t) {
        return null;
    }

    @Override
    public void run() {
        System.out.println("默认处理");
    }

    @Override
    public void afterPropertiesSet() throws Exception {
        MessageHandlerFactory.register(null, this);
    }

}
