package com.orange.qqbot.core.domain;

import com.orange.qqbot.core.domain.constant.PostType;
import lombok.Data;

/**
 * @author : yilantingfeng
 * @version : v1.0
 * @projectName : QQBot
 * @package : com.orange.qqbot.domain
 * @className : Message
 * @description:
 * @date : 2023/1/6 13:10
 */
@Data
public class Message {
    private Integer time;
    private Integer self_id;
    private PostType post_type;
}
