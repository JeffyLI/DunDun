package com.jeffy.dundun.cloud.service;

import com.jeffy.dundun.cloud.CloudGlobal;
import com.jeffy.dundun.cloud.common.BaseRespond;
import com.jeffy.dundun.cloud.common.Validate;
import com.jeffy.dundun.cloud.dao.mapper.NotebookMapper;
import com.jeffy.dundun.cloud.dao.module.Notebook;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service("notebookService")
public class NotebookService {
    @Resource
    private NotebookMapper notebookMapper;

    private Logger log= LogManager.getLogger(this.getClass());

    public List<Notebook> selectByUserId(Integer userId){
        if(userId==null || userId<0){
            return null;
        }

        Map<String,Object> params=new HashMap<>();
        params.put("eqUserId",userId);
        return notebookMapper.selectByMap(params);
    }

    public Notebook selectById(Integer id){
        if(id==null || id<0){
            return null;
        }

        Map<String,Object> params=new HashMap<>();
        params.put("eqId",id);
        List<Notebook> notebooks=notebookMapper.selectByMap(params);
        if(notebooks.size()>0){
            return notebooks.get(0);
        }
        return null;
    }

    public BaseRespond createNote(Notebook notebook){
        BaseRespond baseRespond=new BaseRespond();
        baseRespond.setMsg(CloudGlobal.RESPONSE_SUCCESS_MSG);
        baseRespond.setCode(CloudGlobal.RESPONSE_SUCCESS_CODE);
        if(!Validate.paraNullValidate(notebook.getTitle(),notebook.getUserId())){
            baseRespond.setMsg(CloudGlobal.RESPONSE_NULLPARAM_MSG);
            baseRespond.setCode(CloudGlobal.RESPONSE_NULLPARAM_CODE);
            return baseRespond;
        }

        try{
            notebook.setCreateDate(new Date());
            notebookMapper.insert(notebook);
        }catch (Exception e){
            log.warn("saveNote error: notebook = "+notebook,e);
            baseRespond.setMsg("数据插入异常");
            baseRespond.setCode(400);
            return baseRespond;
        }
        return baseRespond;
    }

    public BaseRespond update(Notebook notebook){
        BaseRespond baseRespond=new BaseRespond();

        if(!Validate.paraNullValidate(notebook.getTitle(),notebook.getUserId(),notebook.getId())){
            baseRespond.setMsg(CloudGlobal.RESPONSE_NULLPARAM_MSG);
            baseRespond.setCode(CloudGlobal.RESPONSE_NULLPARAM_CODE);
            return baseRespond;
        }

        try{
            Notebook tempNotebook=selectById(notebook.getId());
            if(tempNotebook==null){
                baseRespond.setMsg(CloudGlobal.RESPONSE_NODATA_MSG);
                baseRespond.setCode(CloudGlobal.RESPONSE_NODATA_CODE);
                return baseRespond;
            }

            Map<String,Object> params=new HashMap<>();
            params.put("eqId",notebook.getId());
            params.put("title",notebook.getTitle());
            params.put("tag",notebook.getTag());
            params.put("content",notebook.getContent());
            params.put("userId",notebook.getUserId());
            params.put("updateDate",new Date());
            Integer operNum=notebookMapper.updateByMap(params);
            if(operNum==0){
                baseRespond.setMsg(CloudGlobal.RESPONSE_ZEROOPER_MSG);
                baseRespond.setCode(CloudGlobal.RESPONSE_ZEROOPER_CODE);
                return baseRespond;
            }

        }catch (Exception e){
            log.warn("update error: notebook = "+notebook,e);
            baseRespond.setMsg("数据更新异常");
            baseRespond.setCode(400);
            return baseRespond;
        }

        baseRespond.setMsg(CloudGlobal.RESPONSE_SUCCESS_MSG);
        baseRespond.setCode(CloudGlobal.RESPONSE_SUCCESS_CODE);
        return baseRespond;
    }

    public BaseRespond delete(Integer id){
        BaseRespond baseRespond=new BaseRespond();

        /**
         * 还要添加一个删除权限校验，只能删除本人的文件
         */

        try {
            Notebook tempNotebook = selectById(id);
            if (tempNotebook == null) {
                baseRespond.setMsg(CloudGlobal.RESPONSE_NODATA_MSG);
                baseRespond.setCode(CloudGlobal.RESPONSE_NODATA_CODE);
                return baseRespond;
            }

            Map<String, Object> params = new HashMap<>();
            params.put("eqId", id);
            notebookMapper.deleteByMap(params);
        }catch (Exception e){
            log.warn("delete error: id = "+id,e);
            baseRespond.setMsg("数据删除异常");
            baseRespond.setCode(400);
            return baseRespond;
        }

        baseRespond.setMsg(CloudGlobal.RESPONSE_SUCCESS_MSG);
        baseRespond.setCode(CloudGlobal.RESPONSE_SUCCESS_CODE);
        return baseRespond;
    }
}
