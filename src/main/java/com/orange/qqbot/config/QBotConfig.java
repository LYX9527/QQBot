package com.orange.qqbot.config;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

/**
 * @author : yilantingfeng
 * @version : v1.0
 * @projectName : QQBot
 * @package : com.orange.qqbot.config
 * @className : QBotConfig
 * @description:
 * @date : 2023/1/7 17:58
 */
@Component
@ConfigurationProperties(prefix = "qbot")
public class QBotConfig {
    private static String url;

    private static String botqq;

    private static String adminqq;

    public static String getAdminQq() {
        return adminqq;
    }

    public void setAdminqq(String adminqq) {
        QBotConfig.adminqq = adminqq;
    }

    public static String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        QBotConfig.url = url;
    }

    public static String getBotqq() {
        return botqq;
    }

    public void setBotqq(String botqq) {
        QBotConfig.botqq = botqq;
    }
}
