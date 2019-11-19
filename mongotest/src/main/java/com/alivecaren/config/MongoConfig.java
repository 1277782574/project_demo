package com.alivecaren.config;

import com.mongodb.MongoClient;
import com.mongodb.MongoClientURI;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.mongodb.MongoDbFactory;
import org.springframework.data.mongodb.MongoTransactionManager;
import org.springframework.data.mongodb.config.AbstractMongoConfiguration;
import org.springframework.data.mongodb.core.convert.MongoConverter;
import org.springframework.data.mongodb.gridfs.GridFsTemplate;

@Configuration
public class MongoConfig extends AbstractMongoConfiguration {

    @Value("${spring.data.mongodb.uri}")
    private String mongoUri;
    @Autowired
    private MongoConverter mongoConverter;

    @Bean
    GridFsTemplate gridFsTemplate(MongoDbFactory dbFactory) {
        return new GridFsTemplate(dbFactory, mongoConverter);
    }

    @Bean
    MongoTransactionManager transactionManager(MongoDbFactory dbFactory) {
        return new MongoTransactionManager(dbFactory);
    }

    @Override
    public MongoClient mongoClient() {
        MongoClientURI uri = new MongoClientURI(mongoUri);
        return new MongoClient(uri);
    }

    @Override
    protected String getDatabaseName() {
        String dataBase = mongoUri.substring(mongoUri.lastIndexOf("/") + 1);
        return dataBase;
    }

}
