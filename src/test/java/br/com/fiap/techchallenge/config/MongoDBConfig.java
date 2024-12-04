package br.com.fiap.techchallenge.config;

import org.springframework.boot.test.context.TestConfiguration;
import org.testcontainers.containers.MongoDBContainer;

@TestConfiguration
public class MongoDBConfig {

    static final MongoDBContainer mongoDBContainer = new MongoDBContainer("mongo:5.0");

    static {
        mongoDBContainer.start();
        System.setProperty("spring.data.mongodb.uri", mongoDBContainer.getReplicaSetUrl());
    }

}
