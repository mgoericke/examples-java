package com.example.azureservicebusexamples;

import com.microsoft.azure.servicebus.primitives.ServiceBusException;
import lombok.extern.slf4j.Slf4j;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.concurrent.TimeUnit;

import static org.awaitility.Awaitility.await;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.is;

@RunWith(SpringRunner.class)
@SpringBootTest
@Slf4j
public class QueueTest {

	@Autowired
	private ServiceBusSender serviceBusSender;

	@Autowired
	private ServiceBusReceiver serviceBusReceiver;

	@Test
	public void testSendMessage() throws ServiceBusException, InterruptedException {
		this.serviceBusReceiver.receiveMessagesAsync();
		this.serviceBusSender.sendMessageAsync("Hallo Welt");

		await().atMost(5, TimeUnit.SECONDS)
				.until(() -> this.serviceBusReceiver.lastReceivedLabel()!=null);

		assertThat(this.serviceBusReceiver.lastReceivedLabel(), is(equalTo("ServiceBusSender")));

	}
}