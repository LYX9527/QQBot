package com.orange.qqbot.entrance;

import com.alibaba.fastjson.JSONObject;
import com.orange.qqbot.service.redisMQ.RedisMQService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author : yilantingfeng
 * @version : v1.0
 * @projectName : QQBot
 * @package : com.orange.qqbot.controller
 * @className : TakeOver
 * @description:
 * @date : 2023/1/6 13:00
 */
@RestController
@Slf4j
@Tag(name = "接管入口", description = "接管入口")
public class TakeOverEntrance {
    @Autowired
    private RedisMQService redisMQService;

    @PostMapping("/")
    @Operation(summary = "接管入口")
    public void takeOver(@RequestBody JSONObject postMessage) {
        redisMQService.produce(postMessage);
    }
}
