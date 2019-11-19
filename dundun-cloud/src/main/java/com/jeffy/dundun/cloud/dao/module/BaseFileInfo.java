package com.jeffy.dundun.cloud.dao.module;

import java.util.Date;

/**
 * 基本文件信息
 */
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


    public String getFileId() {
        return fileId;
    }

    public void setFileId(String fileId) {
        this.fileId = fileId;
    }

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public String getFileHash() {
        return fileHash;
    }

    public void setFileHash(String fileHash) {
        this.fileHash = fileHash;
    }

    public String getFolderId() {
        return folderId;
    }

    public void setFolderId(String folderId) {
        this.folderId = folderId;
    }

    public String getFileName() {
        return fileName;
    }

    public void setFileName(String fileName) {
        this.fileName = fileName;
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

    public Integer getFileStatus() {
        return fileStatus;
    }

    public void setFileStatus(Integer fileStatus) {
        this.fileStatus = fileStatus;
    }

    public String getFileType() {
        return fileType;
    }

    public void setFileType(String fileType) {
        this.fileType = fileType;
    }

    public Integer getFileSize() {
        return fileSize;
    }

    public void setFileSize(Integer fileSize) {
        this.fileSize = fileSize;
    }

    public String getSavePath() {
        return savePath;
    }

    public void setSavePath(String savePath) {
        this.savePath = savePath;
    }

    @Override
    public String toString() {
        return "BaseFileInfo{" +
                "fileId='" + fileId + '\'' +
                ", userId=" + userId +
                ", fileHash='" + fileHash + '\'' +
                ", folderId='" + folderId + '\'' +
                ", fileSize=" + fileSize +
                ", fileName='" + fileName + '\'' +
                ", fileStatus=" + fileStatus +
                ", fileType='" + fileType + '\'' +
                ", createDate=" + createDate +
                ", updateDate=" + updateDate +
                ", savePath='" + savePath + '\'' +
                '}';
    }
}
