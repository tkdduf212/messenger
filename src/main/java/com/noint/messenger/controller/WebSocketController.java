package com.noint.messenger.controller;

import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.DeliverCallback;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import java.io.IOException;
import java.nio.charset.StandardCharsets;

@RestController
@RequestMapping("/chat")
public class WebSocketController {
//    @Autowired
//    private RabbitQueue rabbitQueue;
    @Autowired
    private Connection connection;
    @GetMapping
    public ModelAndView chatMain(ModelAndView modelAndView){
        modelAndView.setViewName("chat/chatting");
        return modelAndView;
    }

    @GetMapping("/test/{id}")
    public void test(@PathVariable String id) {
//        rabbitQueue.addNewQueue(id, "test-add-Q", id);
    }

    @GetMapping("/test/send")
    public void sendTest() {
        try {
            Channel channel = connection.createChannel();
            channel.queueDeclare("javaQ", false, false, false, null);
            channel.basicPublish("", "javaQ", null, "Hello World!".getBytes());
            System.out.println("send OK");
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    @GetMapping("/test/recv")
    public void recvTest() {
        try {
            Channel channel = connection.createChannel();
            channel.queueDeclare("javaQ", false, false, false, null);
            System.out.println("rece watiing");
            DeliverCallback deliverCallback = (consumerTag, delivery) ->{
                String message = new String(delivery.getBody(), StandardCharsets.UTF_8);
                System.out.println("recve : " + message);
            };
            channel.basicConsume("javaQ", true, deliverCallback, consumerTag -> {});
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
