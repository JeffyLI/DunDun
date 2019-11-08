package com.jeffy.dundun.design.controller;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("test")
public class TestController {

    private Logger log= LogManager.getLogger(this.getClass());

    @RequestMapping("hello")
    public String hello(){
        log.info("Hello World!");
        return "Hello World!";
    }
}
