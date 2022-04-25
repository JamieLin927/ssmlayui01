package com.lin.service;

import com.lin.entity.User;
import com.lin.utils.ResponesReult;
import com.lin.vo.UserVo;

import java.util.List;

public interface UserService {

    public List<User> findAllUsers(UserVo userVo);

    public User login(String userName,String userPwd);

    public int insertuser(UserVo userVo);

    public int deleteByids(Integer[] ids);

    public int deleteByid(Integer id);

    public int updateuser(UserVo userVo);

    public List<UserVo> finduseranddeptname(UserVo userVo);


    int adduserRole(Integer userId, Integer roleId);

    int deleteuserRole(Integer userId, Integer roleId);
}
