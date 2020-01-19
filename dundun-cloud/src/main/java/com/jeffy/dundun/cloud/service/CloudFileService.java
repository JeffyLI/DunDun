package com.jeffy.dundun.cloud.service;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.jeffy.dundun.cloud.dao.mapper.CloudFileMapper;
import com.jeffy.dundun.cloud.dao.module.CloudFile;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;
import java.util.Map;

@Service("cloudFileService")
public class CloudFileService {
    @Resource
    private CloudFileMapper cloudFileMapper;

    private Logger log= LogManager.getLogger(this.getClass());

    public PageInfo<CloudFile> selectByPage(Map<String,Object> params,int pageNum,int pageSize){
        if(pageNum<=0 || pageSize<=0){
            return null;
        }
        PageHelper.startPage(pageNum,pageSize);
        List<CloudFile> cloudFiles=cloudFileMapper.selectByMap(params);
        log.info("cloudFileService - selectByPage: params="+params+",cloudFileSize:"+cloudFiles.size());
        return new PageInfo<CloudFile>(cloudFiles);
    }

}
