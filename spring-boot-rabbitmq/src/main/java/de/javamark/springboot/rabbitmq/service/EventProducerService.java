package de.javamark.springboot.rabbitmq.service;

import de.javamark.springboot.rabbitmq.model.Event;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.core.TopicExchange;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.scheduling.annotation.Scheduled;

import java.util.*;


public class EventProducerService {

    private static final Logger logger = LoggerFactory.getLogger(EventProducerService.class);
    private static final List<String> ROUTING_KEYS = Arrays.asList(
            "resource.created",
            "resource.edited",
            "resource.deleted",
            "otherevent"
    );
    private Random random = new Random();

    private final RabbitTemplate rabbitTemplate;
    private final TopicExchange topicExchange;

    private int eventCounter = 0;


    public EventProducerService(RabbitTemplate rabbitTemplate, TopicExchange topicExchange) {
        this.rabbitTemplate = rabbitTemplate;
        this.topicExchange = topicExchange;

        Map map = new HashMap();
    }

    @Scheduled(fixedDelay = 2000, initialDelay = 1500)
    public void createEvent() {
        String routingKey = randomRoutingKey();
        String message = "a new event has been created " + eventCounter++;
        Event event = new Event(message);
        rabbitTemplate.convertAndSend(topicExchange.getName(), routingKey, event);
        logger.info("[+] Message sent: {}:{}", event.getId(), routingKey);
    }

    private String randomRoutingKey() {
        return ROUTING_KEYS.get(random.nextInt(ROUTING_KEYS.size()));
    }
}
