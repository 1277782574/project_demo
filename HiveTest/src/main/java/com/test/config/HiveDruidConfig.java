package com.test.config;
import com.alibaba.druid.pool.DruidDataSource;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.core.JdbcTemplate;
import javax.sql.DataSource;



@Configuration
public class HiveDruidConfig {

    @Value("${hive.url}")
    private String url;

    @Value("${hive.user}")
    private String user;

    @Value("${hive.password}")
    private String password;

    @Value("${hive.driver-class-name}")
    private String driverClassName;

    @Value("${hive.initialSize}")
    private int initialSize;

    @Value("${hive.minIdle}")
    private int minIdle;

    @Value("${hive.maxActive}")
    private int maxActive;

    @Value("${hive.maxWait}")
    private int maxWait;

    @Value("${hive.timeBetweenEvictionRunsMillis}")
    private int timeBetweenEvictionRunsMillis;

    @Value("${hive.minEvictableIdleTimeMillis}")
    private int minEvictableIdleTimeMillis;

    @Value("${hive.validationQuery}")
    private String validationQuery;

    @Value("${hive.testWhileIdle}")
    private boolean testWhileIdle;

    @Value("${hive.testOnBorrow}")
    private boolean testOnBorrow;

    @Value("${hive.testOnReturn}")
    private boolean testOnReturn;

    @Value("${hive.poolPreparedStatements}")
    private boolean poolPreparedStatements;

    @Value("${hive.maxPoolPreparedStatementPerConnectionSize}")
    private int maxPoolPreparedStatementPerConnectionSize;

    @Bean(name="hiveDruidDataSource")
    public DataSource dataSourcece(){
        DruidDataSource dataSource =new DruidDataSource();
        dataSource.setUrl(url);
        dataSource.setDriverClassName(driverClassName);
        dataSource.setUsername(user);
        dataSource.setPassword(password);
        dataSource.setInitialSize(initialSize);
        dataSource.setMinIdle(minIdle);
        dataSource.setMaxActive(maxActive);
        dataSource.setMaxWait(maxWait);
        dataSource.setTimeBetweenEvictionRunsMillis(timeBetweenEvictionRunsMillis);
        dataSource.setMinEvictableIdleTimeMillis(minEvictableIdleTimeMillis);
        dataSource.setValidationQuery(validationQuery);
        dataSource.setTestWhileIdle(testWhileIdle);
        dataSource.setTestOnBorrow(testOnBorrow);
        dataSource.setTestOnReturn(testOnReturn);
        dataSource.setPoolPreparedStatements(poolPreparedStatements);
        dataSource.setMaxPoolPreparedStatementPerConnectionSize(maxPoolPreparedStatementPerConnectionSize);
        return dataSource;
    }

    @Bean(name="hiveDruidTemplate")
    public JdbcTemplate jdbcTemplate(@Qualifier("hiveDruidDataSource") DataSource dataSource){
        return new JdbcTemplate(dataSource);
    }

}
