package com.noint.messenger.service;

import com.google.gson.Gson;
import com.noint.messenger.entity.Message;
import com.noint.messenger.mq.Rabbit;
import jakarta.websocket.OnClose;
import jakarta.websocket.OnMessage;
import jakarta.websocket.OnOpen;
import jakarta.websocket.Session;
import jakarta.websocket.server.ServerEndpoint;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestParam;

import java.io.IOException;
import java.util.Collections;
import java.util.HashSet;
import java.util.Set;

@Service
@ServerEndpoint(value = "/socket/chat/{name}")
public class WebSocketChatService {
    private final Rabbit rabbit;
    private static Set<Session> clients = Collections.synchronizedSet(new HashSet<Session>());

    public WebSocketChatService() {
        this.rabbit = (Rabbit) SpringContext.getApplicationContext().getBean("rabbit");
    }

    public void Login(String name) {
        rabbit.ReceiveQueueRegister(name);
    }

    @OnMessage
    public void onMessage(String msg, Session session) throws IOException {
//        System.out.println("receive message : " + msg);
//        for(Session s : clients) {
//            System.out.println("send data : " + msg);
//            s.getBasicRemote().sendText(msg);
//        }
        Message.MessageRequestForm form = new Gson().fromJson(msg, Message.MessageRequestForm.class);
        if (form.getUsers().size() > 1) {
            rabbit.publish(form.getUsers(), form.getMsg());
        }else {
            rabbit.publish(form.getUsers().get(0), form.getMsg());
        }
    }

    @OnOpen
    public void onOpen(Session s) {
        System.out.println("open session : " + s.toString());
        if(!clients.contains(s)) {
            clients.add(s);
//            rabbit.readyToConsumeMsg(name, s);
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
