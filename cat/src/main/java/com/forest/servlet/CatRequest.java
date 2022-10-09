package com.forest.servlet;

import java.util.List;
import java.util.Map;

/**
 * @Author forest
 * @Date 2022/9/24 23:05
 * @Version 1.0
 */
public interface CatRequest {
    // 获取URI，包含请求参数，即?后的内容
    String getUri();
    // 获取请求路径，其不包含请求参数
    String getPath();
    // 获取请求方法(GET、POST等)
    String getMethod();
    // 获取所有请求参数
    Map<String, List<String>> getParameters(); // 获取指定名称的请求参数
    List<String> getParameters(String name); // 获取指定名称的请求参数的第一个值
    String getParameter(String name);
}