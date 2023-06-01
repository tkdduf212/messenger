package com.noint.messenger.controller;

import com.noint.messenger.service.WebSocketChatService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.util.Map;

@RestController
@RequestMapping("/chat")
@RequiredArgsConstructor
public class WebSocketController {
    private final WebSocketChatService chatService;

    @GetMapping
    public ModelAndView chatMain(ModelAndView modelAndView){
        modelAndView.setViewName("chat/chatting");
        return modelAndView;
    }

    @PostMapping("/login")
    public ResponseEntity login(@RequestBody Map<String, String> request) {
        chatService.Login(request.get("name"));
        return ResponseEntity.ok().build();
    }
}
