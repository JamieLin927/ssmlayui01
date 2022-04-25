package com.lin.service;

import com.lin.entity.Dept;
import com.lin.vo.DeptVo;

import java.util.List;

public interface DeptService {
    public List<Dept> initallDept(DeptVo deptVo);

    public List<Dept> findallDept(DeptVo deptVo);

    public int adddept(DeptVo deptVo);

    public int deleteByids(Integer[] ids);

    public int deleteById(Integer id);

    public int updatedept(DeptVo deptVo);
}
