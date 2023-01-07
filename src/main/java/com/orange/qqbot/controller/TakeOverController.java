package com.orange.qqbot.controller;

import com.alibaba.fastjson.JSONObject;
import com.orange.qqbot.api.SendMessage;
import com.orange.qqbot.domain.constant.CQ;
import com.orange.qqbot.domain.constant.Constants;
import com.orange.qqbot.domain.constant.MessageType;
import com.orange.qqbot.domain.constant.PostType;
import com.orange.qqbot.handle.GroupMessageHandle;
import com.orange.qqbot.handle.MetaEvenHandle;
import com.orange.qqbot.handle.NoticeHandle;
import com.orange.qqbot.handle.PrivateMessageHandle;
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
public class TakeOverController {
    @PostMapping("/")
    public void takeOver(@RequestBody JSONObject postMessage) {
        String postType = postMessage.getString(Constants.POST_TYPE);
        switch (postType) {
            case PostType.META_EVENT -> new MetaEvenHandle().init(postMessage).run();
            case PostType.NOTICE -> new NoticeHandle().init(postMessage).run();
            case PostType.MESSAGE -> {
                String messageType = postMessage.getString(Constants.MESSAGE_TYPE);
                switch (messageType) {
                    case MessageType.GROUP -> new GroupMessageHandle().init(postMessage).run();
                    case MessageType.PRIVATE -> new PrivateMessageHandle().init(postMessage).run();
                    default ->
                            SendMessage.sendPrivateMessage(CQ.getCQAt(postMessage.getString(Constants.USER_ID)) + " 暂不支持该消息类型", postMessage.getString(Constants.USER_ID), false);
                }
            }
            case PostType.REQUEST -> {
                System.out.println("请求");
                System.out.println(postMessage);
            }
            default -> System.out.println("未知类型");
        }
    }
}
