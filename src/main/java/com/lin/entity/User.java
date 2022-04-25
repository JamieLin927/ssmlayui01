package com.lin.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;
import org.springframework.format.annotation.DateTimeFormat;

/**
 * @author :lzm
 * @date :2022/4/8 13:34
 * @description :
 * @modyified By:
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class User {

    private Integer id;
    private String userName;
    private String userPwd;
    private String trueName;
    private Integer deptId;
    private String email;
    private String phone;
    private Integer is_valid;
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private String createDate;
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private String updateDate;


}
