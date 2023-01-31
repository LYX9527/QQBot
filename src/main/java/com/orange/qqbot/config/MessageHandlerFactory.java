package com.orange.qqbot.config;

import com.orange.qqbot.handle.Handler;
import org.apache.commons.lang3.StringUtils;

import java.util.HashMap;
import java.util.Map;

/**
 * @author : yilantingfeng
 * @version : v1.0
 * @projectName : QQBot
 * @package : com.orange.qqbot.config
 * @className : MessageHandlerFactory
 * @description:
 * @date : 2023/1/31 12:53
 */
public class MessageHandlerFactory {
    private static final Map<String, Handler> handlerMap = new HashMap<>();

    public static Handler getInvokeHandler(String key) {
        return handlerMap.get(key);
    }

    public static void register(String key, Handler handler) {
        if (StringUtils.isEmpty(key) || handler == null) {
            return;
        }
        handlerMap.put(key, handler);
    }
}
