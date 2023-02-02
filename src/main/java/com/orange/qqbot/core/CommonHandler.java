package com.orange.qqbot.core;

import com.alibaba.fastjson.JSONObject;
import org.springframework.beans.factory.InitializingBean;

/**
 * @author : yilantingfeng
 * @version : v1.0
 * @projectName : QQBot
 * @package : com.orange.qqbot.core
 * @className : CommonHander
 * @description:
 * @date : 2023/2/2 19:42
 */
public interface CommonHandler extends InitializingBean {
    <T extends CommonHandler> T init(JSONObject t);

    void run();
}
