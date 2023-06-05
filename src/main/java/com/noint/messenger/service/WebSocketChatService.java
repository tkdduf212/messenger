package com.noint.messenger.service;

import com.google.gson.Gson;
import com.noint.messenger.config.SpringContext;
import com.noint.messenger.entity.Message;
import com.noint.messenger.mq.Rabbit;
import jakarta.websocket.OnClose;
import jakarta.websocket.OnMessage;
import jakarta.websocket.OnOpen;
import jakarta.websocket.Session;
import jakarta.websocket.server.PathParam;
import jakarta.websocket.server.ServerEndpoint;
import org.springframework.stereotype.Service;

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
    public void onMessage(String msg, Session session, @PathParam("name") String name) throws IOException {
//        System.out.println("receive message : " + msg);
//        for(Session s : clients) {
//            System.out.println("send data : " + msg);
//            s.getBasicRemote().sendText(msg);
//        }
        Message.MessageRequestForm form = new Gson().fromJson(msg, Message.MessageRequestForm.class);
        if (form.getTargets().size() > 1) {
            rabbit.publish(form.getTargets(), form.getMsg(), name);
        }else {
            rabbit.publish(form.getTargets().get(0), form.getMsg(), name);
        }
    }

    @OnOpen
    public void onOpen(Session s, @PathParam("name") String name) {
        System.out.println("open session : " + s.toString());
        if(!clients.contains(s)) {
            clients.add(s);
            rabbit.readyToConsumeMsg(name, s);
            System.out.println("session open : " + s);
        }else {
            System.out.println("이미 연결된 session 임!!!");
        }
    }

    @OnClose
    public void onClose(Session s, @PathParam("name") String name) {
        System.out.println("session close : " + s);
        clients.remove(s);
        rabbit.queueAndChannelDelete(name);
    }
}
