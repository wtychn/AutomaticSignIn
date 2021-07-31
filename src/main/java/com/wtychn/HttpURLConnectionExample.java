package com.wtychn;

import org.json.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.regex.Pattern;

import javax.net.ssl.HttpsURLConnection;

public class HttpURLConnectionExample {

    private static final Logger logger = LoggerFactory.getLogger(HttpURLConnectionExample.class);

    // HTTP POST请求
    public void sendPost(String url, String cookie, String urlParameters) throws Exception {
        URL obj = new URL(url);
        HttpsURLConnection con = (HttpsURLConnection) obj.openConnection();

        //添加请求头
        con.setRequestMethod("POST");
        con.setRequestProperty("Cookie", cookie);
        con.setRequestProperty("Host", "wxxy.csu.edu.cn");
        String USER_AGENT = "Mozilla/5.0 (Windows NT 10.0; WOW64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/81.0.4044.138 Safari/537.36";
        con.setRequestProperty("User-Agent", USER_AGENT);
        con.setRequestProperty("Refer", "https://wxxy.csu.edu.cn/ncov/wap/default/index");

        //发送Post请求
//        System.out.println("Sending 'POST' request to URL : " + url);
//        System.out.println("Post parameters : " + urlParameters);
        con.setDoOutput(true);
        con.setDoInput(true);
        DataOutputStream wr = new DataOutputStream(con.getOutputStream());
        wr.writeBytes(urlParameters);
        wr.flush();
        wr.close();

        //接收response
        int responseCode = con.getResponseCode();
        if (responseCode == 200) {
            logger.info("发送完毕!");
        } else {
            logger.warn("发送失败！错误代码：{}", responseCode);
        }

        BufferedReader in = new BufferedReader(new InputStreamReader(con.getInputStream()));
        String inputLine;
        StringBuilder response = new StringBuilder();

        while ((inputLine = in.readLine()) != null) {
            response.append(inputLine);
        }
        in.close();

        //打印结果
        if (response.toString().startsWith("{")) {
            JSONObject responseJson = new JSONObject(response.toString());
            logger.info(responseJson.getString("m"));
        } else {
            logger.warn("发送失败，失败原因：{}", response);
        }

    }

}
