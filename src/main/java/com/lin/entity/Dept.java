package com.lin.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author :lzm
 * @date :2022/4/12 12:56
 * @description :
 * @modyified By:
 */

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Dept {

    private  Integer id;
    private  String  deptName;
    private  String  deptAdd;
    private  String  isValid;
    private  String  remark;
    private  String  creatDate;
    private  String  updateDate;


}
