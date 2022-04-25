package com.lin.vo;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Data;

import java.util.List;

/**
 * @author :lzm
 * @date :2022/4/9 14:02
 * @description :
 * @modyified By:
 */

@Data
@JsonInclude(JsonInclude.Include.NON_NULL)
public class MenuVo {

    private  Integer id;
    private  Integer pid;
    private  String title;
    private  String icon;
    private  String href;
    private  String target;
    private List<MenuVo> child;
}
