package com.noint.messenger.controller;

import com.noint.messenger.mq.RabbitQueue;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

@RestController
@RequestMapping("/chat")
public class WebSocketController {
    @Autowired
    private RabbitQueue rabbitQueue;
    @GetMapping
    public ModelAndView chatMain(ModelAndView modelAndView){
        modelAndView.setViewName("chat/chatting");
        return modelAndView;
    }

    @GetMapping("/test/{id}")
    public void test(@PathVariable String id) {
        rabbitQueue.addNewQueue(id, "test-add-Q", id);
    }
}
