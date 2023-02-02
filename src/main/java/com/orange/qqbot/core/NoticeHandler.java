package com.orange.qqbot.core;

import com.alibaba.fastjson.JSONObject;

/**
 * @author : yilantingfeng
 * @version : v1.0
 * @projectName : QQBot
 * @package : com.orange.qqbot.core
 * @className : NoticeHandler
 * @description:
 * @date : 2023/2/2 17:47
 */
public interface NoticeHandler extends CommonHandler {
    NoticeHandler init(JSONObject t);
    void run();
}
