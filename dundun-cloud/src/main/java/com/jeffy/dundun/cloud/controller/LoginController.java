package com.jeffy.dundun.cloud.controller;

import com.jeffy.dundun.cloud.common.BaseRespond;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

@Controller
@RequestMapping("login")
public class LoginController {

    @RequestMapping("loginPage")
    public String loginPage(){
        return "login/login";
    }

    @ResponseBody
    @RequestMapping("loginIn")
    public Object loginIn(HttpServletRequest request){
        String account=request.getParameter("account");
        String password=request.getParameter("password");
        BaseRespond<String> baseRespond=new BaseRespond<>();
        if(checkAccount(account,password)) {
            HttpSession httpSession=request.getSession();
            httpSession.setAttribute("account",account);
            baseRespond.setMsg("登录成功");
            baseRespond.setCode(200);
        }else{
            baseRespond.setMsg("登录失败");
            baseRespond.setCode(400);
        }

        return baseRespond;
    }

    private boolean checkAccount(String account,String password){
        if(account==null || password==null){
            return false;
        }

        if(account.equals("123")&&password.equals("123")){
            return true;
        }else{
            return false;
        }
    }
}
