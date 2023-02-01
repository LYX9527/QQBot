package com.orange.qqbot.core.annotation;

import org.springframework.context.annotation.Description;

import java.lang.annotation.*;

/**
 * @author : yilantingfeng
 * @version : v1.0
 * @projectName : QQBot
 * @package : com.orange.qqbot.core.annotation
 * @className : PrivateMeaasge
 * @description:
 * @date : 2023/2/1 12:55
 */
@Retention(RetentionPolicy.RUNTIME)
@Description("接收私聊消息")
@Documented
@Inherited
@Target({ElementType.METHOD})
public @interface PrivateMessage {
    String value() default "";
}
