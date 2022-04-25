package com.lin.service;

import com.lin.entity.Menu;
import com.lin.entity.Role;
import com.lin.vo.RoleVo;

import java.util.List;

/**
 * @author :lzm
 * @date :2022/4/13 20:35
 * @description :
 * @modyified By:
 */
public interface RoleService {



    public List<Role> findallRole(RoleVo roleVo);

    public int insertRole(RoleVo roleVo);

    public int updateRole(RoleVo roleVo);

    public int deleteRoleByIds(Integer[] ids);

    public int deleteRoleById(Integer id);

    Role checkName(String rolename);

    List<Menu> initroleMenu();

    int authrolemenu(Integer MenuId, Integer roleId);

    int deleterolemenu(Integer roleId);

    List<Integer> findroleMenuByrid(Integer roleId);

    List<Role> getallrole();

    List<Role> getroleByuId(Integer userId);
}
