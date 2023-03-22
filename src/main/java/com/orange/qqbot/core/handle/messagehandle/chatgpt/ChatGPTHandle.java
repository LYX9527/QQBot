package com.orange.qqbot.core.handle.messagehandle.chatgpt;

import com.alibaba.fastjson.JSONObject;
import com.orange.qqbot.api.GPTApi;
import com.orange.qqbot.api.SendMessage;
import com.orange.qqbot.config.QBotConfig;
import com.orange.qqbot.core.annotation.GroupMessage;
import com.orange.qqbot.core.annotation.MessageEventHandler;
import com.orange.qqbot.core.domain.constant.KeyWord;
import com.orange.qqbot.utils.MessageParser;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import java.lang.reflect.Field;
import java.util.concurrent.*;

/**
 * @author ：yilantingfeng
 * @version : v1.0
 * @project ：QQBot
 * @package : com.orange.qqbot.core.handle.messagehandle.chatgpt
 * @name ：ChatGPTHandle
 * @date ：2023/3/20 14:33
 * @description :
 */
@MessageEventHandler
public class ChatGPTHandle {
    private static final Logger log = LoggerFactory.getLogger(ChatGPTHandle.class);
    private final ExecutorService pool = Executors.newFixedThreadPool(15);
    @Autowired
    GPTApi GPTApi;

    @GroupMessage
    public void handle(JSONObject postMessage) throws IllegalAccessException {
        log.info("postMessageSelfId: {}", postMessage.getString("self_id"));
        MessageParser messageParser = new MessageParser(postMessage);
        String atMessage = messageParser.getAtMessage().trim();
        if (messageParser.getAtQq().trim().equals(QBotConfig.getBotqq())) {
            Class<KeyWord> keyWordClass = KeyWord.class;
            Field[] declaredFields = keyWordClass.getDeclaredFields();
            boolean flag = true;
            for (Field declaredField : declaredFields) {
                String keyword = (String) declaredField.get(null);
                if (keyword.equals(atMessage)) {
                    flag = false;
                    break;
                }
            }
            if (flag) {
                String groupId = messageParser.getGroupId();
                Future<?> data = pool.submit(() -> GPTApi.getAnswer(atMessage, messageParser.getSenderQq(), groupId));
                JSONObject answer;
                try {
                    answer = (JSONObject) data.get(30, TimeUnit.SECONDS);
                    log.info("answer: {}", answer);
                } catch (InterruptedException e) {
                    log.error("InterruptedException:error: {}", e.getMessage());
                    throw new RuntimeException(e);
                } catch (ExecutionException e) {
                    log.error("ExecutionException:error: {}", e.getMessage());
                    throw new RuntimeException(e);
                } catch (TimeoutException e) {
                    log.error("TimeoutException:error: {}", e.getMessage());
                    throw new RuntimeException(e);
                }
                SendMessage.sendGroupMessage(answer.getString("data"), groupId, true);
            }
        }
    }
}
