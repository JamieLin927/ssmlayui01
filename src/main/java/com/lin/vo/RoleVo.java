package com.lin.vo;

import com.lin.entity.Dept;
import com.lin.entity.Role;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author :lzm
 * @date :2022/4/12 12:58
 * @description :
 * @modyified By:
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class RoleVo extends Role {
    private Integer page;
    private Integer limit;
}
