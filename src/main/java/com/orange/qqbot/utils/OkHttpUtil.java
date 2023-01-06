package com.orange.qqbot.utils;

import okhttp3.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Map;
import java.util.concurrent.TimeUnit;

/**
 * Created by yilantingfen
 * on 2023/01/06 16:55
 **/
public class OkHttpUtil {

    private static final Logger logger = LoggerFactory.getLogger(OkHttpUtil.class);

    /**
     * 根据map获取get请求参数
     *
     * @param queries
     * @return
     */
    public static StringBuffer getQueryString(String url, Map<String, String> queries) {
        StringBuffer sb = new StringBuffer(url);
        if (queries != null && queries.keySet().size() > 0) {
            boolean firstFlag = true;
            for (Map.Entry<String, String> entry : queries.entrySet()) {
                if (firstFlag) {
                    sb.append("?").append(entry.getKey()).append("=").append(entry.getValue());
                    firstFlag = false;
                } else {
                    sb.append("&").append(entry.getKey()).append("=").append(entry.getValue());
                }
            }
        }
        return sb;
    }

    /**
     * 调用okhttp的newCall方法
     *
     * @param request
     * @return
     */
    private static String execNewCall(Request request) {
        OkHttpClient client = new OkHttpClient.Builder()
                .connectTimeout(15, TimeUnit.SECONDS)
                .writeTimeout(15, TimeUnit.SECONDS)
                .readTimeout(15, TimeUnit.SECONDS)
                .build();
        try (Response response = client.newCall(request).execute()) {
            assert response.body() != null;
            return response.body().string();
        } catch (Exception e) {
            logger.error("okhttp3 put error >> ex = {}", e.getMessage());
        }
        return "";
    }

    /**
     * get
     *
     * @param url     请求的url
     * @param queries 请求的参数，在浏览器？后面的数据，没有可以传null
     * @return
     */
    public static String get(String url, Map<String, String> queries, Map<String, String> headers) {
        StringBuffer sb = getQueryString(url, queries);
        Request.Builder builder = new Request.Builder();
        builder = builder.url(sb.toString());
        if (headers != null) {
            builder = builder.headers(Headers.of(headers));
        }
        return execNewCall(builder.build());
    }

    /**
     * Post请求发送JSON数据....{"name":"zhangsan","pwd":"123456"}
     * 参数一：请求Url
     * 参数二：请求的JSON
     * 参数三：请求回调
     */
    public static String postJsonParams(String url, String jsonParams, Map<String, String> headers) {
        RequestBody requestBody = RequestBody.create(MediaType.parse("application/json; charset=utf-8"), jsonParams);
        Request request = new Request.Builder()
                .url(url)
                .post(requestBody)
                .headers(Headers.of(headers))
                .build();
        return execNewCall(request);
    }

    /**
     * Post请求发送xml数据....
     * 参数一：请求Url
     * 参数二：请求的xmlString
     * 参数三：请求回调
     */
    public static String postXmlParams(String url, String xml) {
        RequestBody requestBody = RequestBody.create(MediaType.parse("application/xml; charset=utf-8"), xml);
        Request request = new Request.Builder()
                .url(url)
                .post(requestBody)
                .build();
        return execNewCall(request);
    }

    /**
     * Post请求发送multipart/form-data数据....
     * 参数一：请求Url
     * 参数二：请求的参数体
     * 参数三：请求头
     */
    public static String postMultipartFormDataParams(String url, RequestBody requestBody, Map<String, String> headers) {
        Request request = new Request.Builder()
                .url(url)
                .post(requestBody)
                .headers(Headers.of(headers))
                .build();
        return execNewCall(request);
    }

    /**
     * Post请求发送application/x-www-form-urlencoded数据....
     * 参数一：请求Url
     * 参数二：请求的参数体
     * 参数三：请求头
     */
    public static String postMapParams(String url, Map<String, String> params, Map<String, String> headers) {
        String s = getQueryString("", params).toString();
        System.out.println(s);
        RequestBody requestBody = RequestBody.create(MediaType.parse("application/x-www-form-urlencoded; charset=utf-8"), s.replace("?", ""));
        Request request = new Request.Builder()
                .url(url)
                .post(requestBody)
                .headers(Headers.of(headers))
                .build();
        return execNewCall(request);
    }

}
