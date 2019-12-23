package com.jeffy.dundun.cloud.dao.module;

import lombok.Data;

import java.util.Date;

@Data
public class Notebook {
    private Integer id;  //id
    private String title;  //账号
    private String tag;  //密码
    private Integer userId; //手机号码
    private String content;  //邮箱
    private Date createDate; //创建时间
    private Date updateDate; //更新时间
}
