package com.orange.qqbot.config;

import com.xxl.job.core.executor.impl.XxlJobSpringExecutor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @author : yilantingfeng
 * @version : v1.0
 * @projectName : QQBot
 * @package : com.orange.qqbot.config
 * @className : XxlJobConfiguration
 * @description:
 * @date : 2023/2/6 12:36
 */
@Configuration
@Slf4j
public class XxlJobConfiguration {
    @Value("${xxl.job.admin.addresses:''}")
    private String adminAddresses;

    @Value("${xxl.job.executor.appname:''}")
    private String appname;

    @Value("${xxl.job.accessToken:''}")
    private String accessToken;

    @Value("${xxl.job.executor.port:''}")
    private String port;


    @Value("${xxl.job.executor.address:''}")
    private String address;

    @Bean
    public XxlJobSpringExecutor xxlJobExecutor() {
        log.info(">>>>>>>>>>> xxl-job config init==>SUCCESS");
        XxlJobSpringExecutor xxlJobSpringExecutor = new XxlJobSpringExecutor();
        xxlJobSpringExecutor.setAdminAddresses(adminAddresses);
        xxlJobSpringExecutor.setAppname(appname);
        xxlJobSpringExecutor.setAccessToken(accessToken);
        xxlJobSpringExecutor.setPort(Integer.parseInt(port));
        xxlJobSpringExecutor.setIp(address);
        return xxlJobSpringExecutor;
    }
}
