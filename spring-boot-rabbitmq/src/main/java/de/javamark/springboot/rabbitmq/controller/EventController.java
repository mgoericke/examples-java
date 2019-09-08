package de.javamark.springboot.rabbitmq.controller;

import de.javamark.springboot.rabbitmq.service.EventProducerService;
import org.springframework.context.annotation.Profile;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

@Profile("producer")
@RestController
public class EventController {

    private final EventProducerService eventProducerService;

    public EventController(EventProducerService eventProducerService) {
        this.eventProducerService = eventProducerService;
    }


    @PostMapping("/events")
    public String addEvent() {
        eventProducerService.createEvent();
        return "Event created";
    }
}
