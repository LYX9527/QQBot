package com.orange.qqbot.service.redisMQ;

import com.alibaba.fastjson.JSONObject;

/**
 * @author ：yilantingfeng
 * @version : v1.0
 * @project ：PermissionToVerify
 * @package : com.orange.framework.service.redisMQ
 * @name ：RedisMQService
 * @date ：2023/3/22 20:50
 * @description :
 */
public interface RedisMQService {
    void produce(JSONObject jsonObject);

    void consume();

    void blockingConsume();
}
