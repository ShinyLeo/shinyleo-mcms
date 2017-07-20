package com.shinyleo.proxy.util;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.DefaultHttpClient;

import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.message.BasicHeader;
import org.apache.http.message.BasicNameValuePair;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.*;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import org.apache.http.cookie.Cookie;

/**
 * Created by shinyleo on 17/7/20.
 */
public class Proxy {

    public static Logger log = LoggerFactory.getLogger(Proxy.class);

    public Proxy() {
    }

    public static Result get(String url, Header header, Map params) {
        DefaultHttpClient client = new DefaultHttpClient();
        url = url + (params == null?"":assemblyParameter(params));
        HttpGet httpGet = new HttpGet(url);
        httpGet.getParams().setParameter("http.protocol.cookie-policy", "compatibility");
        if(header != null && header.getHeaders().size() > 0) {
            httpGet.setHeaders(assemblyHeader(header.getHeaders()));
        }

        try {
            CloseableHttpResponse response = client.execute(httpGet);
            HttpEntity e = response.getEntity();
            Result result = new Result();
            result.setCookie(assemblyCookie(client.getCookieStore().getCookies()));
            result.setStatusCode(response.getStatusLine().getStatusCode());
            result.setHeaders(response.getAllHeaders());
            result.setHttpEntity(e);
            return result;
        } catch (ClientProtocolException var8) {
            var8.printStackTrace();
            log.error(var8.getMessage());
        } catch (IOException var9) {
            var9.printStackTrace();
            log.error(var9.getMessage());
        }

        return null;
    }

    public static void get(String url, String path) {
        try {
            CloseableHttpClient e2 = HttpClientBuilder.create().build();
            HttpGet gm = new HttpGet(url);
            HttpResponse response = e2.execute(gm);
            InputStream is = response.getEntity().getContent();
            FileOutputStream os = new FileOutputStream(path);
            boolean c = true;

            int c1;
            while((c1 = is.read()) != -1) {
                os.write(c1);
            }

            is.close();
            os.flush();
            os.close();
        } catch (IOException var8) {
            var8.printStackTrace();
        }

    }

    public static Result post(String url, Header header, Map params, String encoding) {
        DefaultHttpClient client = new DefaultHttpClient();
        HttpPost httpPost = new HttpPost(url);
        httpPost.getParams().setParameter("http.protocol.cookie-policy", "compatibility");
        ArrayList response;
        if(params != null) {
            response = new ArrayList();
            Iterator e = params.keySet().iterator();

            while(e.hasNext()) {
                String result = (String)e.next();
                response.add(new BasicNameValuePair(result, "" + params.get(result)));
            }

            try {
                httpPost.setEntity(new UrlEncodedFormEntity(response, encoding));
            } catch (UnsupportedEncodingException var11) {
                var11.printStackTrace();
                log.error(var11.getMessage());
            }
        }

        if(header != null && header.getHeaders().size() > 0) {
            httpPost.setHeaders(assemblyHeader(header.getHeaders()));
        }

        response = null;

        try {
            CloseableHttpResponse response1 = client.execute(httpPost);
            HttpEntity e1 = response1.getEntity();
            Result result1 = new Result();
            result1.setStatusCode(response1.getStatusLine().getStatusCode());
            result1.setHeaders(response1.getAllHeaders());
            result1.setCookie(assemblyCookie(client.getCookieStore().getCookies()));
            result1.setHttpEntity(e1);
            return result1;
        } catch (ClientProtocolException var9) {
            var9.printStackTrace();
            log.error(var9.getMessage());
        } catch (IOException var10) {
            var10.printStackTrace();
            log.error(var10.getMessage());
        }

        return null;
    }

    public static void getRandCode(String url, Header header, String fileUrl) {
        DefaultHttpClient client = new DefaultHttpClient();
        HttpGet get = new HttpGet(url);
        get.getParams().setParameter("http.protocol.cookie-policy", "compatibility");
        Map _headers = header.getHeaders();
        if(header != null && _headers.size() > 0) {
            get.setHeaders(assemblyHeader(_headers));
        }

        try {
            CloseableHttpResponse response = client.execute(get);
            HttpEntity e = response.getEntity();
            InputStream in = e.getContent();
            header.setCookie(assemblyCookie(client.getCookieStore().getCookies()));
            boolean temp = false;
            File file = new File(fileUrl);
            FileOutputStream out = new FileOutputStream(file);

            int temp1;
            while((temp1 = in.read()) != -1) {
                out.write(temp1);
            }

            in.close();
            out.close();
        } catch (ClientProtocolException var12) {
            var12.printStackTrace();
            log.error(var12.getMessage());
        } catch (IOException var13) {
            var13.printStackTrace();
            log.error(var13.getMessage());
        }

    }

    public static BasicHeader[] assemblyHeader(Map headers) {
        BasicHeader[] allHeader = new BasicHeader[headers.size()];
        int i = 0;

        for(Iterator it = headers.keySet().iterator(); it.hasNext(); ++i) {
            String str = (String)it.next();
            allHeader[i] = new BasicHeader(str, (String)headers.get(str));
        }

        return allHeader;
    }

    public static String assemblyCookie(List cookies) {
        StringBuffer sbu = new StringBuffer();

        for(int i = 0; i < cookies.size(); ++i) {
            Cookie cookie = (Cookie)cookies.get(i);
            sbu.append(cookie.getName()).append("=").append(cookie.getValue()).append(";");
        }

        if(sbu.length() > 0) {
            sbu.deleteCharAt(sbu.length() - 1);
        }

        return sbu.toString();
    }

    public static String assemblyParameter(Map parameters) {
        String para = "?";

        String str;
        for(Iterator it = parameters.keySet().iterator(); it.hasNext(); para = para + str + "=" + parameters.get(str) + "&") {
            str = (String)it.next();
        }

        return para.substring(0, para.length() - 1);
    }
}
