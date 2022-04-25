package com.lin.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author :lzm
 * @date :2022/4/8 11:58
 * @description :
 * @modyified By:
 */

@RestController
public class TestController {
    @RequestMapping("/test")
    public String test(){
        return "jj";

    }
}
