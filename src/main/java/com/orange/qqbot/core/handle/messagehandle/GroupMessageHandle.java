package com.orange.qqbot.core.handle.messagehandle;

import com.alibaba.fastjson.JSONObject;
import com.orange.qqbot.core.factory.MessageHandlerFactory;
import com.orange.qqbot.core.MessageHandler;
import com.orange.qqbot.core.annotation.MessageEventHandler;
import com.orange.qqbot.core.annotation.GroupMessage;
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
 * @package : com.orange.qqbot.domain.handle
 * @className : GroupMessagehandle
 * @description:
 * @date : 2023/1/6 15:05
 */
@Component
public class GroupMessageHandle implements MessageHandler {
    @Autowired
    private ApplicationContext applicationContext;
    private static JSONObject postMessage;

    public GroupMessageHandle init(JSONObject postMessage) {
        GroupMessageHandle.postMessage = postMessage;
        return this;
    }

    public void run() {
        Map<String, Object> beansWithAnnotation = applicationContext.getBeansWithAnnotation(MessageEventHandler.class);
        beansWithAnnotation.forEach((k, v) -> {
            Class<?> aClass = v.getClass();
            Method[] methods = aClass.getMethods();
            for (Method method : methods) {
                if (method.isAnnotationPresent(GroupMessage.class)) {
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
        MessageHandlerFactory.register(MessageType.GROUP, this);
    }
}
