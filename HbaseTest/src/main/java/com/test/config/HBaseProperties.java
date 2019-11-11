package com.test.config;

import org.springframework.boot.context.properties.ConfigurationProperties;

import java.util.Map;

/**
 * 用来读取hbase配置文件
 */
@ConfigurationProperties(prefix = "hbase")
public class HBaseProperties {


    private Map<String,String> config;


    public Map<String, String> getConfig() {
        return config;
    }

    public void setConfig(Map<String, String> config) {
        this.config = config;
    }
}
