package de.javamark.springboot.rabbitmq.model;

import java.io.Serializable;
import java.util.UUID;

public class Event implements Serializable {
    private String id;
    private String message;

    public Event(String message) {
        this.id = UUID.randomUUID().toString();
        this.message = message;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
