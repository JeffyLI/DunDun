package com.jeffy.dundun.cloud.dao.module;

import lombok.Data;

import java.util.Date;

@Data
public class CloudUserInfo {

    private Integer id;  //id
    private String accout;  //账号
    private String password;  //密码
    private Integer phoneNum; //手机号码
    private String email;  //邮箱
    private Integer totalSpace;  // 总空间
    private Integer freeSpace;  //剩余空间
    private Integer userLevel; //会员等级
    private Date createDate; //创建时间
    private Date updateDate; //更新时间

}
