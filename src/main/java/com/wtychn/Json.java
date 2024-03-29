package com.wtychn;

import org.json.JSONObject;

import java.io.*;
import java.net.URLEncoder;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Json {
    JSONObject data;

    public Json(JSONObject jsonObject) {
        data = jsonObject;
    }

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
        System.out.println("今天的日期:" + dateStr);
        data.put("date", dateStr);
    }

    //转换为form格式
    public String toForm() throws UnsupportedEncodingException {
        String formString = data.toString()
                .replaceAll(":", "=")
                .replaceAll(",", "&")
                .replaceAll("\"", "")
                .replaceAll("[{}]", "")
                .trim();
        //替换所有中文字符为url utf-8格式
        Pattern p = Pattern.compile("[\\u4e00-\\u9fa5]+");
        Matcher m = p.matcher(formString);
        StringBuffer b = new StringBuffer();
        while (m.find()) {
            m.appendReplacement(b, URLEncoder.encode(m.group(0), "UTF-8"));
        }
        m.appendTail(b);
        return b.toString();
    }
}
