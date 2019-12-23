package com.jeffy.dundun.cloud.service;

import com.jeffy.dundun.cloud.common.Validate;
import com.jeffy.dundun.cloud.dao.module.BaseFileInfo;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.stereotype.Service;
import org.springframework.util.DigestUtils;

import javax.annotation.Resource;
import java.io.File;
import java.io.FileInputStream;
import java.util.List;

/**
 * 文件保存服务
 */
@Service("baseFileUtils")
public class BaseFileUtils {

    @Resource
    private BaseFileInfoService baseFileInfoService;

    private Logger log= LogManager.getLogger(this.getClass());

    /**
     * 判断文件是否存在
     * 若是文件已上传过，直接写入用户文件列表，然后返回：1
     * 如果不存在，则返回2
     * @param baseFileInfo 文件信息
     * @return 0:文件不存在，1：文件已存在，2：异常
     */
    public int checkFileExist(BaseFileInfo baseFileInfo){
        if(!Validate.paraNullValidate(baseFileInfo.getFileHash(),baseFileInfo.getFileName(),baseFileInfo.getUserId(),baseFileInfo.getFolderId())){
            log.warn("checkFileExist - Exist null params:"+baseFileInfo);
            return 2;
        }

        List<BaseFileInfo> baseFileInfos=baseFileInfoService.selectByFileHash(baseFileInfo.getFileHash());
        if(null==baseFileInfos){
            return 2;
        }

        //文件已经被上传过
        if(baseFileInfos.size()>0) {
            BaseFileInfo tempBaseFileInfo=baseFileInfos.get(0);
            baseFileInfo.setSavePath(tempBaseFileInfo.getSavePath());
            baseFileInfo.setFileId(String.valueOf(System.currentTimeMillis())+String.valueOf(baseFileInfo.getUserId()));
            String msg=baseFileInfoService.createBaseFileInfo(baseFileInfo);

            //同时减少用户存储空间 ------
            if("SUCCESS".equals(msg)){
                return 1;
            }

            return 2;

        }

        return 0;
    }

    /**
     * 检查获取文件的大小和hash值
     * @param saveFile 接收的文件
     * @param fileSize 实际文件大小
     * @param fileHash 实际文件Hash
     * @return 检查信息
     */
    public String checkFileComplete(File saveFile, int fileSize, String fileHash){
        String msg="SUCCESS";
        if(fileSize!=saveFile.length()){
            log.warn("UploadFileSize != RealFileSize:uploadFileSize="+fileSize+" ,realFileSize="+saveFile.length());
            msg="Save File Exception:FileSize not equals";
            return msg;
        }

        try {
            String saveFileMD5 = DigestUtils.md5DigestAsHex(new FileInputStream(saveFile));
            if (!fileHash.equals(saveFileMD5)) {
                log.warn("Upload FileHash != Real FileHash:" + fileHash + " , saveFileMD5=" + saveFileMD5);
                msg = "Save File Exception:FileHash not equals.";
                return msg;
            }
        }catch (Exception e){
            log.warn("md5DigestAsHex Exception:" + fileHash + " , saveFileMD5=" + fileHash);
        }

        return msg;
    }

    /**
     * 创建物理文件目录和文件
     * @param saveFile
     * @return
     */
    public String createPhyFile(File saveFile){
        try{
            if(!saveFile.getParentFile().exists()){
                boolean createDir=saveFile.getParentFile().mkdirs();
                if(!createDir){
                    return "Create File Dir False.";
                }
            }
        }catch (Exception e){
            log.warn("Create File Dir Exception:",e);
            return "Create File Dir Exception";
        }

        return "SUCCESS";
    }
}
