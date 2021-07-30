package com.wtychn;

import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.regex.Pattern;

import javax.net.ssl.HttpsURLConnection;

public class HttpURLConnectionExample {

    private final String USER_AGENT = "Mozilla/5.0 (Windows NT 10.0; WOW64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/81.0.4044.138 Safari/537.36";

    // HTTP GET请求
    public void sendGet(String url) throws Exception {
        URL obj = new URL(url);
        HttpURLConnection con = (HttpURLConnection) obj.openConnection();

        //默认值我GET
        con.setRequestMethod("GET");

        //添加请求头
        con.setRequestProperty("User-Agent", USER_AGENT);
        System.out.println("\nSending 'GET' request to URL : " + url);

        //返回代码
        int responseCode = con.getResponseCode();
        System.out.println(responseCode==200?"get成功!":"get失败!错误代码:"+responseCode);

        BufferedReader in = new BufferedReader(
                new InputStreamReader(con.getInputStream()));
        String inputLine;
        StringBuilder response = new StringBuilder();

        while ((inputLine = in.readLine()) != null) {
            response.append(inputLine);
        }
        in.close();

        //打印结果
        System.out.println(response.toString());

    }

    // HTTP POST请求
    public void sendPost(String url, String cookie, String urlParameters) throws Exception {
        URL obj = new URL(url);
        HttpsURLConnection con = (HttpsURLConnection) obj.openConnection();

        //添加请求头
        con.setRequestMethod("POST");
        con.setRequestProperty("Cookie", cookie);
        con.setRequestProperty("Host", "wxxy.csu.edu.cn");
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
        System.out.println(responseCode == 200 ? "发送成功!" : "发送失败!错误代码:" + responseCode);

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
            System.out.println(responseJson.getString("m"));
        } else {
            System.out.println("发送失败，失败原因：");
            System.out.println(response);
        }

    }

}
