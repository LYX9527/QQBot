package com.orange.qqbot.core.domain;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import lombok.Data;

/**
 * @author ：yilantingfeng
 * @version : v1.0
 * @project ：PermissionToVerify
 * @package : com.orange.common.model
 * @name ：HttpRequestResult
 * @date ：2023/3/16 13:26
 * @description :
 */
@Data
//@Accessors(chain = true)
public class HttpRequestResult {
    private boolean isSuccess;
    private String result;

    public JSONObject toJsonObject() {
        return isSuccess ? JSONObject.parseObject(result) : null;
    }

    public JSONArray toJsonArray() {
        return isSuccess ? JSONArray.parseArray(result) : null;
    }
}
