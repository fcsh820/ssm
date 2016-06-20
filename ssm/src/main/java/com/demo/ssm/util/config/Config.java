package com.demo.ssm.util.config;

/**
 * Created by Administrator on 2016/2/17.
 */
public class Config {
    public static final String INIT_PWD; //初始密码

    static{
        INIT_PWD = get("INIT_PWD");
    }

    private static String get(String name) {
        return ConfigReader.getInstance().get(name);
    }
}
