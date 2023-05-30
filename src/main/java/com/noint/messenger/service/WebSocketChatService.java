package com.noint.messenger.service;

import jakarta.websocket.OnClose;
import jakarta.websocket.OnMessage;
import jakarta.websocket.OnOpen;
import jakarta.websocket.Session;
import jakarta.websocket.server.ServerEndpoint;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.Collections;
import java.util.HashSet;
import java.util.Set;

@Service
@ServerEndpoint(value = "/socket/chat")
public class WebSocketChatService {
    @Autowired
    private RabbitTemplate rabbitTemplate;
//    @Autowired
//    private RabbitQueue rabbitQueue;
    private static Set<Session> clients = Collections.synchronizedSet(new HashSet<Session>());

    @OnMessage
    public void onMessage(String msg, Session session) throws IOException {
        System.out.println("receive message : " + msg);
        for(Session s : clients) {
            System.out.println("send data : " + msg);
            s.getBasicRemote().sendText(msg);
        }
    }

    @OnOpen
    public void onOpen(Session s) {
        System.out.println("open session : " + s.toString());
        if(!clients.contains(s)) {
            clients.add(s);
            System.out.println("session open : " + s);
        }else {
            System.out.println("이미 연결된 session 임!!!");
        }
    }

    @OnClose
    public void onClose(Session s) {
        System.out.println("session close : " + s);
        clients.remove(s);
    }
}
