package com.shinyleo.proxy.util;

import org.apache.http.*;
import org.apache.http.util.EntityUtils;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.zip.GZIPInputStream;

/**
 * Created by shinyleo on 17/7/20.
 */
public class Result {

    private String cookie;
    private int statusCode;
    private HashMap headerAll;
    private HttpEntity httpEntity;

    public Result() {
    }

    public String getCookie() {
        return this.cookie;
    }

    public int getStatusCode() {
        return this.statusCode;
    }

    public HashMap getHeaders() {
        return this.headerAll;
    }

    public void setHeaders(org.apache.http.Header[] headers) {
        this.headerAll = new HashMap();

        for(int i = 0; i < headers.length; ++i) {
            org.apache.http.Header header = headers[i];
            this.headerAll.put(header.getName(), header.getValue());
        }

    }

    public HttpEntity getHttpEntity() {
        return this.httpEntity;
    }

    public void setHttpEntity(HttpEntity httpEntity) {
        this.httpEntity = httpEntity;
    }

    public String getContent(String charset) {
        try {
            return EntityUtils.toString(this.httpEntity, charset);
        } catch (ParseException var3) {
            var3.printStackTrace();
        } catch (IOException var4) {
            var4.printStackTrace();
        }

        return null;
    }

    public String getContent() {
        try {
            return EntityUtils.toString(this.httpEntity);
        } catch (ParseException var2) {
            var2.printStackTrace();
        } catch (IOException var3) {
            var3.printStackTrace();
        }

        return null;
    }

    public String getContentForGzip(String charset) {
        if(this.httpEntity.getContentEncoding().getValue().indexOf("gzip") > -1) {
            try {
                GZIPInputStream e = new GZIPInputStream(this.httpEntity.getContent());
                InputStreamReader isr = new InputStreamReader(e, charset);
                BufferedReader br = new BufferedReader(isr);
                StringBuffer sb = new StringBuffer();

                String tempbf;
                while((tempbf = br.readLine()) != null) {
                    sb.append(tempbf);
                    sb.append("\r\n");
                }

                e.close();
                isr.close();
                return sb.toString();
            } catch (IllegalStateException var7) {
                var7.printStackTrace();
            } catch (IOException var8) {
                var8.printStackTrace();
            }
        }

        return null;
    }

    public void setCookie(String cookie) {
        this.cookie = cookie;
    }

    public void setStatusCode(int statusCode2) {
        this.statusCode = statusCode2;
    }
}
