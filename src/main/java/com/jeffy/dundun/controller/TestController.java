package com.jeffy.dundun.controller;

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
/*
开发文档
1.我的网站要做什么？
目前受欢迎的网站都是资讯网站。
但是我的网站连域名都没有。。。。我的网站要做出去吗？
如果不做出去，我就应该做一个自己感到有趣的网站或者是功能性很强的网站
如果要做出去，我倒是可以做资讯类的或者是社交类的。但是我没有咨询信息啊，社交类的话，要做到多特别才有人访问啊 。

2.目前有什么想法吗？
（1）树洞：所有发送上去的信息只保留短暂的时间，可以匿名，可以设置仅自己可见。
        别人可以在下面评论，一样时间已过就消失不见（或者评论数超过一定数量）。
        展现的形式就想弹幕一样，在屏幕上流动
        话题争辩：一定时间内，票数多的获胜，可以转投阵营，投票功能（可以被申诉，申诉人数到达一定数量即可重新开启讨论）
        表白墙：永恒，写在上面的就无法被删除。（除非被举报）
        展现的顺序？随机，权重？
        网页端，手机端
        万一在表白墙发布咒骂言论怎么办？


* */