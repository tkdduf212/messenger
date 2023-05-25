package com.noint.messenger;

import com.noint.messenger.mq.receiver.RabbitmqReceiver;
import lombok.RequiredArgsConstructor;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.util.concurrent.TimeUnit;

@Component
@RequiredArgsConstructor
public class Runner implements CommandLineRunner {
    private final RabbitTemplate rabbitTemplate;
    private final RabbitmqReceiver receiver;

    @Override
    public void run(String... args) throws Exception {
        System.out.println("Sending message");
        rabbitTemplate.convertAndSend("test-EX", "foo.bar.baz", "Hello form RabbitMQ!");
        receiver.getLatch().await(10000, TimeUnit.MILLISECONDS);
    }
}
