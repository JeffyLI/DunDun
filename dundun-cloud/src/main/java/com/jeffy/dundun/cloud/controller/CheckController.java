package com.jeffy.dundun.cloud.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class CheckController {
    @RequestMapping("index")
    public String index(){
        return "index";
    }

    @ResponseBody
    @RequestMapping("check")
    public String check(){
        return "(✪ω✪)(✪ω✪)(✪ω✪)(✪ω✪)";
    }
}
