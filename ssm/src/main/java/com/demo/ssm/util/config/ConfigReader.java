package com.demo.ssm.util.config;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

/**
 * 配置文件读取
 * Created by Administrator on 2016/2/17.
 */
public class ConfigReader extends Properties {

    private ConfigReader() {
        InputStream in = getClass().getClassLoader().getResourceAsStream(
                "config.properties");
        try {
            this.load(in);
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                in.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    private static ConfigReader _instance = new ConfigReader();
    public static ConfigReader getInstance() {
        return _instance;
    }

    public String get(String key) {
        return this.getProperty(key);
    }
}
