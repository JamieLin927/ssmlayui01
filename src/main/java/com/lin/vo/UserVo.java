package com.lin.vo;

import com.lin.entity.Dept;
import com.lin.entity.User;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author :lzm
 * @date :2022/4/10 16:58
 * @description :
 * @modyified By:
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserVo extends User {

    private Integer page;
    private Integer limit;

    private Dept dept;
}
