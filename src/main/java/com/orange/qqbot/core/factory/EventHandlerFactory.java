package com.orange.qqbot.core.factory;

import com.orange.qqbot.core.CommonHandler;
import org.apache.commons.lang3.StringUtils;

import java.util.HashMap;
import java.util.Map;

/**
 * @author : yilantingfeng
 * @version : v1.0
 * @projectName : QQBot
 * @package : com.orange.qqbot.config
 * @className : EventHandlerFactory
 * @description:
 * @date : 2023/2/2 19:37
 */
public class EventHandlerFactory {
    private static final Map<String, CommonHandler> handlerMap = new HashMap<>();

    public static CommonHandler getInvokeHandler(String key) {
        return handlerMap.get(key);
    }

    public static void register(String key, CommonHandler eventHandler) {
        if (StringUtils.isEmpty(key) || eventHandler == null) {
            return;
        }
        handlerMap.put(key, eventHandler);
    }
}
