package com.orange.qqbot.config;

import com.orange.qqbot.core.KWHandler;
import com.orange.qqbot.core.domain.constant.MessageType;
import org.apache.commons.lang3.StringUtils;

import java.util.HashMap;
import java.util.Map;

/**
 * @author : yilantingfeng
 * @version : v1.0
 * @projectName : QQBot
 * @package : com.orange.qqbot.config
 * @className : KeyWordHandlerFactory
 * @description:
 * @date : 2023/2/1 18:56
 */
public class KeyWordHandlerFactory {
    private static final Map<String, KWHandler> handlerMap = new HashMap<>();

    public static KWHandler getInvokeHandler(String key, String type) {
        return handlerMap.get(key + type);
    }

    public static void register(String key, String type, KWHandler handler) {
        if (StringUtils.isEmpty(key) || handler == null) {
            return;
        }
        handlerMap.put(key + type, handler);
    }

    public static void register(String key, KWHandler handler) {
        if (StringUtils.isEmpty(key) || handler == null) {
            return;
        }
        handlerMap.put(key + MessageType.GROUP, handler);
    }
}
