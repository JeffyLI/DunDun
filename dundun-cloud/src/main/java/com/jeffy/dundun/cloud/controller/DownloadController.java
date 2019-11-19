package com.jeffy.dundun.cloud.controller;

import com.jeffy.dundun.cloud.dao.module.BaseFileInfo;
import com.jeffy.dundun.cloud.service.BaseFileInfoService;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.*;

@RestController
@RequestMapping("download")
public class DownloadController {
    @Resource
    private BaseFileInfoService baseFileInfoService;

    private Logger log= LogManager.getLogger(this.getClass());

    @RequestMapping("downloadFile")
    public String downloadFile(HttpServletRequest request,
                               @RequestParam(required = true) String fileId,
                               HttpServletResponse response){
        /**
         * 1.合法性校验（签名校验，权限校验）
         * 2.下载
         */

        BaseFileInfo baseFileInfo=baseFileInfoService.selectById(fileId);
        if(baseFileInfo==null){
            return "文件不存在";
        }

        String filePath=baseFileInfo.getSavePath()+baseFileInfo.getFileHash();

        if (baseFileInfo.getFileName() != null) {
            //设置文件路径
            File file = new File(filePath);
            if (file.exists()) {
                response.setContentType("application/force-download");// 设置强制下载不打开
                response.setContentType("multipart/form-data;charset=UTF-8");
                // 设置文件名
                response.setHeader("Content-Disposition", "attachment;fileName="+ baseFileInfo.getFileName());
                byte[] buffer = new byte[1024];
                FileInputStream fis = null;
                BufferedInputStream bis = null;
                try {
                    fis = new FileInputStream(file);
                    bis = new BufferedInputStream(fis);
                    OutputStream os = response.getOutputStream();
                    int i = bis.read(buffer);
                    while (i != -1) {
                        os.write(buffer, 0, i);
                        i = bis.read(buffer);
                    }
                    log.info("downloadFile : SUCCESS");
                } catch (Exception e) {
                    log.warn("downloadFile Exception: fileId="+fileId,e);
                } finally {
                    if (bis != null) {
                        try {
                            bis.close();
                        } catch (IOException e) {
                            log.warn("downloadFile Exception: BufferedInputStream Close Exception"+fileId,e);
                        }
                    }
                    if (fis != null) {
                        try {
                            fis.close();
                        } catch (IOException e) {
                            log.warn("downloadFile Exception: FileInputStream Close Exception.",e);
                        }
                    }
                }
            }
        }

        return null;
    }
}
