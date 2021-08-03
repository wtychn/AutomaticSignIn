package com.wtychn;

import de.sstoehr.harreader.model.Har;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.yaml.snakeyaml.Yaml;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;
import java.util.*;

public class SignIn {
    private static final Logger logger = LoggerFactory.getLogger(SignIn.class);

    public static void main(String[] args) {
        SignIn signIn = new SignIn();
        signIn.timer();
    }

    public void timer() {
        Calendar calendar = Calendar.getInstance();
        calendar.set(Calendar.HOUR_OF_DAY, 0); // 控制时
        calendar.set(Calendar.MINUTE, 10);     // 控制分
        calendar.set(Calendar.SECOND, 0);      // 控制秒

        Date time = calendar.getTime();        // 得出执行任务的时间,此处为今天的 00：10：00

        Timer timer = new Timer();
        timer.scheduleAtFixedRate(new TimerTask() {
            public void run() {
                // -------设定要指定任务--------
                try {
                    startSignIn();
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }, time, 1000 * 60 * 60 * 24);// 这里设定将延时每天固定执行
    }

    private void startSignIn() throws Exception {
        Config config = new Config();
        if(!config.isRunnable()) return;

        HttpURLConnectionExample http = new HttpURLConnectionExample();

        String url = "https://wxxy.csu.edu.cn/ncov/wap/default/save";
        System.out.println("自动健康打卡启动...");

        HarTransform harTransform = new HarTransform();
        List<Har> hars = harTransform.readHars((String) Config.properties.get("hars"));
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
