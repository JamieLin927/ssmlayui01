package com.lin.controller;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.lin.entity.User;
import com.lin.service.UserService;
import com.lin.utils.ResponesReult;
import com.lin.vo.UserVo;
import org.apache.ibatis.annotations.Param;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.DigestUtils;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpSession;
import java.util.List;

/**
 * @author :lzm
 * @date :2022/4/8 13:42
 * @description :
 * @modyified By:
 */
@RestController
@RequestMapping("/user")
public class UserController {

    @Autowired
    private UserService userService;


    /**
     * 功能描述:查询所有用户
     * @Param:
     * @Return: java.util.List<com.lin.entity.User>
     * @Author: lzm
     * @Date: 2022/4/8 13:44
     * @Description:
     */
    @RequestMapping("/finduseranddeptname")
    public ResponesReult finduseranddeptname(UserVo userVo){

        PageHelper.startPage(userVo.getPage(),userVo.getLimit());
        List<UserVo> list = userService.finduseranddeptname(userVo);
        PageInfo<UserVo> pageInfo=new PageInfo<>(list);

       return new ResponesReult(200,"查询成功111",pageInfo.getTotal(),list);
    }


    @RequestMapping("/findalluser")
    public ResponesReult findAllUser(UserVo userVo){

        PageHelper.startPage(userVo.getPage(),userVo.getLimit());
        List<User> list = userService.findAllUsers(userVo);
        PageInfo<User> pageInfo=new PageInfo<User>(list);

        return new ResponesReult(200,"查询成功111",pageInfo.getTotal(),list);
    }

    /**登录功能接口
     * 功能描述:
     * @Param:
     * @param userName
     * @param userPwd
     * @param session
     * @Return: com.lin.utils.ResponesReult
     * @Author: lzm
     * @Date: 2022/4/15 18:23
     * @Description:
     */
    @PostMapping("/login")
    public ResponesReult dologin(String userName, String userPwd, HttpSession session) {
        User user = userService.login(userName, DigestUtils.md5DigestAsHex(userPwd.getBytes()));
        if (user != null) {
            session.setAttribute("userName",user);
            return new ResponesReult(200, "欢迎光临");

        } else {

            return new ResponesReult(300, "用户名密码不正确");
        }
    }


    /**
     * 功能描述:插入用户
     * @Param:
     * @param userVo
     * @Return: com.lin.utils.ResponesReult
     * @Author: lzm
     * @Date: 2022/4/15 18:23
     * @Description:
     */
    @PostMapping("/insertuser")
    public ResponesReult insertuser(UserVo userVo){
        userVo.setUserPwd(DigestUtils.md5DigestAsHex("123456".getBytes()));
        int i = userService.insertuser(userVo);
        if(i>0){
            return new ResponesReult(200, "用户添加成功");

        } else {

            return new ResponesReult(300, "新增用户失败，请重新输入");
        }
    }


    /**
     * 功能描述:批量删除用户
     * @Param:
     * @param ids
     * @Return: com.lin.utils.ResponesReult
     * @Author: lzm
     * @Date: 2022/4/11 15:16
     * @Description:
     */
    @RequestMapping("/deleteByIds")
    public ResponesReult deleteByIds(@RequestBody Integer[] ids){

        int i =userService.deleteByids(ids);
        if(i>0){
            return new ResponesReult(200, "批量删除成功");

        } else {

            return new ResponesReult(300, "删除用户失败，请重新删除");
        }
    }


    /**
     * 功能描述:通过id删除单条用户数据
     * @Param:
     * @param id
     * @Return: com.lin.utils.ResponesReult
     * @Author: lzm
     * @Date: 2022/4/11 16:30
     * @Description:
     */
    @RequestMapping("/deleteById")
    public ResponesReult deleteById(Integer id){
        int i = userService.deleteByid(id);
        if(i>0){
            return new ResponesReult(200, "删除用户成功");

        } else {

            return new ResponesReult(300, "删除用户失败");
        }
    }


    /**
     * 功能描述:通过id修改单条用户数据
     * @Param:
     * @param userVo
     * @Return: com.lin.utils.ResponesReult
     * @Author: lzm
     * @Date: 2022/4/11 16:30
     * @Description:
     */
    @RequestMapping("/updateuser")
    public ResponesReult updateuser(UserVo userVo){
        int i = userService.updateuser(userVo);
        if(i>0){
            return new ResponesReult(200, "修改用户成功");

        } else {

            return new ResponesReult(300, "修改用户失败");
        }
    }


    /**
     * 功能描述:给用户分配角色
     * @Param:
     * @param userId
     * @param roleId
     * @Return: com.lin.utils.ResponesReult
     * @Author: lzm
     * @Date: 2022/4/24 18:43
     * @Description:
     */
    @RequestMapping("/adduserRole")
    public ResponesReult adduserRole(Integer  userId, Integer roleId ){
        int i = userService.adduserRole(userId,roleId);
        if(i>0){
            return new ResponesReult(200, "分配角色成功");

        } else {

            return new ResponesReult(300, "修改用户失败");
        }
    }


    /**
     * 功能描述:给用户分配角色之删除用户角色
     * @Param:
     * @param userId
     * @param roleId
     * @Return: com.lin.utils.ResponesReult
     * @Author: lzm
     * @Date: 2022/4/24 18:44
     * @Description:
     */
    @RequestMapping("/deleteuserRole")
    public ResponesReult deleteuserRole(Integer  userId, Integer roleId ){
        int i = userService.deleteuserRole(userId,roleId);
        if(i>0){
            return new ResponesReult(200, "分配角色成功");

        } else {

            return new ResponesReult(300, "修改用户失败");
        }
    }
}
