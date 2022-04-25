package com.lin.mapper;

import com.lin.entity.Menu;
import com.lin.entity.Role;
import com.lin.vo.RoleVo;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface RoleMapper {
    public List<Role> findallRole(RoleVo roleVo);

    public int insertRole(RoleVo roleVo);

    public int updateRole(RoleVo roleVo);

    public int deleteRoleByIds(@Param("ids") Integer[] ids);

    public int deleteRoleById(Integer id);

    Role checkName(String rolename);

    List<Menu> initroleMenu();

    int authrolemenu(@Param("menuId") Integer menuId, @Param("roleId") Integer roleId);

    int deleterolemenu(Integer roleId);

    List<Integer> findroleMenuByrid(Integer roleId);

    List<Role> getallrole();

    List<Role> getroleByuId(Integer userId);
}