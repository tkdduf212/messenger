package com.noint.messenger.controller;

import com.noint.messenger.entity.Member;
import com.noint.messenger.service.GenericService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

@RestController
@RequiredArgsConstructor
public class GenericController {
    private final GenericService genericService;

    @GetMapping("/")
    public ModelAndView main(ModelAndView modelAndView) {
        modelAndView.setViewName("index");
        return modelAndView;
    }

    @PostMapping("/login")
    public ModelAndView login(@RequestParam String name) {
        Member m = genericService.login(name);
        return new ModelAndView("redirect:/chat/"+m.getSeq());
    }
}
