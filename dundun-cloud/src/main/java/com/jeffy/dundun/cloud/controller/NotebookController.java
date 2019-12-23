package com.jeffy.dundun.cloud.controller;

import com.jeffy.dundun.cloud.CloudGlobal;
import com.jeffy.dundun.cloud.common.BaseRespond;
import com.jeffy.dundun.cloud.dao.module.Notebook;
import com.jeffy.dundun.cloud.service.NotebookService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.List;


@Controller
@RequestMapping("notebook")
public class NotebookController {
    @Resource
    private NotebookService notebookService;

    @RequestMapping("homepage")
    public String homepage(){
        return "notebook/homepage";
    }

    @RequestMapping("show")
    public String show(@RequestParam(required = true) Integer id,ModelMap map){
        map.addAttribute("noteId", id);
        return "notebook/show";
    }

    @RequestMapping("edit")
    public String edit(){
        return "notebook/edit";
    }

    @ResponseBody
    @RequestMapping(value = "list",method = RequestMethod.POST)
    public Object list(HttpServletRequest request){
        /**
         * 获取用户信息，然后查出用户的笔记信息
         */
        BaseRespond<List<Notebook>> baseRespond=new BaseRespond();
        baseRespond.setCode(CloudGlobal.RESPONSE_SUCCESS_CODE);
        baseRespond.setMsg(CloudGlobal.RESPONSE_SUCCESS_MSG);
        Object userIdStr=request.getSession().getAttribute("account");
        if(userIdStr!=null){
            Integer userId=Integer.parseInt(userIdStr.toString());
            baseRespond.setData(notebookService.selectByUserId(userId));
        }else{
            baseRespond.setCode(400);
            baseRespond.setMsg("未获取到用户信息");
        }
        return baseRespond;
    }

    @ResponseBody
    @RequestMapping(value = "selectById",method = RequestMethod.POST)
    public Object selectById(HttpServletRequest request, @RequestParam(required = true) Integer id){
        BaseRespond<Notebook> baseRespond=new BaseRespond();
        Notebook notebook=notebookService.selectById(id);
        if(notebook==null){
            baseRespond.setCode(402);
            baseRespond.setMsg("No Such Data");
        }

        baseRespond.setCode(CloudGlobal.RESPONSE_SUCCESS_CODE);
        baseRespond.setMsg(CloudGlobal.RESPONSE_SUCCESS_MSG);
        baseRespond.setData(notebook);
        return baseRespond;
    }

    @ResponseBody
    @RequestMapping(value = "create",method = RequestMethod.POST)
    public Object create(HttpServletRequest request,Notebook notebook){
        Object accountStr=request.getSession().getAttribute("account");
        if(accountStr==null){
            return "account is null";
        }
        Integer account=Integer.parseInt(accountStr.toString());
        notebook.setUserId(account);
        return notebookService.createNote(notebook);
    }

    @ResponseBody
    @RequestMapping(value = "update",method = RequestMethod.POST)
    public Object update(HttpServletRequest request,Notebook notebook){
        Object accountStr=request.getSession().getAttribute("account");
        if(accountStr==null){
            return "account is null";
        }
        Integer account=Integer.parseInt(accountStr.toString());
        notebook.setUserId(account);
        return notebookService.update(notebook);
    }

    @ResponseBody
    @RequestMapping(value = "delete",method = RequestMethod.POST)
    public Object delete(HttpServletRequest request,@RequestParam(required = true) Integer id){
        return notebookService.delete(id);
    }
}
