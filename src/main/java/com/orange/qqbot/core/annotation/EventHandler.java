package com.orange.qqbot.core.annotation;

import org.springframework.context.annotation.Description;
import org.springframework.stereotype.Component;

import java.lang.annotation.*;

/**
 * @author : yilantingfeng
 * @version : v1.0
 * @projectName : QQBot
 * @package : com.orange.qqbot.core.annotation
 * @className : Handler
 * @description:
 * @date : 2023/2/1 18:39
 */
@Retention(RetentionPolicy.RUNTIME)
@Description("处理器")
@Target({ElementType.TYPE})
@Component
public @interface EventHandler {
}
