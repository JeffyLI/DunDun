package com.jeffy.dundun.cloud.dao.module;

import lombok.Data;

@Data
public class CloudFile {
    //文件MD5（主键）
    private String md5;
    //文件ID
    private String fileId;
    //文件桶名
    private Integer containerId;
    //文件资源池ID
    private String objectId;
    //文件名称
    private String fileName;
    //文件大小
    private Long fileSize;
    //文件下载地址
    private String durl;
}
