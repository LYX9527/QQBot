package com.orange.qqbot.core.handle.messagehandle.keyword;

import cn.hutool.core.date.ChineseDate;
import com.alibaba.fastjson.JSONObject;
import com.orange.qqbot.api.SendMessage;
import com.orange.qqbot.config.KeyWordHandlerFactory;
import com.orange.qqbot.core.CommonHandler;
import com.orange.qqbot.core.domain.constant.Constants;
import com.orange.qqbot.core.domain.constant.KeyWord;
import com.orange.qqbot.core.domain.constant.MessageType;
import com.orange.qqbot.utils.MessageParser;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

/**
 * @author : yilantingfeng
 * @version : v1.0
 * @projectName : QQBot
 * @package : com.orange.qqbot.domain.handle.eventhandel
 * @className : NowTimeHandle
 * @description:
 * @date : 2023/1/6 20:19
 * @description : 当前时间处理内置类
 */
@Component
public class NowTimeHandle implements CommonHandler {
    private static final Logger logger = LoggerFactory.getLogger(NowTimeHandle.class);
    private static JSONObject postMessage;

    @Override
    public NowTimeHandle init(JSONObject t) {
        NowTimeHandle.postMessage = t;
        return this;
    }

    @Override
    public void handlePrivate() {
        MessageParser messageParser = new MessageParser(postMessage);
        String senderQq = messageParser.getSenderQq();
        SendMessage.sendPrivateMessage(getContent(), senderQq, false);
        logger.info("发送当前时间成功,在私聊《" + senderQq + "》");
    }

    @Override
    public void handleGroup() {
        String groupId = postMessage.getString(Constants.GROUP_ID);
        SendMessage.sendGroupMessage(getContent(), groupId, false);
        logger.info("发送当前时间成功成功,在群《" + groupId + "》");
    }

    @Override
    public void afterPropertiesSet() throws Exception {
        KeyWordHandlerFactory.register(KeyWord.TIME, this);
        KeyWordHandlerFactory.register(KeyWord.TIME, MessageType.PRIVATE, this);
    }

    @Override
    public String getContent() {
        ChineseDate chineseDate = new ChineseDate(LocalDate.now());
        StringBuilder sb = new StringBuilder();
        String s = LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyy年MM月dd日 HH:mm:ss"));
        sb.append("现在是：\n");
        sb.append(s);
        sb.append(" 星期");
        int value = LocalDateTime.now().getDayOfWeek().getValue();
        switch (value) {
            case 1 -> sb.append("一");
            case 2 -> sb.append("二");
            case 3 -> sb.append("三");
            case 4 -> sb.append("四");
            case 5 -> sb.append("五");
            case 6 -> sb.append("六");
            case 7 -> sb.append("日");
        }
        sb.append("\n");
        sb.append("农历:");
        String ch = chineseDate.toString();
        sb.append(ch);
        return sb.toString();
    }
}
