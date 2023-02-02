package com.orange.qqbot.core.handle.messagehandle;

import com.alibaba.fastjson.JSONObject;
import com.orange.qqbot.core.factory.MessageHandlerFactory;
import com.orange.qqbot.core.MessageHandler;
import com.orange.qqbot.core.annotation.MessageEventHandler;
import com.orange.qqbot.core.annotation.PrivateMessage;
import com.orange.qqbot.core.domain.constant.MessageType;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Component;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.Map;

/**
 * @author : yilantingfeng
 * @version : v1.0
 * @projectName : QQBot
 * @package : com.orange.qqbot.handle
 * @className : PrivateMessageHandle
 * @description:
 * @date : 2023/1/7 17:17
 */
@Component
public class PrivateMessageHandle implements MessageHandler {
    @Autowired
    private ApplicationContext applicationContext;
    private static JSONObject postMessage;

    public PrivateMessageHandle init(JSONObject postMessage) {
        PrivateMessageHandle.postMessage = postMessage;
        return this;
    }

    public void run() {
        Map<String, Object> beansWithAnnotation = applicationContext.getBeansWithAnnotation(MessageEventHandler.class);
        beansWithAnnotation.forEach((k, v) -> {
            Class<?> aClass = v.getClass();
            Method[] methods = aClass.getMethods();
            for (Method method : methods) {
                if (method.isAnnotationPresent(PrivateMessage.class)) {
                    try {
                        method.invoke(v, postMessage);
                    } catch (IllegalAccessException | InvocationTargetException e) {
                        throw new RuntimeException(e);
                    }
                }
            }
        });
    }

    @Override
    public void afterPropertiesSet() {
        MessageHandlerFactory.register(MessageType.PRIVATE, this);
    }
}
