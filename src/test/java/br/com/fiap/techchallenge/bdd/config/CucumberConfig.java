package br.com.fiap.techchallenge.bdd.config;

import br.com.fiap.techchallenge.config.AWSSqsConfig;
import br.com.fiap.techchallenge.config.MongoDBConfig;
import io.cucumber.spring.CucumberContextConfiguration;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.Import;
import org.springframework.test.context.ActiveProfiles;

@ActiveProfiles("test")
@CucumberContextConfiguration
@Import({MongoDBConfig.class, AWSSqsConfig.class})
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class CucumberConfig {
}
