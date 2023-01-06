package com.orange.qqbot.controller;

import com.alibaba.fastjson.JSONObject;
import com.orange.qqbot.api.SendMessage;
import com.orange.qqbot.domain.constant.CQFace;
import com.orange.qqbot.domain.constant.Constants;
import com.orange.qqbot.domain.constant.PostType;
import com.orange.qqbot.domain.handle.GroupMessageHandle;
import com.orange.qqbot.domain.handle.MetaEvenHandle;
import org.springframework.web.bind.annotation.*;

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
public class TakeOverController {
    @PostMapping("/")
    public void takeOver(@RequestBody JSONObject postMessage) {
        String postType = postMessage.getString(Constants.POST_TYPE);
        boolean isMessage = PostType.MESSAGE.equals(postType);
        boolean isNotice = PostType.NOTICE.equals(postType);
        boolean isRequest = PostType.REQUEST.equals(postType);
        boolean isMetaEvent = PostType.META_EVENT.equals(postType);
        if (isMessage) {
            new GroupMessageHandle().init(postMessage).run();
        } else if (isNotice) {
            System.out.println("通知");
            System.out.println(postMessage);
        } else if (isRequest) {
            System.out.println("请求");
            System.out.println(postMessage);
        } else if (isMetaEvent) {
            new MetaEvenHandle().init(postMessage).run();
        }
    }

    @GetMapping("/send/{message}")
    public String test(@PathVariable String message) {
        SendMessage.sendPrivateMessage(CQFace.getCQFace(message), "2632938870", false);
        return "test";
    }
}
