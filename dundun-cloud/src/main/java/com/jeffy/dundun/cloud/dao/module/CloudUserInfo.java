package com.jeffy.dundun.cloud.dao.module;

import java.util.Date;

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

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getAccout() {
        return accout;
    }

    public void setAccout(String accout) {
        this.accout = accout;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Integer getPhoneNum() {
        return phoneNum;
    }

    public void setPhoneNum(Integer phoneNum) {
        this.phoneNum = phoneNum;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Integer getTotalSpace() {
        return totalSpace;
    }

    public void setTotalSpace(Integer totalSpace) {
        this.totalSpace = totalSpace;
    }

    public Integer getFreeSpace() {
        return freeSpace;
    }

    public void setFreeSpace(Integer freeSpace) {
        this.freeSpace = freeSpace;
    }

    public Integer getUserLevel() {
        return userLevel;
    }

    public void setUserLevel(Integer userLevel) {
        this.userLevel = userLevel;
    }

    public Date getCreateDate() {
        return createDate;
    }

    public void setCreateDate(Date createDate) {
        this.createDate = createDate;
    }

    public Date getUpdateDate() {
        return updateDate;
    }

    public void setUpdateDate(Date updateDate) {
        this.updateDate = updateDate;
    }

    @Override
    public String toString() {
        return "CloudUserInfo{" +
                "id=" + id +
                ", accout='" + accout + '\'' +
                ", password='" + password + '\'' +
                ", phoneNum=" + phoneNum +
                ", email='" + email + '\'' +
                ", totalSpace='" + totalSpace + '\'' +
                ", freeSpace='" + freeSpace + '\'' +
                ", userLevel=" + userLevel +
                ", createDate=" + createDate +
                ", updateDate=" + updateDate +
                '}';
    }
}
