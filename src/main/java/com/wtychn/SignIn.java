package com.wtychn;

import de.sstoehr.harreader.model.Har;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.List;

public class SignIn {
    private static final Logger logger = LoggerFactory.getLogger(SignIn.class);

    public static void main(String[] args) throws Exception {
        Config config = new Config();
        if(!config.isRunnable()) return;

        HttpURLConnectionExample http = new HttpURLConnectionExample();

        String url = "https://wxxy.csu.edu.cn/ncov/wap/default/save";
        System.out.println("自动健康打卡启动...");

        HarTransform harTransform = new HarTransform();
        List<Har> hars = harTransform.readHars("src/main/resources/hars");
        int idx = 0;
        for (Har har : hars) {
            idx += 1;
            logger.info("正在进行第{}个打卡进程...", idx);
            String params;
            Cookie cookiesDate;
            params = harTransform.getParams(har);
            cookiesDate = harTransform.getCookie(har);

            http.sendPost(url, cookiesDate.userCookie, params);
        }

    }
}
