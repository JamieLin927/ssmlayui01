package com.lin.aspect;

import org.springframework.stereotype.Component;

/**
 * @author :lzm
 * @date :2022/4/22 11:13
 * @description :
 * @modyified By:
 */
@Component
public class XmlAspect {

    public void  pt(){}

    public void  before(){
        System.out.println("===============之前===============");
    }
}
