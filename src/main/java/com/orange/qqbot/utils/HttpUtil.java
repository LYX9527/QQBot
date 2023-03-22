package com.orange.qqbot.utils;

import com.alibaba.fastjson.JSONObject;
import com.orange.qqbot.core.domain.HttpRequestResult;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;

import java.util.Map;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.TimeoutException;

/**
 * @author ：yilantingfeng
 * @version : v1.0
 * @project ：PermissionToVerify
 * @package : com.orange.common.utils
 * @name ：OkHttpUtil
 * @date ：2023/3/16 12:22
 * @description :
 */
@Component
public class HttpUtil {
    private static final Logger logger = LoggerFactory.getLogger(HttpUtil.class);
    private WebClient webClient;

    /**
     * 根据map获取请求体 a=aa&b=bb
     *
     * @param queries 请求的参数
     * @return 返回的数据
     */
    private static String getQueryBody(Map<String, String> queries) {
        StringBuilder sb = new StringBuilder();
        if (queries != null && queries.keySet().size() > 0) {
            boolean firstFlag = true;
            for (Map.Entry<String, String> entry : queries.entrySet()) {
                if (firstFlag) {
                    sb.append(entry.getKey()).append("=").append(entry.getValue());
                    firstFlag = false;
                } else {
                    sb.append("&").append(entry.getKey()).append("=").append(entry.getValue());
                }
            }
        }
        return sb.toString();
    }

    /**
     * get请求处理
     *
     * @param url    请求的url
     * @param params 请求的参数
     * @return 返回的数据
     */
    public HttpRequestResult get(String url, Map<String, String> params, Map<String, String> headers) {
        WebClient.ResponseSpec retrieve = webClient.get().uri(uriBuilder -> {
            if (params != null && params.keySet().size() > 0) {
                params.forEach(uriBuilder::queryParam);
            }
            return uriBuilder.path(handleUrl(url)).build();
        }).headers(httpHeaders -> {
            if (headers != null && headers.keySet().size() > 0) {
                headers.forEach(httpHeaders::add);
            }
        }).retrieve();
        Mono<ResponseEntity<String>> responseEntityMono = retrieve.toEntity(String.class);
        return handleResponse(responseEntityMono);
    }

    /**
     * post请求发送 application/x-www-form-urlencoded数据
     *
     * @param url     请求的url
     * @param params  请求的参数
     * @param headers 请求头
     * @return 返回的数据
     */
    public HttpRequestResult postParams(String url, Map<String, String> params, Map<String, String> headers) {
        WebClient.ResponseSpec retrieve = webClient.post().uri(url).headers(httpHeaders -> {
                    if (headers != null && headers.keySet().size() > 0) {
                        headers.forEach(httpHeaders::add);
                    }
                }).contentType(MediaType.APPLICATION_FORM_URLENCODED)
                .bodyValue(getQueryBody(params)).retrieve();
        Mono<ResponseEntity<String>> responseEntityMono = retrieve.toEntity(String.class);
        return handleResponse(responseEntityMono);
    }


    /**
     * post请求发送multipart/form-data数据
     *
     * @param url     请求的url
     * @param files   文件列表
     * @param headers 请求头
     * @return 返回的数据
     */
//    public HttpRequestResult postMultipartFormData(String url, MultipartFile[] files, Map<String, String> params, Map<String, String> headers) {
//        MultipartBodyBuilder builder = new MultipartBodyBuilder();
//        builder.part("file", new FileSystemResource(FileUtils.multipartFileToFile(files[0])));
//        if (params != null && params.keySet().size() > 0) {
//            params.forEach(builder::part);
//        }
//        WebClient.ResponseSpec retrieve = webClient.post().uri(url).headers(httpHeaders -> {
//                    if (headers != null && headers.keySet().size() > 0) {
//                        headers.forEach(httpHeaders::add);
//                    }
//                }).contentType(MediaType.MULTIPART_FORM_DATA)
//                .body(BodyInserters.fromMultipartData(builder.build())).retrieve();
//        Mono<ResponseEntity<String>> responseEntityMono = retrieve.toEntity(String.class);
//        return handleResponse(responseEntityMono);
//    }

    /**
     * post请求发送JSON数据
     *
     * @param url        请求的url
     * @param jsonObject 请求的参数
     * @param headers    请求头
     * @return 返回的数据
     */
    public HttpRequestResult postJson(String url, JSONObject jsonObject, Map<String, String> headers) {
        WebClient.ResponseSpec retrieve = webClient.post().uri(url).headers(httpHeaders -> {
                    if (headers != null && headers.keySet().size() > 0) {
                        headers.forEach(httpHeaders::add);
                    }
                }).contentType(MediaType.APPLICATION_JSON)
                .bodyValue(jsonObject.toJSONString()).retrieve();
        Mono<ResponseEntity<String>> responseEntityMono = retrieve.toEntity(String.class);
        return handleResponse(responseEntityMono);
    }

    public HttpUtil setBaseUrl(String baseUrl) {
        //判断baseUrl是否以/结尾，如果不是则加上/
        if (!baseUrl.endsWith("/")) {
            baseUrl = baseUrl + "/";
        }
        webClient = WebClient.create(baseUrl);
        return this;
    }

    /**
     * 处理返回结果
     *
     * @param mono 返回结果
     * @return 处理后的结果
     */
    private HttpRequestResult handleResponse(Mono<ResponseEntity<String>> mono) {
        HttpRequestResult httpRequestResult = new HttpRequestResult();
        try {
            CompletableFuture<ResponseEntity<String>> future = mono.toFuture();
            ResponseEntity<String> stringResponseEntity = future.get(100, TimeUnit.SECONDS);
            httpRequestResult.setSuccess(true);
            httpRequestResult.setResult(stringResponseEntity.getBody());
        } catch (InterruptedException | ExecutionException | TimeoutException e) {
            httpRequestResult.setSuccess(false);
            logger.error("请求失败{}", e.getMessage());
            httpRequestResult.setResult(e.getMessage());
        }
        return httpRequestResult;
    }

    /**
     * 处理url，如果url开头为/，则去掉/
     *
     * @param url 请求的url
     * @return 处理后的url
     */
    private String handleUrl(String url) {
        String temp;
        if (url.startsWith("/")) {
            temp = url.substring(1);
        } else {
            temp = url;
        }
        return temp;
    }
}
