package com.lin.service.Impl;

import com.lin.aspect.InvokeLog;
import com.lin.entity.Menu;
import com.lin.entity.Role;
import com.lin.mapper.RoleMapper;
import com.lin.service.RoleService;
import com.lin.vo.RoleVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author :lzm
 * @date :2022/4/13 20:35
 * @description :
 * @modyified By:
 */
@Service
public class RoleServiceImpl implements RoleService {
    @Autowired
    private RoleMapper roleMapper;

    @InvokeLog
    @Override
    public List<Role> findallRole(RoleVo roleVo) {
        return roleMapper.findallRole(roleVo);
    }

    @Override
    public int insertRole(RoleVo roleVo) {
        return roleMapper.insertRole(roleVo);
    }

    @Override
    public int updateRole(RoleVo roleVo) {
        return roleMapper.updateRole(roleVo);
    }

    @Override
    public int deleteRoleByIds(Integer[] ids) {
        return roleMapper.deleteRoleByIds(ids);
    }

    @Override
    public int deleteRoleById(Integer id) {
        return roleMapper.deleteRoleById(id);
    }

    @Override
    public Role checkName(String rolename) {
        return roleMapper.checkName(rolename);
    }

    @Override
    public List<Menu> initroleMenu() {
        return roleMapper.initroleMenu();
    }

    @Override
    public int authrolemenu(Integer MenuId, Integer roleId) {
        return roleMapper.authrolemenu(MenuId,roleId);
    }

    @Override
    public int deleterolemenu(Integer roleId) {
        return roleMapper.deleterolemenu(roleId);
    }

    @Override
    public List<Integer> findroleMenuByrid(Integer roleId) {
        return roleMapper.findroleMenuByrid(roleId);
    }

    @Override
    public List<Role> getallrole() {
        return roleMapper.getallrole();
    }

    @Override
    public List<Role> getroleByuId(Integer userId) {

        return roleMapper.getroleByuId(userId);
    }
}
