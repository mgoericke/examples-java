package de.javamark.springboot.rabbitmq.service;

import org.springframework.amqp.core.TopicExchange;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;
import org.springframework.scheduling.annotation.EnableScheduling;

@Profile("producer")
@Configuration
@EnableScheduling
public class EventProducerConfiguration {

    private final String topicExchangeName;

    public EventProducerConfiguration(@Value("${exchange.name}") String topicExchangeName) {
        this.topicExchangeName = topicExchangeName;
    }

    @Bean
    public EventProducerService eventProducerService(RabbitTemplate rabbitTemplate, TopicExchange topicExchange) {
        return new EventProducerService(rabbitTemplate, topicExchange);
    }

    @Bean
    public TopicExchange eventExchange() {
        return new TopicExchange(this.topicExchangeName);
    }
}
