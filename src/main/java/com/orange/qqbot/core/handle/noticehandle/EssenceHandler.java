package com.orange.qqbot.core.handle.noticehandle;

import com.alibaba.fastjson.JSONObject;
import com.orange.qqbot.core.NoticeHandler;
import com.orange.qqbot.core.domain.constant.NoticeType;
import com.orange.qqbot.core.factory.NoticeHandlerFactory;
import org.springframework.stereotype.Component;

/**
 * @author : yilantingfeng
 * @version : v1.0
 * @projectName : QQBot
 * @package : com.orange.qqbot.core.handle.noticehandle
 * @className : EssenceHandler
 * @description:
 * @date : 2023/2/2 20:18
 */
@Component
public class EssenceHandler implements NoticeHandler {
    private static JSONObject postMessage;

    @Override
    public NoticeHandler init(JSONObject t) {
        EssenceHandler.postMessage = t;
        return this;
    }

    @Override
    public void run() {
        System.out.println("精华消息");
    }

    @Override
    public void afterPropertiesSet() throws Exception {
        NoticeHandlerFactory.register(NoticeType.ESSENCE, this);
    }

}
