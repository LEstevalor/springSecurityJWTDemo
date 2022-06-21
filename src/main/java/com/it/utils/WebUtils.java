package com.it.utils;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * 将字符串渲染到客户端
 * 向响应之中写入中聚类
 *
 * @author bing_  @create 2022/1/4-22:20
 */
public class WebUtils {
    /**
     * 将字符串渲染到客户端
     *
     * @param response 渲染对象
     * @param string   待渲染的字符串
     * @return null
     */
    public static String renderString(HttpServletResponse response, String string) {
        try {
            response.setStatus(200);
            response.setContentType("application/json");
            response.setCharacterEncoding("utf-8");
            response.getWriter().print(string);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }
}

