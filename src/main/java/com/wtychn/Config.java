package com.wtychn;

import org.yaml.snakeyaml.Yaml;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;
import java.util.Map;

public class Config {
    // 首先声明一个Map存解析之后的内容:
    public static Map properties;

    // 空的构造函数
    public Config() {
        initWithString("config.yml");
    }

    // 以文件路径为条件的构造函数
    public Config(String filePath) {
        initWithString(filePath);
    }

    // 从String 中获取配置的方法
    public void initWithString(String filePath) {
        InputStream inputStream = null;
        try {
            inputStream = new FileInputStream(filePath);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        // 调基础工具类的方法
        Yaml yaml = new Yaml();
        properties = yaml.loadAs(inputStream, Map.class);
    }

    public boolean isRunnable() {
        return (boolean) properties.get("status");
    }

}
