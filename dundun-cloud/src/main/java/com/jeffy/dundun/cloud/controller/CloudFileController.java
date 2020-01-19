package com.jeffy.dundun.cloud.controller;

import com.github.pagehelper.PageInfo;
import com.jeffy.dundun.cloud.CloudGlobal;
import com.jeffy.dundun.cloud.common.BaseRespond;
import com.jeffy.dundun.cloud.common.RequestUtils;
import com.jeffy.dundun.cloud.dao.module.CloudFile;
import com.jeffy.dundun.cloud.service.CloudFileService;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.util.Map;

@Controller
@RequestMapping("cloudFile")
public class CloudFileController {
    @Resource
    private CloudFileService cloudFileService;

    private Logger log= LogManager.getLogger(this.getClass());

    @RequestMapping("listPage")
    public String listPage(HttpServletRequest reques){
        return "cloudFile/listPage";
    }


    @ResponseBody
    @RequestMapping(value = "list",method = RequestMethod.POST)
    public Object list(HttpServletRequest request,
                       @RequestParam(required = true) Integer pageNum,
                       @RequestParam(required = true) Integer pageSize){
        Map<String,Object> params= RequestUtils.getParameterMap(request);
        BaseRespond<PageInfo<CloudFile>> baseRespond=new BaseRespond<>();
        baseRespond.setData(cloudFileService.selectByPage(params,pageNum,pageSize));
        baseRespond.setCode(CloudGlobal.RESPONSE_SUCCESS_CODE);
        baseRespond.setMsg(CloudGlobal.RESPONSE_SUCCESS_MSG);
        return baseRespond;
    }
}
