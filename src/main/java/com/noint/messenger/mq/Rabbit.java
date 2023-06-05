package com.noint.messenger.mq;

import com.google.gson.Gson;
import com.noint.messenger.entity.Message;
import com.rabbitmq.client.BuiltinExchangeType;
import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.DeliverCallback;
import jakarta.websocket.Session;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.lang.reflect.Type;
import java.nio.charset.StandardCharsets;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.TimeoutException;

@Component
@RequiredArgsConstructor
public class Rabbit {
    private final Connection connection;
    private final Map<String, Channel> channelStorage = new HashMap<>();

    public void ReceiveQueueRegister(String queueName) {
        try {
            Channel channel = this.createChannel(queueName);
            channel.exchangeDeclare("javaEx", BuiltinExchangeType.DIRECT);
            channel.queueDeclare(queueName, false, false, false, null);
            channel.queueBind(queueName, "javaEx", queueName);
            System.out.println(queueName + " recv watiing");
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public void readyToConsumeMsg(String name, Session session) {
        System.out.println(name + " 컨슘 준비 완");
        Channel channel = channelStorage.get(name);
        DeliverCallback deliverCallback = (consumerTag, delivery) -> {
            String message = new String(delivery.getBody(), StandardCharsets.UTF_8);
            session.getBasicRemote().sendText(message);
            System.out.println(name + " recve : " + message);
        };
        try {
            channel.basicConsume(name, true, deliverCallback, consumerTag -> {
            });
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public void publish(String targetQueueName, String msg, String sender) {
        try {
            Channel channel = channelStorage.get(sender);
            channel.basicPublish("javaEx", targetQueueName, null, msg.getBytes());
            System.out.println("send OK");
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public void publish(List<String> targetQueueNames, String msg, String sender) {
        Channel channel = channelStorage.get(sender);
        try {
            for (String target : targetQueueNames) {
                channel.basicPublish("javaEx", target, null, msg.getBytes());
                System.out.println("send OK");
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public void queueAndChannelDelete(String name) {
        Channel channel = channelStorage.get(name);
        try {
            channel.queueDelete(name);
            channel.close();
        } catch (IOException e) {
            throw new RuntimeException(e);
        } catch (TimeoutException e) {
            throw new RuntimeException(e);
        }
    }

    private Channel createChannel(String userName) {
        Channel channel;
        try {
            channel = connection.createChannel();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        channelStorage.put(userName, channel);
        return channel;
    }
}
