package com.lin.mapper;

import com.lin.entity.Dept;
import com.lin.vo.DeptVo;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface DeptMapper {
    public List<Dept> initallDept(DeptVo deptVo);

    public List<Dept>  findallDept(DeptVo deptVo);

    public int adddept(DeptVo deptVo);

    public int deleteByids(@Param("ids") Integer[] ids);

    public int deleteById(Integer id);

    public int updatedept(DeptVo deptVo);
}
