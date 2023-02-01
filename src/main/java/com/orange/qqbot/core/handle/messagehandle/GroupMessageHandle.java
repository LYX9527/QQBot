package com.orange.qqbot.core.handle.messagehandle;

import com.alibaba.fastjson.JSONObject;
import com.orange.qqbot.config.MessageHandlerFactory;
import com.orange.qqbot.core.Handler;
import com.orange.qqbot.core.annotation.GroupMessage;
import com.orange.qqbot.core.domain.constant.MessageType;
import com.orange.qqbot.core.handle.eventhandel.KeyWordHandle;
import com.orange.qqbot.utils.MessageParser;
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
 * @package : com.orange.qqbot.domain.handle
 * @className : GroupMessagehandle
 * @description:
 * @date : 2023/1/6 15:05
 */
@Component
public class GroupMessageHandle implements Handler {
    @Autowired
    private ApplicationContext applicationContext;
    private static JSONObject postMessage;

    public GroupMessageHandle init(JSONObject postMessage) {
        GroupMessageHandle.postMessage = postMessage;
        return this;
    }

    public void run() {
        System.out.println("-----------------------群消息开始--------------------------");
        MessageParser messageParser = new MessageParser(postMessage);
        String nickname = messageParser.getSenderNickname();
        String card = messageParser.getSenderCard();
        String rawMessage = messageParser.getRawMessage();
        String groupId = messageParser.getGroupId();
        if (messageParser.isAtBot()) {
            String message = messageParser.getAtMessage();
            KeyWordHandle.handle(message, groupId);
        }
        System.out.println(card + "(" + nickname + "):" + rawMessage);
        System.out.println("-----------------------消息结束--------------------------");
        registerCustomizeHandler();
    }

    @Override
    public void afterPropertiesSet() {
        MessageHandlerFactory.register(MessageType.GROUP, this);
    }

    private void registerCustomizeHandler() {
        Map<String, Object> beansWithAnnotation = applicationContext.getBeansWithAnnotation(Service.class);
        beansWithAnnotation.forEach((k, v) -> {
            Class<?> aClass = v.getClass();
            Method[] methods = aClass.getMethods();
            for (Method method : methods) {
                if (method.isAnnotationPresent(GroupMessage.class)) {
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
