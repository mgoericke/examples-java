package de.javamark.springboot.rabbitmq.service;

import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.BindingBuilder;
import org.springframework.amqp.core.Queue;
import org.springframework.amqp.core.TopicExchange;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;

@Configuration
@Profile("consumer")
public class EventConsumerConfiguration {

    private final String topicExchangeName;
    private final String queueName;
    private final String routingKey;

    public EventConsumerConfiguration(@Value("${exchange.name}") String topicExchangeName,
                                      @Value("${consumer.queue}") String queueName,
                                      @Value("${consumer.routing-key}") String routingKey) {
        this.topicExchangeName = topicExchangeName;
        this.queueName = queueName;
        this.routingKey = routingKey;
    }

    @Bean
    public EventConsumerService eventConsumerService() {
        return new EventConsumerService(routingKey);
    }

    @Bean
    public TopicExchange eventExchange() {
        return new TopicExchange(this.topicExchangeName);
    }

    @Bean
    public Queue queue() {
        return new Queue(this.queueName);
    }

    @Bean
    public Binding binding(Queue queue, TopicExchange eventExchange) {
        return BindingBuilder
                .bind(queue)
                .to(eventExchange)
                .with(this.routingKey);
    }
}
