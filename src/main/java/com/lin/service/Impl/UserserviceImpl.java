package com.lin.service.Impl;

import com.lin.entity.User;
import com.lin.mapper.UserMapper;
import com.lin.service.UserService;
import com.lin.utils.ResponesReult;
import com.lin.vo.UserVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author :lzm
 * @date :2022/4/8 13:41
 * @description :
 * @modyified By:
 */
@Service
public class UserserviceImpl implements UserService {

    @Autowired
    private UserMapper userMapper;
    @Override
    public List<User> findAllUsers(UserVo userVo) {
        return userMapper.findAllUser(userVo);
    }

    @Override
    public User login(String userName,String userPwd) {

        return userMapper.login(userName,userPwd);
    }

    @Override
    public int insertuser(UserVo userVo) {

        int i=userMapper.insertuser(userVo);

        return i;
    }

    @Override
    public int deleteByids(Integer[] ids) {
        int i = userMapper.deleteByids(ids);
        return i;
    }

    @Override
    public int deleteByid(Integer id) {
        int i = userMapper.deleteByid(id);
        return i;
    }

    @Override
    public int updateuser(UserVo userVo) {
        int i = userMapper.updateuser(userVo);
        return i;
    }

    @Override
    public List<UserVo> finduseranddeptname(UserVo userVo) {

        return  userMapper.finduseranddeptname(userVo);
    }

    @Override
    public int adduserRole(Integer userId, Integer roleId) {
        return userMapper.adduserRole(userId,roleId);
    }

    /**
     * 功能描述:删除用户角色
     * @Param:
     * @param userId
     * @param roleId
     * @Return: int
     * @Author: lzm
     * @Date: 2022/4/24 18:46
     * @Description:
     */
    @Override
    public int deleteuserRole(Integer userId, Integer roleId) {
        return userMapper.deleteuserRole(userId,roleId);
    }


}
