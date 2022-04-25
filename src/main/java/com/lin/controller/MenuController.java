package com.lin.controller;

import com.lin.entity.User;
import com.lin.service.Impl.MenuServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpSession;
import java.util.Map;

/**
 * @author :lzm
 * @date :2022/4/9 14:28
 * @description :
 * @modyified By:
 */
@RestController
public class MenuController {

    @Autowired
    private MenuServiceImpl menuService;

    /**
     * 功能描述:初始化菜单栏数据
     * @Param:
     * @Return: java.util.Map<java.lang.String, java.lang.Object>
     * @Author: lzm
     * @Date: 2022/4/15 19:45
     * @Description:
     */
    @RequestMapping("/initmenu")
    public Map<String,Object> initmenu(HttpSession session){
        User user = (User) session.getAttribute("userName");
        return menuService.initmenu(user.getId());
    }
}
