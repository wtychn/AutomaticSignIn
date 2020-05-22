package com.wtychn;

import org.json.JSONObject;

import java.io.*;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

public class Json {
    JSONObject data;

    public Json(String fileName) throws IOException {
        // 读取原始json文件
        File file = new File(fileName);
        BufferedReader br = new BufferedReader(new FileReader(file));
        String line;
        StringBuilder jsonString = new StringBuilder();
        //缓存流逐行读入
        while ((line = br.readLine()) != null) {
            jsonString.append(line);
        }
        data = new JSONObject(jsonString.toString());
    }

    //修改日期
    public void updateDate() {
        Date today = new Date();
        DateFormat dateFormat = new SimpleDateFormat("yyyyMMdd");
        String dateStr = dateFormat.format(today);
        System.out.println("日期更改为:" + dateStr);
        data.put("date", dateStr);
    }

    //转换为form格式
    public String toForm() {
        return data.toString()
                .replaceAll(":","=")
                .replaceAll(",","&")
                .replaceAll("\"","")
                .replaceAll("[{}]","")
                .trim();
    }
}
