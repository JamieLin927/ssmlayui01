package com.lin.controller;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.lin.entity.Dept;
import com.lin.service.DeptService;
import com.lin.utils.ResponesReult;
import com.lin.vo.DeptVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * @author :lzm
 * @date :2022/4/12 13:06
 * @description :
 * @modyified By:
 */
@RestController
@RequestMapping("/dept")
public class DeptController {

    @Autowired
    private DeptService deptService;

    /**
     * 功能描述:主要用于初始化一些用到部门信息的组件，如：下拉框
     * @Param:
     * @param deptVo
     * @Return: com.lin.utils.ResponesReult
     * @Author: lzm
     * @Date: 2022/4/12 20:17
     * @Description:
     */
    @RequestMapping("/initallDept")
    public ResponesReult initallDept(DeptVo deptVo){
        List<Dept> deptList = deptService.initallDept(deptVo);
        return new ResponesReult(200,"dd",deptList);

    }


    /**
     * 功能描述:查询所有的部门信息
     * @Param:
     * @param deptVo
     * @Return: com.lin.utils.ResponesReult
     * @Author: lzm
     * @Date: 2022/4/12 20:16
     * @Description:
     */
    @RequestMapping("/findallDept")
    public ResponesReult findallDept(DeptVo deptVo) {

        PageHelper.startPage(deptVo.getPage(),deptVo.getLimit());
        List<Dept> deptList = deptService.findallDept(deptVo);
        PageInfo<Dept> pageInfo=new PageInfo<>(deptList);
        return new ResponesReult(200, "部门查询成功",pageInfo.getTotal(), deptList);
    }


    /**
     * 功能描述:添加部门信息
     * @Param:
     * @param deptVo
     * @Return: com.lin.utils.ResponesReult
     * @Author: lzm
     * @Date: 2022/4/12 20:17
     * @Description:
     */
    @RequestMapping("/adddept")
    public ResponesReult adddept(DeptVo deptVo) {
        int i = deptService.adddept(deptVo);
        if(i>0){
        return new ResponesReult(200, "部门添加成功");
    }
        else {
            return new ResponesReult(300, "部门添加失败");}
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
    public ResponesReult deleteByIds(@RequestBody  Integer[] ids){

        int i =deptService.deleteByids(ids);
        if(i>0){
            return new ResponesReult(200, "批量删除成功");

        } else {

            return new ResponesReult(300, "删除用户失败，请重新删除");
        }
    }


    /**
     * 功能描述:通过id删除用户
     * @Param:
     * @param id
     * @Return: com.lin.utils.ResponesReult
     * @Author: lzm
     * @Date: 2022/4/11 15:16
     * @Description:
     */
    @RequestMapping("/deleteById")
    public ResponesReult deleteById(Integer id){

        int i =deptService.deleteById(id);
        if(i>0){
            return new ResponesReult(200, "删除部门信息成功");

        } else {

            return new ResponesReult(300, "删除用户失败，请重新删除");
        }
    }


    /**
     * 功能描述: 修改部门信息
     * @Param:
     * @param deptVo
     * @Return: com.lin.utils.ResponesReult
     * @Author: lzm
     * @Date: 2022/4/12 20:30
     * @Description:
     */
    @RequestMapping("/updatedept")
    public ResponesReult updatedept(DeptVo deptVo){
        int i = deptService.updatedept(deptVo);
        if(i>0){
            return new ResponesReult(200,"修改成功");
        }
        else {
            return new ResponesReult(200,"修改成功");
        }


    }




}
