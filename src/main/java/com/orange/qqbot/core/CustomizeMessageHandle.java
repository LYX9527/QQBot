package com.orange.qqbot.core;

import com.alibaba.fastjson.JSONObject;

public interface CustomizeMessageHandle {
    void handle(JSONObject postMessage);
}
