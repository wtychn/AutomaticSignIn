package com.wtychn;

import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;

public class Cookie {
    String userCookie;
    public Cookie(String fileName) throws IOException {
        // 读取原始文件
        File file = new File(fileName);
        BufferedReader br = new BufferedReader(new FileReader(file));
        String line;
        StringBuilder cookieString = new StringBuilder();
        //缓存流逐行读入
        while ((line = br.readLine()) != null) {
            cookieString.append(line);
        }
        userCookie = cookieString.toString();
    }
}
