package com.test.config;

import org.apache.hadoop.hbase.HBaseConfiguration;
import org.apache.hadoop.hbase.client.HBaseAdmin;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.hadoop.hbase.HbaseTemplate;

import java.io.IOException;
import java.util.Map;
import java.util.Set;

/**
 * 把配置加入到hbaseTemplate中，交给spring容器
 * 再把hbaseAdmin交给spring容器
 */
@Configuration
@EnableConfigurationProperties(HBaseProperties.class)
public class HBaseConfig {


    private final HBaseProperties properties;

    public HBaseConfig(HBaseProperties properties){
        this.properties =properties;
    }

    @Bean
    public HbaseTemplate hbaseTemplate(){
        HbaseTemplate hbaseTemplate =new HbaseTemplate();
        hbaseTemplate.setConfiguration(configuration());
        hbaseTemplate.setAutoFlush(true);
        return hbaseTemplate;
    }

    @Bean
    public HBaseAdmin hbaseAdmin() throws IOException {
        return  new HBaseAdmin(hbaseTemplate().getConfiguration());
    }

    private org.apache.hadoop.conf.Configuration configuration() {
        org.apache.hadoop.conf.Configuration configuration = HBaseConfiguration.create();

        Map<String, String> config = properties.getConfig();

        Set<String> keySet = config.keySet();
        for (String key : keySet) {
            configuration.set(key,config.get(key));
        }
        return configuration;
    }
}
