package com.orange.qqbot.core.handle.eventhandel;

import com.orange.qqbot.core.domain.constant.KeyWord;

/**
 * @author : yilantingfeng
 * @version : v1.0
 * @projectName : QQBot
 * @package : com.orange.qqbot.handle.eventhandel
 * @className : KeyWordHandle
 * @description:
 * @date : 2023/1/8 17:46
 */
public class KeyWordHandle {
    public static void handle(String message, String groupId) {
        String keyword = message.trim();
        switch (keyword) {
            case KeyWord.TIME -> NowTimeHandle.handle(groupId);
            case KeyWord.HOT_SEARCH -> HotSearchHandle.handle(groupId);
            default -> System.out.println("未知关键字");
        }
    }
}
