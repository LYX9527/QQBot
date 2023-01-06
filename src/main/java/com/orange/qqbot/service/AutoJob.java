package com.orange.qqbot.service;

import com.orange.qqbot.api.SendMessage;
import com.orange.qqbot.domain.constant.CQFace;
import com.orange.qqbot.domain.constant.Constants;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

/**
 * @author : yilantingfeng
 * @version : v1.0
 * @projectName : QQBot
 * @package : com.orange.qqbot.service
 * @className : AutoJob
 * @description:
 * @date : 2023/1/6 18:27
 */
@Service
public class AutoJob {
    @Scheduled(cron = "0 0 0/1 * * ? ")
    public void autoJob() {
        SendMessage.sendPrivateMessage(CQFace.getCQFace("13") + "整点报时,现在是北京时间:" + LocalDateTime.now().format(DateTimeFormatter.ofPattern(Constants.DATE_TIME_FORMATTER)), "2632938870", false);
        SendMessage.sendGroupMessage(CQFace.getCQFace("13") + "整点报时,现在是北京时间:" + LocalDateTime.now().format(DateTimeFormatter.ofPattern(Constants.DATE_TIME_FORMATTER)), "1046681020", false);
    }
}
