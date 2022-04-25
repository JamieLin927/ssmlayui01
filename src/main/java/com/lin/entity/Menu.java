package com.lin.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;

import java.io.Serializable;
import java.util.Date;

/**
 * @author :lzm
 * @date :2022/4/9 13:31
 * @description :
 * @modyified By:
 */

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Menu implements Serializable {
    private  Integer id;
    private  Integer pid;
    private  String title;
    private  String icon;
    private  String href;
    private  String target;
    private  Integer sort;
    private  Integer status;
    private  String remark;
    private  Date create_at;
    private  Date update_at;
    private  Date delete_at;

}
