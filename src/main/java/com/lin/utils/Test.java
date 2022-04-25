package com.lin.utils;

import com.lin.aspect.LogAspect;
import com.lin.entity.User;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 * @author :lzm
 * @date :2022/4/21 10:52
 * @description :
 * @modyified By:
 */
public class Test {
    public static void main(String[] args) {
        ApplicationContext applicationContext = new ClassPathXmlApplicationContext("bean.xml");
        User user1 = (User) applicationContext.getBean("user1");
        LogAspect logAspect = (LogAspect) applicationContext.getBean("logAspect");
        System.out.println(user1);

        
        System.out.println(logAspect);
    }

}
