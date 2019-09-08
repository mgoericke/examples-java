package com.example.azureservicebusexamples;

import com.microsoft.azure.servicebus.IMessage;
import com.microsoft.azure.servicebus.IQueueClient;
import com.microsoft.azure.servicebus.Message;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
@Slf4j
public class ServiceBusSender {

    private final IQueueClient senderClient;

    public void sendMessageAsync(final String detail) {
        final IMessage message = new Message(detail);
        message.setLabel(this.getClass().getSimpleName());
        this.senderClient.sendAsync(message)
                .thenRunAsync(() -> log.info("[+] message sent {}", message.getMessageId()));
    }
}
