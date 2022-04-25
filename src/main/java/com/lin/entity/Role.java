package com.lin.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Role {
    private Integer id;

    private String roleName;

    private String roleRemark;

    private Integer isValid;

    private Date createDate;

    private Date updateDate;


}