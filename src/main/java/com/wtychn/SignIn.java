package com.wtychn;

import java.io.IOException;

public class SignIn {
    public static void main(String[] args) throws Exception {
        HttpURLConnectionExample http = new HttpURLConnectionExample();

        String url = "https://wxxy.csu.edu.cn/ncov/wap/default/save";
        System.out.println("自动健康打卡启动...");

        Json jsonDate = new Json("src\\main\\resources\\src.json");
        Cookie cookieDate = new Cookie("src\\main\\resources\\cookie.txt");
        jsonDate.updateDate();

        http.sendPost(url, cookieDate.userCookie, jsonDate.toForm());
    }
}
