package com.lin.controller;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.lin.entity.Menu;
import com.lin.entity.Role;
import com.lin.service.RoleService;
import com.lin.utils.ResponesReult;
import com.lin.utils.TreeNode;
import com.lin.utils.TreeUtil;
import com.lin.vo.MenuVo;
import com.lin.vo.RoleVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

/**
 * @author :lzm
 * @date :2022/4/13 20:30
 * @description :
 * @modyified By:
 */
@RestController
@RequestMapping("/role")
public class RoleController {

    @Autowired
    private RoleService roleService;


    /**
     * 功能描述:查询角色信息
     * @Param:
     * @param roleVo
     * @Return: com.lin.utils.ResponesReult
     * @Author: lzm
     * @Date: 2022/4/15 19:44
     * @Description:
     */
    @RequestMapping("/findallrole")
    public ResponesReult findallrole(RoleVo roleVo){
        PageHelper.startPage(roleVo.getPage(),roleVo.getLimit());
        List<Role> roleList = roleService.findallRole(roleVo);
        PageInfo<Role> pageInfo=new PageInfo<>(roleList);
        return new ResponesReult(200,"数据查询成功",pageInfo.getTotal(),roleList);

    }


    /**
     * 功能描述:新增角色信息
     * @Param:
     * @param roleVo
     * @Return: com.lin.utils.ResponesReult
     * @Author: lzm
     * @Date: 2022/4/15 19:43
     * @Description:
     */
    @RequestMapping("/insertrole")
    public ResponesReult insertrole(RoleVo roleVo){
        int i = roleService.insertRole(roleVo);
        if(i>0){

        return new ResponesReult(200,"成功");
        }else {
            return new ResponesReult(200,"失败");
        }

    }


    /**
     * 功能描述:通过id删除单行角色数据
     * @Param:
     * @param id
     * @Return: com.lin.utils.ResponesReult
     * @Author: lzm
     * @Date: 2022/4/15 19:43
     * @Description:
     */
    @RequestMapping("/deleteroleById")
    public ResponesReult deleteroleById(Integer id){

        int i = roleService.deleteRoleById(id);
        if(i>0){

            return new ResponesReult(200,"成功");
        }else {
            return new ResponesReult(200,"失败");
        }




    }


    /**
     * 功能描述:通过ids批量删除角色
     * @Param:
     * @param ids
     * @Return: com.lin.utils.ResponesReult
     * @Author: lzm
     * @Date: 2022/4/15 19:39
     * @Description:
     */
    @RequestMapping("/deleteroleByIds")
    public ResponesReult deleteroleByIds(@RequestBody Integer[] ids){

        int i = roleService.deleteRoleByIds(ids);
        if(i>0){

            return new ResponesReult(200,"成功");
        }else {
            return new ResponesReult(200,"失败");
        }

    }


    /**
     * 功能描述:更新角色
     * @Param:
     * @param roleVo
     * @Return: com.lin.utils.ResponesReult
     * @Author: lzm
     * @Date: 2022/4/15 19:40
     * @Description:
     */
    @RequestMapping("/updaterole")
    public ResponesReult updaterole(RoleVo roleVo){

        int i = roleService.updateRole(roleVo);
        if(i>0){

            return new ResponesReult(200,"成功");
        }else {
            return new ResponesReult(200,"失败");
        }

    }


    /**
     * 功能描述:新增角色的时候判断角色是否存在
     * @Param:
     * @param roleName
     * @Return: com.lin.utils.ResponesReult
     * @Author: lzm
     * @Date: 2022/4/15 19:41
     * @Description:
     */
    @RequestMapping("/checkName")
    public ResponesReult checkName(String roleName){
      Role role=roleService.checkName(roleName);
        if(role==null){

            return new ResponesReult(200,"角色名已经存在");
        }else {
            return new ResponesReult(300,"失败");
        }
    }

    @RequestMapping("/initroleMenu")
    public ResponesReult initroleMenu(Integer roleId){

        List<TreeNode> treeNodes = new ArrayList<>();
        List<Menu> menus = roleService.initroleMenu();
        List<Integer> menuids= roleService.findroleMenuByrid(roleId);
        for(Menu menu : menus){
          String checkstau="0";
           for (Integer menuid:menuids){
               if (menuid==menu.getId()){
                   checkstau="1";
               }
           }
           TreeNode treeNode=new TreeNode();
           treeNode.setId(menu.getId());
           treeNode.setTitle(menu.getTitle());
           treeNode.setParentId(menu.getPid());
           treeNode.setCheckArr(checkstau);
           treeNodes.add(treeNode);
       }
           // return new ResponesReult(200,"成功", TreeUtil.toTree(menuInfo,0));
        return  new ResponesReult(200,"ok",treeNodes);

    }



    @Transactional
    @PostMapping("/authrolemenu")
    public ResponesReult authrolemenu(@RequestParam("ids[]") Integer[] ids, Integer roleId){
        roleService.deleterolemenu(roleId);
        for (int i = 0; i <ids.length ; i++) {
            roleService.authrolemenu(ids[i],roleId);
        }

        return  new ResponesReult(200,"ok");

    }


    @RequestMapping("/getallrole")
    public ResponesReult getallrole(){

        List<Role> roleList = roleService.getallrole();
        PageInfo<Role> pageInfo=new PageInfo<>(roleList);
        return new ResponesReult(200,"数据查询成功",pageInfo.getTotal(),roleList);

    }


    @RequestMapping("/getroleByuId")
    public ResponesReult getroleByuId(Integer userId){
        return  new ResponesReult(200,"ok" ,roleService.getroleByuId(userId));

    }







}
