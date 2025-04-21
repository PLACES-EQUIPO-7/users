package com.places.users.config;

import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoClients;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.env.Environment;
import org.springframework.data.mongodb.core.MongoTemplate;

@Configuration
public class mongo {

    @Bean
    public MongoClient mongoClient(Environment env) {

        String host = env.getProperty("mongo.conn.host");
        String port = env.getProperty("mongo.conn.port");
        String user = env.getProperty("mongo.conn.user");
        String password = env.getProperty("mongo.conn.password");
        String url = String.format("mongodb+srv://%s:%s@%s/?retryWrites=true&w=majority&appName=my-cluster", user, password, host);

        return MongoClients.create(url);
    }

    @Bean
    public MongoTemplate mongoTemplateUsers(MongoClient mongoClient, Environment env) {
        String database = env.getProperty("mongo.conn.database");
        return new MongoTemplate(mongoClient, database);
    }

}
