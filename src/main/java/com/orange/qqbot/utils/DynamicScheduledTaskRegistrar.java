package com.orange.qqbot.utils;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.scheduling.concurrent.ThreadPoolTaskScheduler;
import org.springframework.scheduling.config.CronTask;
import org.springframework.scheduling.config.ScheduledTask;
import org.springframework.scheduling.config.ScheduledTaskRegistrar;

import java.util.LinkedHashMap;
import java.util.Map;

/**
 * @author ：yilantingfeng
 * @version : v1.0
 * @project ：QQbot
 * @package : com.orange.qqbot.utils
 * @name ：DynamicScheduledTaskRegistrar
 * @date ：2023/2/28 13:24
 * @description :
 */
public class DynamicScheduledTaskRegistrar extends ScheduledTaskRegistrar {

    private static final Logger log = LoggerFactory.getLogger(DynamicScheduledTaskRegistrar.class);

    private final Map<String, ScheduledTask> scheduledTaskMap = new LinkedHashMap<>(16);

    public DynamicScheduledTaskRegistrar() {
        super();
        ThreadPoolTaskScheduler taskScheduler = new ThreadPoolTaskScheduler();
        taskScheduler.setPoolSize(8);
        taskScheduler.setRemoveOnCancelPolicy(true);
        taskScheduler.setThreadNamePrefix("dynamic-scheduled-task-");
        taskScheduler.initialize();
        this.setScheduler(taskScheduler);
    }

    /**
     * 新增任务
     *
     * @param taskName 任务名称
     * @param cron     cron表达式
     * @param runnable 任务
     */
    public Boolean addCronTask(String taskName, String cron, Runnable runnable) {
        if (scheduledTaskMap.containsKey(taskName)) {
            log.error("定时任务[" + taskName + "]已存在，添加失败");
            return Boolean.FALSE;
        }
        CronTask cronTask = new CronTask(runnable, cron);
        ScheduledTask scheduledTask = this.scheduleCronTask(cronTask);
        scheduledTaskMap.put(taskName, scheduledTask);
        log.info("定时任务[" + taskName + "]新增成功");
        return Boolean.TRUE;
    }

    /**
     * 删除任务
     *
     * @param taskName 任务名称
     */
    public void cancelCronTask(String taskName) {
        ScheduledTask scheduledTask = scheduledTaskMap.get(taskName);
        if (null != scheduledTask) {
            scheduledTask.cancel();
            scheduledTaskMap.remove(taskName);
        }
        log.info("定时任务[" + taskName + "]删除成功");
    }

    public void cancelAllCronTask() {
        scheduledTaskMap.values().forEach(ScheduledTask::cancel);
        scheduledTaskMap.clear();
        log.info("定时任务全部删除成功");
    }

    @Override
    public void destroy() {
        super.destroy();
        scheduledTaskMap.values().forEach(ScheduledTask::cancel);
    }
}