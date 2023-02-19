package com.orange.qqbot.service;

import com.orange.qqbot.api.SendMessage;
import com.orange.qqbot.core.domain.constant.CQ;
import com.xxl.job.core.context.XxlJobHelper;
import com.xxl.job.core.executor.XxlJobExecutor;
import com.xxl.job.core.handler.IJobHandler;
import jakarta.annotation.PostConstruct;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

/**
 * @author : yilantingfeng
 * @version : v1.0
 * @projectName : QQBot
 * @package : com.orange.qqbot.service
 * @className : AutoJob
 * @description:
 * @date : 2023/1/6 18:27
 */
@Component
@Slf4j
public class AutomaticallyHandler extends IJobHandler {

    @Override
    public void execute() {
        XxlJobHelper.log(">>>>>> log start @{}", System.currentTimeMillis());
        String jobParam = XxlJobHelper.getJobParam();
        String[] userArr = jobParam.split(",");
        try {
            if (userArr.length >= 1) {
                for (String user : userArr) {
                    String[] split1 = user.split("-");
                    wakeUpAutomatically(split1[0], split1[1]);
                }
            }
            XxlJobHelper.handleSuccess("发送完毕");
        } catch (Exception e) {
            e.printStackTrace();
            XxlJobHelper.handleFail(e.getMessage());
        }
        XxlJobHelper.log(">>>>>> log end @{}", System.currentTimeMillis());
    }

    @PostConstruct
    public void init() {
        XxlJobExecutor.registJobHandler(AutomaticallyHandler.class.getSimpleName(), this);
    }

    private void wakeUpAutomatically(String groupId, String qq) {
        SendMessage.sendGroupMessage(CQ.getCQPoke(qq), groupId, false);
        SendMessage.sendGroupMessage(CQ.getCQAt(qq) + "起床啦！", groupId, false);
    }
}
