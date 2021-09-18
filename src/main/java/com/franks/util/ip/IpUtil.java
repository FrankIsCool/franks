package com.franks.util.ip;

import javax.servlet.http.HttpServletRequest;

/**
 * IP工具类
 *
 * @author frank
 * @module
 * @date 2021/9/18 15:27
 */
public class IpUtil {

    /**
     * 获取客户端IP地址
     *
     * @param request
     * @return java.lang.String
     * @Author frank
     * @Date 2021/9/18 15:27
     */
    public static String getIp(HttpServletRequest request) {
        String ip = request.getHeader("x-forwarded-for");
        if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
            ip = request.getHeader("Proxy-Client-IP");
        }
        if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
            ip = request.getHeader("WL-Proxy-Client-IP");
        }
        if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
            ip = request.getHeader("X-Real-IP");
        }
        if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
            ip = request.getHeader("PROXY_FORWARDED_FOR");
        }
        if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
            ip = request.getRemoteAddr();
        }
        int l = ip.indexOf(",");
        if (l != -1) {
            ip = ip.substring(0, l);
        }
        return ip;
    }
}
