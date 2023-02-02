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
 * @className : GroupUploadHandle
 * @description:
 * @date : 2023/2/2 20:01
 */
@Component
public class GroupUploadHandler implements NoticeHandler {
    private static JSONObject postMessage;

    @Override
    public NoticeHandler init(JSONObject t) {
        GroupUploadHandler.postMessage = t;
        return this;
    }

    @Override
    public void run() {
        System.out.println("群文件上传");
    }

    @Override
    public void afterPropertiesSet() throws Exception {
        NoticeHandlerFactory.register(NoticeType.GROUP_UPLOAD, this);
    }
}
