package com.lin.utils;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

/**
 * @author :lzm
 * @date :2022/4/24 2:25
 * @description :
 * @modyified By:
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class TreeNode {

    private Integer id;
    private Integer parentId;
    private String checkArr="0";
    private String title;
    private List<TreeNode> children;


}
