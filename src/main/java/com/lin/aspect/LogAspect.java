package com.lin.aspect;

import com.lin.vo.RoleVo;
import com.sun.javafx.util.Logging;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.Signature;
import org.aspectj.lang.annotation.*;
import org.aspectj.lang.reflect.MethodSignature;
import org.slf4j.Logger;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

import java.lang.reflect.Method;

/**
 * @author :lzm
 * @date :2022/4/21 11:16
 * @description :
 * @modyified By:
 */

@Component
@Aspect

public class LogAspect {



//    @Pointcut("@annotation(com.lin.aspect.InvokeLog)")
//    public void pt(){
//    }

    @Pointcut("execution(* com.lin.service.*.*(..))")
    public void pt(){
    }

//    @Before("pt()")
//    public void methodbefore(JoinPoint joinPoint){
//        Object[] args = joinPoint.getArgs();
//        System.out.println(args);
//        System.out.println("执行了aop+++++++++++++++++++");
//    }
//
//    @AfterReturning(value = "pt()",returning = "res")
//    public void methodafter(JoinPoint joinPoint,Object res){
//        String s = res.toString();
//        System.out.println(s);
//        System.out.println("执行了aop+++++++++++++++++++");
//    }

    @Around("pt()")
    public Object  around(ProceedingJoinPoint pjp){
        MethodSignature signature = (MethodSignature) pjp.getSignature();
        String name = signature.getName();
        Method method = signature.getMethod();
        System.out.println(method);
        String classs = signature.getReturnType().toString();
        String s = pjp.getArgs().toString();

        System.out.println("============执行了环绕====前=======");
        Object res=null;
        try {
           res=pjp.proceed();
            System.out.println("============执行了环绕====hou=======");
        } catch (Throwable throwable) {
            System.out.println("============执行了环绕====异常=======");
            throwable.printStackTrace();
        }finally {
            System.out.println("============执行了环绕====finally===="+classs+"=="+name+"==="+s+"==");
        }

return res;
    }
}
