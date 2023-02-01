package com.orange.qqbot.core.annotation;

import org.springframework.context.annotation.Description;

import java.lang.annotation.*;

@Retention(RetentionPolicy.RUNTIME)
@Description("接收群消息")
@Documented
@Inherited
@Target({ElementType.METHOD})
public @interface GroupMessage {
    String value() default "";
}
