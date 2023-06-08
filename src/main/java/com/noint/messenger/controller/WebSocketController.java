package com.noint.messenger.controller;

import com.noint.messenger.service.WebSocketChatService;
import com.noint.messenger.service.WebSocketDataService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

@RestController
@RequestMapping("/chat")
@RequiredArgsConstructor
public class WebSocketController {
    private final WebSocketChatService chatService;
    private final WebSocketDataService dataService;

    @GetMapping("/{seq}")
    public ModelAndView chatMain(ModelAndView modelAndView, @PathVariable Long seq){
        modelAndView.addObject("data", dataService.getData(seq));
        modelAndView.setViewName("chat/chatting");
        return modelAndView;
    }
}
