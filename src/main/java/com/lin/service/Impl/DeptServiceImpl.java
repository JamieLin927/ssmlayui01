package com.lin.service.Impl;

import com.lin.entity.Dept;
import com.lin.mapper.DeptMapper;
import com.lin.service.DeptService;
import com.lin.utils.ResponesReult;
import com.lin.vo.DeptVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * @author :lzm
 * @date :2022/4/12 13:05
 * @description :
 * @modyified By:
 */

@Service
public class DeptServiceImpl implements DeptService {

    @Autowired
    private DeptMapper deptMapper;
    @Override
    public List<Dept> initallDept(DeptVo deptVo) {
        return deptMapper.initallDept(deptVo);
    }


    @Override
    public List<Dept> findallDept(DeptVo deptVo) {
        List<Dept> depts = deptMapper.findallDept(deptVo);
        return depts;
    }

    @Override
    public int adddept(DeptVo deptVo) {
        return deptMapper.adddept(deptVo);
    }

    @Override
    public int deleteByids(Integer[] ids) {
        return deptMapper.deleteByids(ids);
    }

    @Override
    public int deleteById(Integer id) {
        return deptMapper.deleteById(id);
    }

    @Override
    public int updatedept(DeptVo deptVo) {
        return deptMapper.updatedept(deptVo);
    }
}
