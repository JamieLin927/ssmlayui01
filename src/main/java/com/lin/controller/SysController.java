package com.lin.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpSession;

/**
 * @author :lzm
 * @date :2022/4/8 14:54
 * @description :该接口主要用于页面跳转
 * @modyified By:
 */
@Controller
public class SysController {
    @RequestMapping("/index")
    public String toindex(){
        System.out.println("aaaaaaaaaa");
        return "index";
    }

    @RequestMapping("/userlist")
    public String touserlist(){
        System.out.println("bbbb");

        return "userlist";
    }

    @RequestMapping("/form")
    public String toform(){
        System.out.println("bbbb");

        return "form";
    }
    @RequestMapping("/towelcome")
    public String towelcome(){
        System.out.println("bbbb11111");

        return "welcome-1";
    }

    @RequestMapping("/torolelist")
    public String torolelist(){
        System.out.println("bbbb");

        return "rolelist";
    }

    @RequestMapping("/todeptlist")
    public String todeptlist(){
        System.out.println("deptlist");

        return "deptlist";
    }

    @RequestMapping("/tologin")
    public String tologin(HttpSession session){
        return "login";
    }


    @RequestMapping("/loginout")
    public String loginout(HttpSession session){
        session.removeAttribute("username");
        return "login";
    }


}
