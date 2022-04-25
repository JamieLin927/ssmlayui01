package com.lin.mapper;

import com.lin.entity.User;
import com.lin.vo.UserVo;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface UserMapper {

    public List<User> findAllUser(UserVo userVo);

    public User login(@Param("userName") String userName,@Param("userPwd") String userPwd);

    public int insertuser(UserVo userVo);

    public int deleteByids(@Param("ids") Integer[] ids);

    public int deleteByid(Integer id);

    public int updateuser(UserVo userVo);

    public List<UserVo> finduseranddeptname(UserVo userVo);

    int adduserRole(@Param("userId") Integer userId, @Param("roleId") Integer roleId);

    int deleteuserRole(@Param("userId") Integer userId, @Param("roleId") Integer roleId);
}
