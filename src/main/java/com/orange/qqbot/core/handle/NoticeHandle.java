package com.orange.qqbot.core.handle;

import com.alibaba.fastjson.JSONObject;
import com.orange.qqbot.core.NoticeHandler;
import com.orange.qqbot.core.domain.constant.Constants;
import com.orange.qqbot.core.domain.constant.NoticeType;
import com.orange.qqbot.core.domain.constant.PostType;
import com.orange.qqbot.core.factory.EventHandlerFactory;
import com.orange.qqbot.core.factory.NoticeHandlerFactory;
import org.springframework.stereotype.Component;

/**
 * @author : yilantingfeng
 * @version : v1.0
 * @projectName : QQBot
 * @package : com.orange.qqbot.handle
 * @className : NoticeHandle
 * @description:
 * @date : 2023/1/7 17:23
 */
@Component
public class NoticeHandle implements NoticeHandler {
    private static JSONObject postMessage;

    @Override
    public NoticeHandle init(JSONObject postMessage) {
        NoticeHandle.postMessage = postMessage;
        return this;
    }

    public void run() {
        String noticeType = postMessage.getString(Constants.NOTICE_TYPE);
        NoticeHandler invokeHandler = NoticeHandlerFactory.getInvokeHandler(noticeType);
        try {
            invokeHandler.init(postMessage).run();
        } catch (Exception e) {
            EventHandlerFactory.getInvokeHandler(Constants.DEFAULT).init(postMessage).run();
        }
        System.out.println("-----------------------通知消息开始--------------------------");
        switch (noticeType) {
            case NoticeType.NOTIFY -> notifyHandle();
            default -> defaultHandle(noticeType);
        }
        System.out.println("-----------------------通知消息结束--------------------------");
    }

    private void defaultHandle(String noticeType) {
        System.out.println(noticeType);
    }

    private void notifyHandle() {
        System.out.println(postMessage);
        System.out.println("通知");
    }

    @Override
    public void afterPropertiesSet() {
        EventHandlerFactory.register(PostType.NOTICE, this);
    }
}
