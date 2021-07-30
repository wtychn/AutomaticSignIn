package com.wtychn;

import de.sstoehr.harreader.model.Har;

import java.io.IOException;
import java.util.List;

public class SignIn {
    public static void main(String[] args) throws Exception {
        Config config = new Config();
        if(!config.isRunnable()) return;

        HttpURLConnectionExample http = new HttpURLConnectionExample();

        String url = "https://wxxy.csu.edu.cn/ncov/wap/default/save";
        System.out.println("自动健康打卡启动...");

        HarTransform harTransform = new HarTransform();
        List<Har> hars = harTransform.readHars("src/main/resources/hars");
        for (Har har : hars) {
            String params;
            Cookie cookiesDate;
            params = harTransform.getParams(har);
            cookiesDate = harTransform.getCookie(har);

            http.sendPost(url, cookiesDate.userCookie, params);
        }

    }
}
