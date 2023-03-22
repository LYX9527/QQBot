package com.orange.qqbot.service.redisMQ;

import cn.hutool.core.lang.UUID;
import com.alibaba.fastjson.JSONObject;
import com.orange.qqbot.core.CommonHandler;
import com.orange.qqbot.core.domain.constant.Constants;
import com.orange.qqbot.core.factory.EventHandlerFactory;
import jakarta.annotation.PostConstruct;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * @author ：yilantingfeng
 * @version : v1.0
 * @project ：PermissionToVerify
 * @package : com.orange.framework.service.redisMQ
 * @name ：RedisMQServiceImpl
 * @date ：2023/3/22 20:50
 * @description :
 */
@Service
public class RedisMQServiceImpl implements RedisMQService {
    private static final Logger log = LoggerFactory.getLogger(RedisMQServiceImpl.class);

    private static final String MESSAGE_KEY = "message:queue";

    @Autowired
    private RedisTemplate redisTemplate;

    @Override
    public void produce(JSONObject postMessage) {
        if (postMessage == null) {
            return;
        }
        if (postMessage.containsKey(Constants.POST_TYPE)) {
            postMessage.put("self_id", UUID.randomUUID().toString());
            redisTemplate.opsForList().leftPush(MESSAGE_KEY, postMessage);
        }
    }

    @Override
    public void consume() {
        JSONObject string = (JSONObject) redisTemplate.opsForList().rightPop(MESSAGE_KEY);
        log.info("consume : {}", string);
    }

    @PostConstruct
    @Override
    public void blockingConsume() {
        ExecutorService pool = Executors.newFixedThreadPool(1);
        pool.submit(() -> {
            while (true) {
                try {
                    JSONObject postMessage = (JSONObject) redisTemplate.opsForList().rightPop(MESSAGE_KEY);
                    if (postMessage != null) {
                        log.info("blockingConsume : {}", postMessage);
                        handleMessage(postMessage);
                    }
                } catch (Exception e) {
                    log.error("blockingConsume error : {}", e.getMessage());
                }
            }
        });
    }

    private void handleMessage(JSONObject postMessage) {
        String postType = postMessage.getString(Constants.POST_TYPE);
        CommonHandler invokeHandler = EventHandlerFactory.getInvokeHandler(postType);
        try {
            invokeHandler.init(postMessage).run();
        } catch (Exception e) {
            log.error("接受消息格式错误", e);
            log.error("消息内容：{}", postMessage.toJSONString());
            Integer retryNum = postMessage.getInteger("retryNum");
            if (retryNum == null) {
                retryNum = 1;
            }
            log.info("尝试重试中，重试次数：{}", retryNum);
            if (retryNum < 3) {
                postMessage.put("retryNum", retryNum + 1);
                redisTemplate.opsForList().rightPush(MESSAGE_KEY, postMessage);
            } else {
                log.error("重试次数超过3次，放弃重试");
                log.error("消息已被放弃处理，并销毁");
                log.error("消息内容：{}", postMessage.toJSONString());
            }
        }
    }
}
