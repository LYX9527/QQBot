package com.orange.qqbot.core;

import com.alibaba.fastjson.JSONObject;

/**
 * @author : yilantingfeng
 * @version : v1.0
 * @projectName : QQBot
 * @package : com.orange.qqbot.handle
 * @className : Handler
 * @description:
 * @date : 2023/1/31 12:49
 */
public interface MessageHandler extends CommonHandler {

    MessageHandler init(JSONObject t);

    void run();
}
