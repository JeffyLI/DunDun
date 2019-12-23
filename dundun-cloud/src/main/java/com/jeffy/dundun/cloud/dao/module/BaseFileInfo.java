package com.jeffy.dundun.cloud.dao.module;

import lombok.Data;

import java.util.Date;

/**
 * 基本文件信息
 */
@Data
public class BaseFileInfo {
    //文件id（唯一)
    private String fileId;

    //上传用户id
    private Integer userId;

    //文件Hash值
    private String fileHash;

    //所在目录id
    private String folderId;

    //文件大小
    private Integer fileSize;

    //文件名
    private String fileName;

    //文件状态
    private Integer fileStatus;

    //文件类型
    private String fileType;

    //创建时间
    private Date createDate;

    //更新时间
    private Date updateDate;

    //文件保存路径
    private String savePath;

}
