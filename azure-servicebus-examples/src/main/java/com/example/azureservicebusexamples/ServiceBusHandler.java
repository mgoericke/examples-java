package com.example.azureservicebusexamples;

import com.microsoft.azure.servicebus.ExceptionPhase;
import com.microsoft.azure.servicebus.IMessage;
import com.microsoft.azure.servicebus.IMessageHandler;
import com.microsoft.azure.servicebus.IQueueClient;
import lombok.extern.slf4j.Slf4j;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.concurrent.CompletableFuture;

@Slf4j
public class ServiceBusHandler implements IMessageHandler {

    private final IQueueClient client;

    public static String lastReceivedLabel;
    public static List<IMessage> receivedMessages = Collections.synchronizedList(new ArrayList<>(1));

    public ServiceBusHandler(final IQueueClient client) {
        this.client = client;
    }

    @Override
    public CompletableFuture<Void> onMessageAsync(final IMessage receivedMessage) {
        if(receivedMessage.getLabel()!=null) {
            log.info("[+] message received {}", receivedMessage.getLabel());
            lastReceivedLabel = receivedMessage.getLabel();
            receivedMessages.add(receivedMessage);
        }
        return CompletableFuture.completedFuture(null);
    }

    @Override
    public void notifyException(final Throwable throwable, final ExceptionPhase exceptionPhase) {
        log.error(throwable.getLocalizedMessage());
    }
}