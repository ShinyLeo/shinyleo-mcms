package com.shinyleo.proxy.util;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by shinyleo on 17/7/20.
 */
public class Header {

    private Map headers = new HashMap();

    public Header(String host, String code) {
        this.headers.put("Host", host);
        this.headers.put("Connection", "keep-alive");
        this.headers.put("Referer", "http://" + host);
        this.headers.put("Cache-Control", "max-age=0");
        this.headers.put("User-Agent", "Mozilla/5.0 (Windows NT 5.1) AppleWebKit/535.1 (KHTML, like Gecko) Chrome/14.0.835.186 Safari/535.1");
        this.headers.put("Content-Type", "application/x-www-form-urlencoded");
        this.headers.put("Accept", "text/html,application/xhtml+xml,application/xml;q=0.9,*/*;q=0.8");
        this.headers.put("Accept-Language", "zh-CN,zh;q=0.8");
        this.headers.put("Accept-Charset", "GBK,utf-8;q=0.7,*;q=0.3");
        this.headers.put("Cookie", this.getCookie());
    }

    public String getCookie() {
        return this.headers.get("Cookie") != null?(String)this.headers.get("Cookie"):null;
    }

    public void setCookie(String cookie) {
        this.headers.put("Cookie", cookie);
    }

    public void setHeader(String key, String value) {
        this.headers.put(key, value);
    }

    public Map getHeaders() {
        return this.headers;
    }
}
