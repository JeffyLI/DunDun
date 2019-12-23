package com.jeffy.dundun.cloud.controller;

import com.jeffy.dundun.cloud.common.BaseRespond;
import com.jeffy.dundun.cloud.dao.module.BaseFileInfo;
import com.jeffy.dundun.cloud.service.BaseFileInfoService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


@RestController
@RequestMapping("baseFileInfo")
public class BaseFileInfoController {
    @Resource
    private BaseFileInfoService baseFileInfoService;

    @RequestMapping("delete")
    public Object deleteById(@RequestParam(required = true) String fileId){
        BaseRespond baseRespond=new BaseRespond();

       if(baseFileInfoService.deleteById(fileId)){
           baseRespond.setMsg("SUCCESS");
           baseRespond.setCode(200);
       }else{
           baseRespond.setMsg("Delete Fail");
           baseRespond.setCode(400);
       }

       return baseRespond;
    }

    @RequestMapping("selectByUserId")
    public Object selectByUserId(@RequestParam(required = true) String userId){
        BaseRespond<List<BaseFileInfo>> baseRespond=new BaseRespond<>();

        List<BaseFileInfo> baseFileInfos=baseFileInfoService.selectByUserId(userId);
        baseRespond.setData(baseFileInfos);
        baseRespond.setMsg("SUCCESS");
        baseRespond.setCode(200);

        return baseRespond;
    }

}
