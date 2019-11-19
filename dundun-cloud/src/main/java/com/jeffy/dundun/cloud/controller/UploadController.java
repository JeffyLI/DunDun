package com.jeffy.dundun.cloud.controller;

import com.jeffy.dundun.cloud.common.BaseRespond;
import com.jeffy.dundun.cloud.dao.module.BaseFileInfo;
import com.jeffy.dundun.cloud.service.BaseFileInfoService;
import com.jeffy.dundun.cloud.service.BaseFileUtils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.io.File;
import java.io.InputStream;


@RestController
@RequestMapping("upload")
public class UploadController {
    @Resource
    private BaseFileInfoService baseFileInfoService;

    @Resource
    private BaseFileUtils baseFileUtils;

    @Value("${value.save-file-path}")
    private String saveFilePath;

    private Logger log= LogManager.getLogger(this.getClass());

    /**
     * 接口：
     * 1.创建文件（检查服务器存储空间，获取文件信息，校验秒传，分配id）
     * 2.上传文件（大文件上传和小文件上传）
     */

    /**
     * 1.获取文件信息（文件大小，文件md5，用户id，创建目录，生成fileId）
     * 2.检查文件是否已上传过（如果是，则直接创建文件记录，即秒传）
     * 3.检查用户存储空间（预留空间，如果上传失败，则返回空间）
     * 4.检查机器存储空间（这一步坐在创建用户空间那里吧）
     */
    @RequestMapping("createFile")
    public Object createFile(HttpServletRequest request,BaseFileInfo baseFileInfo){
        return "";
    }

    @RequestMapping("testUploadFile")
    public String testUploadFile(MultipartFile file, HttpServletRequest request){

        try{
            InputStream inputStream=file.getInputStream();

            byte[] temp=new byte[64*1024];
            int total=0;
            int len=0;
            while((len=inputStream.read(temp,0,temp.length))!=-1){
                total+=len;
                log.info("total:"+total+",len:"+len);
            }
            inputStream.close();
        }catch (Exception e){
            log.warn("Exception:",e);
            return "Exception";
        }

        return "success";
    }

    @RequestMapping("uploadSmallFile")
    public Object uploadSmallFile(MultipartFile file, HttpServletRequest request,BaseFileInfo baseFileInfo){
        /**
         * 1.校验请求信息
         * 2.获取输入流
         * 3.保存文件
         * 4.校验文件MD5
         * 5.创建文件记录
         */

        BaseRespond baseRespond=new BaseRespond();
        baseFileInfo.setFileName(file.getOriginalFilename());

        /**
         * 检查文件目录是否存在
         * chenkFolderExist(String folderId)
         */

        /**
         * 查用户云盘信息表，检查用户剩余存储空间
         * 若用户空间不足，则返回状态码：401，msg="InsufficientStorageSpace"
         * checkUserStorageSpace（Integer userId）
         */

        //文件重名问题

        //判断文件是否已存在
        int status= baseFileUtils.checkFileExist(baseFileInfo);
        if(status==2){
            baseRespond.setMsg("CheckFileExist Exception");
            baseRespond.setCode(400);
            return baseRespond;
        }else if(status==1){
            baseRespond.setMsg("FileExist");
            baseRespond.setCode(201);
            return baseRespond;
        }

        String destDir=saveFilePath+baseFileInfo.getFileHash()+File.separator;
        File saveFile=new File(destDir+baseFileInfo.getFileHash());
        //创建文件目录和文件
        baseRespond.setMsg(baseFileUtils.createPhyFile(saveFile));
        if(!"SUCCESS".equals(baseRespond.getMsg())){
            baseRespond.setCode(400);
            return baseRespond;
        }

        try {

            file.transferTo(saveFile);

            String checkMsg= baseFileUtils.checkFileComplete(saveFile,baseFileInfo.getFileSize(),baseFileInfo.getFileHash());
            if(!checkMsg.equals("SUCCESS")){
                //校验不通过，删除创建的文件和目录
                boolean deleteFile=saveFile.delete();
                log.info("Delete File :"+deleteFile);
                baseRespond.setMsg(checkMsg);
                baseRespond.setCode(400);
                return baseRespond;
            }

            baseFileInfo.setSavePath(destDir);
            baseFileInfo.setFileId(String.valueOf(System.currentTimeMillis())+String.valueOf(baseFileInfo.getUserId()));
            String msg=baseFileInfoService.createBaseFileInfo(baseFileInfo);
            log.info("Upload File Success:"+baseFileInfo);
        }catch (Exception e){
            if(saveFile.exists()){
                boolean deleteFile=saveFile.delete();
                log.info("Delete File :"+deleteFile);
            }

            log.warn("Save File Exception:",e);
            baseRespond.setMsg("Save File Exception");
            baseRespond.setCode(400);
            return baseRespond;
        }

        baseRespond.setMsg("SUCCESS");
        baseRespond.setCode(200);
        return baseRespond;
    }

}
