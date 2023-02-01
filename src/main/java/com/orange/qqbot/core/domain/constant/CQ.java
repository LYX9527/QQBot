package com.orange.qqbot.core.domain.constant;

/**
 * @author : yilantingfeng
 * @version : v1.0
 * @projectName : QQBot
 * @package : com.orange.qqbot.domain.constant
 * @className : CQFace
 * @description:
 * @date : 2023/1/6 18:19
 */
public class CQ {
    public static String getCQFace(String face) {
        return "[CQ:face,id=" + face + "]";
    }

    public static String getCQAt(String userId) {
        return "[CQ:at,qq=" + userId + "]";
    }

    public static String getCQShare(String url, String title) {
        return "[CQ:share,url=" + url + ",title=" + title + "]";
    }

    public static String getCQImage(String file) {
        return "[CQ:image,file=" + file + "]";
    }

    public static String getCQRecord(String file) {
        return "[CQ:record,file=" + file + "]";
    }

    public static String getCQPoke(String userId) {
        return "[CQ:poke,qq=" + userId + "]";
    }
}
