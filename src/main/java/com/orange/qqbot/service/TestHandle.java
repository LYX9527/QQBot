package com.orange.qqbot.service;

import com.alibaba.fastjson.JSONObject;
import com.orange.qqbot.core.CustomizeMessageHandle;
import com.orange.qqbot.core.annotation.GroupMessage;
import org.springframework.stereotype.Service;

/**
 * @author : yilantingfeng
 * @version : v1.0
 * @projectName : QQBot
 * @package : com.orange.qqbot.service
 * @className : TestAnnotation
 * @description:
 * @date : 2023/2/1 11:06
 */
@Service
public class TestHandle implements CustomizeMessageHandle {
    @Override
    @GroupMessage
    public void handle(JSONObject postMessage) {
        System.out.println(postMessage);
        System.out.println("我是继承了的test");
    }
}
