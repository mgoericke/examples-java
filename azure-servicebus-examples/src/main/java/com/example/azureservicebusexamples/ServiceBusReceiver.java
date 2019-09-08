package com.example.azureservicebusexamples;

import com.microsoft.azure.servicebus.IQueueClient;
import com.microsoft.azure.servicebus.MessageHandlerOptions;
import com.microsoft.azure.servicebus.primitives.ServiceBusException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.time.Duration;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

@Component
@Slf4j
public class ServiceBusReceiver {

    private final IQueueClient receiverClient;
    private final ServiceBusHandler serviceBusHandler;

    public ServiceBusReceiver(final IQueueClient receiverClient) {
        this.receiverClient = receiverClient;
        this.serviceBusHandler = new ServiceBusHandler(this.receiverClient);
    }


    public void receiveMessagesAsync() throws ServiceBusException, InterruptedException {
        final ExecutorService executorService = Executors.newSingleThreadExecutor();
        final MessageHandlerOptions messageHandlerOptions = new MessageHandlerOptions(1, true, Duration.ofMinutes(1));
        this.receiverClient.registerMessageHandler(
                new ServiceBusHandler(this.receiverClient),
                messageHandlerOptions,
                executorService);
    }

    public String lastReceivedLabel() {
        return this.serviceBusHandler.lastReceivedLabel;
    }
}
