package com.example.azureservicebusexamples;

import com.microsoft.azure.servicebus.IQueueClient;
import com.microsoft.azure.servicebus.QueueClient;
import com.microsoft.azure.servicebus.ReceiveMode;
import com.microsoft.azure.servicebus.primitives.ConnectionStringBuilder;
import com.microsoft.azure.servicebus.primitives.ServiceBusException;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.time.Duration;

@Configuration
public class ServiceBusConfig {
    @Value("${azure.servicebus.connection-string}")
    private String connectionString;

    @Value("${azure.servicebus.entity-path}")
    private String entityPath;

    @Bean
    public IQueueClient senderClient() throws ServiceBusException, InterruptedException {
        final ConnectionStringBuilder connectionStringBuilder = new ConnectionStringBuilder(this.connectionString , this.entityPath);
        return new QueueClient(connectionStringBuilder, ReceiveMode.PEEKLOCK);
    }
    @Bean
    public IQueueClient receiverClient() throws ServiceBusException, InterruptedException {
        final ConnectionStringBuilder connectionStringBuilder = new ConnectionStringBuilder(this.connectionString , this.entityPath);
        connectionStringBuilder.setOperationTimeout(Duration.ofSeconds(5));
        return new QueueClient(connectionStringBuilder, ReceiveMode.PEEKLOCK);
    }
}
