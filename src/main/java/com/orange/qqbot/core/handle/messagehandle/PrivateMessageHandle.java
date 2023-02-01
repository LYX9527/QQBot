package com.orange.qqbot.core.handle.messagehandle;

import com.alibaba.fastjson.JSONObject;
import com.orange.qqbot.config.MessageHandlerFactory;
import com.orange.qqbot.core.Handler;
import com.orange.qqbot.core.annotation.PrivateMessage;
import com.orange.qqbot.core.domain.constant.MessageType;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.lang.reflect.Parameter;
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
public class PrivateMessageHandle implements Handler {
    @Autowired
    private ApplicationContext applicationContext;
    private static JSONObject postMessage;

    public PrivateMessageHandle init(JSONObject postMessage) {
        PrivateMessageHandle.postMessage = postMessage;
        return this;
    }

    public void run() {
        System.out.println("-----------------------私聊消息开始--------------------------");
        System.out.println(postMessage);
        System.out.println("-----------------------私聊消息结束--------------------------");
        registerCustomizeHandler();
    }

    @Override
    public void afterPropertiesSet() throws Exception {
        MessageHandlerFactory.register(MessageType.PRIVATE, this);
    }

    private void registerCustomizeHandler() {
        Map<String, Object> beansWithAnnotation = applicationContext.getBeansWithAnnotation(Service.class);
        beansWithAnnotation.forEach((k, v) -> {
            Class<?> aClass = v.getClass();
            Method[] methods = aClass.getMethods();
            for (Method method : methods) {
                if (method.isAnnotationPresent(PrivateMessage.class)) {
                    Parameter[] parameters = method.getParameters();
                    try {
                        method.invoke(v, postMessage);
                    } catch (IllegalAccessException | InvocationTargetException e) {
                        throw new RuntimeException(e);
                    }
                }
            }
        });

    }
}
