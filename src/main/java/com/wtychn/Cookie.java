package com.wtychn;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

public class Cookie {
    String userCookie;

    private Map<String, String> map;

    public Cookie() {
        map = new HashMap<>();
    }

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

    public void set(String name, String value) {
        map.put(name, value);
    }

    public String getString() {
        StringBuilder sb = new StringBuilder();
        for (Map.Entry<String, String> entry : map.entrySet()) {
            sb.append(entry.getKey())
                    .append("=")
                    .append(entry.getValue())
                    .append(";");
        }
        sb.deleteCharAt(sb.length() - 1);
        userCookie = sb.toString();
        return userCookie;
    }

    public void setUserCookie(String userCookie) {
        this.userCookie = userCookie;
    }
}
