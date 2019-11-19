package com.jeffy.dundun.cloud.service;

import com.jeffy.dundun.cloud.common.Validate;
import com.jeffy.dundun.cloud.dao.mapper.BaseFileInfoMapper;
import com.jeffy.dundun.cloud.dao.module.BaseFileInfo;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service("baseFileInfoService")
public class BaseFileInfoService {
    @Resource
    private BaseFileInfoMapper baseFileInfoMapper;

    private Logger log= LogManager.getLogger(this.getClass());

    /**
     * 创建文件记录
     * @param baseFileInfo
     * @return
     */
    public String createBaseFileInfo(BaseFileInfo baseFileInfo){
        if(!Validate.paraNullValidate(baseFileInfo.getFileId(),baseFileInfo.getFileHash(),baseFileInfo.getFileName(),
                baseFileInfo.getFolderId(),baseFileInfo.getUserId(),baseFileInfo.getFileSize())){
            log.warn("BaseFileInfoService - createBaseFileInfo() exist null params : BaseFileInfo="+baseFileInfo);
            return "Exist null params";
        }

        //生成文件id(用户id + 时间戳）
        Date now=new Date();
        baseFileInfo.setCreateDate(now);
        baseFileInfo.setUpdateDate(now);
        baseFileInfo.setFileStatus(0);

        try {
            baseFileInfoMapper.insert(baseFileInfo);
        }catch (Exception e){
            log.warn("BaseFileInfoService - createBaseFileInfo() Exception: BaseFileInfo="+baseFileInfo,e);
            return "CreateBaseFileInfo Exception";
        }

        return "SUCCESS";
    }

    public List<BaseFileInfo> selectByFileHash(String fileHash){
        if(null==fileHash){
            return null;
        }

        List<BaseFileInfo> baseFileInfos=null;
        try{
            Map<String,Object> params =new HashMap<>();
            params.put("eqFileHash",fileHash);
            baseFileInfos=baseFileInfoMapper.selectByMap(params);
        }catch(Exception e){
            log.warn("BaseFileInfoService - createBaseFileInfo() Exception: BaseFileInfos="+baseFileInfos,e);
        }

        return baseFileInfos;
    }

    public List<BaseFileInfo> selectByUserId(String userId){
        if(null==userId){
            return null;
        }

        List<BaseFileInfo> baseFileInfos=null;
        try{
            Map<String,Object> params =new HashMap<>();
            params.put("eqUserId",userId);
            baseFileInfos=baseFileInfoMapper.selectByMap(params);
        }catch(Exception e){
            log.warn("BaseFileInfoService - createBaseFileInfo() Exception: BaseFileInfos="+baseFileInfos,e);
        }

        return baseFileInfos;
    }

    public List<BaseFileInfo> selectByMap(Map<String,Object> params){
        //预防所有参数都不传
        return baseFileInfoMapper.selectByMap(params);
    }

    public BaseFileInfo selectById(String fileId){
        if(!Validate.paraNullValidate(fileId)){
            return null;
        }

        List<BaseFileInfo> baseFileInfos=null;
        try{
            Map<String,Object> params =new HashMap<>();
            params.put("eqFileId",fileId);
            baseFileInfos=baseFileInfoMapper.selectByMap(params);
            if(baseFileInfos.size()>0){
                return baseFileInfos.get(0);
            }
        }catch(Exception e){
            log.warn("BaseFileInfoService - createBaseFileInfo() Exception: BaseFileInfos="+baseFileInfos,e);
        }

        return null;
    }

    public boolean deleteById(String fileId){
        if(!Validate.paraNullValidate(fileId)){
            return false;
        }

        Map<String,Object> params =new HashMap<>();
        params.put("eqFileId",fileId);
        try {
            baseFileInfoMapper.deleteByMap(params);
        }catch (Exception e){
            log.warn("deleteById fail:fileId="+fileId);
            return false;
        }

        return true;
    }
}
