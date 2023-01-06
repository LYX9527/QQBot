package com.orange.qqbot.service;

import com.orange.qqbot.api.SendMessage;
import com.orange.qqbot.domain.constant.CQ;
import com.orange.qqbot.domain.constant.Constants;
import com.orange.qqbot.domain.handle.eventhandel.HistoryTodayHandle;
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
        SendMessage.sendPrivateMessage(CQ.getCQFace("13") + "整点报时,现在是北京时间:" + LocalDateTime.now().format(DateTimeFormatter.ofPattern(Constants.DATE_TIME_FORMATTER)), "2632938870", false);
        SendMessage.sendGroupMessage(CQ.getCQFace("13") + "整点报时,现在是北京时间:" + LocalDateTime.now().format(DateTimeFormatter.ofPattern(Constants.DATE_TIME_FORMATTER)), "1046681020", false);
    }

    @Scheduled(cron = "0 0 8 * * ? ")
    public void autoJob2() {
        HistoryTodayHandle.handle("1046681020");
    }
}
