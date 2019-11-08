package com.jeffy.dundun.design.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("message")
public class MessageController {
    @RequestMapping("garbage")
    public String garbagePage(){
        return "/message/garbage";
    }
}
