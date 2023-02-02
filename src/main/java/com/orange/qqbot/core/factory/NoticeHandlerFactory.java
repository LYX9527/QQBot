package com.orange.qqbot.core.factory;


import com.orange.qqbot.core.NoticeHandler;
import org.apache.commons.lang3.StringUtils;

import java.util.HashMap;
import java.util.Map;

/**
 * @author : yilantingfeng
 * @version : v1.0
 * @projectName : QQBot
 * @package : com.orange.qqbot.config
 * @className : NoticeHandlerFactory
 * @description:
 * @date : 2023/2/2 17:46
 */
public class NoticeHandlerFactory {
    private static final Map<String, NoticeHandler> handlerMap = new HashMap<>();

    public static NoticeHandler getInvokeHandler(String key) {
        return handlerMap.get(key);
    }

    public static void register(String key, NoticeHandler noticeHandler) {
        if (StringUtils.isEmpty(key) || noticeHandler == null) {
            return;
        }
        handlerMap.put(key, noticeHandler);
    }
}
