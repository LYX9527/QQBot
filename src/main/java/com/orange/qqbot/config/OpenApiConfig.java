package com.orange.qqbot.config;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.info.License;
import org.springdoc.core.models.GroupedOpenApi;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class OpenApiConfig {
    @Bean
    public GroupedOpenApi userApi() {
        String[] paths = {"/**"};
        return GroupedOpenApi.builder().group("主服务模块")
                .pathsToMatch(paths).build();
    }

    @Bean
    public OpenAPI customOpenAPI() {
        return new OpenAPI()
                .info(new Info()
                        .title("QQBot API")
                        .version("1.0.0")
                        .description("基于java 与 go-cqhttp的QQ机器人")
                        .termsOfService("https://github.com/yilantingfeng9527/QQBot")
                        .license(new License().name("Apache 2.0")
                                .url("https://github.com/yilantingfeng9527/QQBot")));
    }

}
