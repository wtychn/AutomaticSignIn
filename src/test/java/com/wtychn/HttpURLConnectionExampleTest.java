package com.wtychn;

import org.junit.Test;

public class HttpURLConnectionExampleTest {
    @Test
    public void getTest() throws Exception {
        HttpURLConnectionExample http = new HttpURLConnectionExample();

        String url = "https://cn.bing.com/search?q=java";

        System.out.println("Testing - Send Http GET request");
        http.sendGet(url);
    }

    @Test
    public void postTest() throws Exception {
        HttpURLConnectionExample http = new HttpURLConnectionExample();

        String url = "https://wxxy.csu.edu.cn/ncov/wap/default/save";
        System.out.println("Testing - Send Http POST request");

        Json jsonDate = new Json("src\\main\\resources\\src.json");
        Cookie cookieDate = new Cookie("src\\main\\resources\\cookie.txt");
        jsonDate.updateDate();

        http.sendPost(url, cookieDate.userCookie, jsonDate.toForm());
    }
}