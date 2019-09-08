package de.javamark.springboot.rabbitmq.service;

import de.javamark.springboot.rabbitmq.model.Event;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.factory.annotation.Value;

public class EventConsumerService {
    private Logger logger = LoggerFactory.getLogger(EventConsumerService.class);

    private final String routingKey;

    public EventConsumerService(@Value("${consumer.routing-key}") String routingKey) {
        this.routingKey = routingKey;
    }

    @RabbitListener(queues = "${consumer.queue}")
    public void receive(Event event) {
        logger.info("[*] Message received: {}:{}", event.getId(), routingKey);
    }
}
