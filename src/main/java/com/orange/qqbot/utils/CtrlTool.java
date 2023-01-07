package com.orange.qqbot.utils;

/**
 * @author : yilantingfeng
 * @version : v1.0
 * @projectName : QQBot
 * @package : com.orange.qqbot.utils
 * @className : CtrlTool
 * @description:
 * @date : 2023/1/7 18:22
 */
public class CtrlTool {
    public static String timestampToText(Long time) {
        return new java.text.SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new java.util.Date(time * 1000));
    }
}
