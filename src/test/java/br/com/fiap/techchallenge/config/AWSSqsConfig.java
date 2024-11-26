package br.com.fiap.techchallenge.config;

import com.amazonaws.services.sqs.AmazonSQS;
import io.awspring.cloud.sqs.operations.SqsTemplate;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Primary;
import org.springframework.test.context.ActiveProfiles;

import static org.mockito.Mockito.mock;

@ActiveProfiles("test")
public class AWSSqsConfig {

    @Bean
    @Primary
    public AmazonSQS amazonSQS() {
        return mock(AmazonSQS.class);
    }

    @Bean
    @Primary
    public SqsTemplate sqsTemplate(AmazonSQS amazonSQS) {
        return mock(SqsTemplate.class);
    }

}
