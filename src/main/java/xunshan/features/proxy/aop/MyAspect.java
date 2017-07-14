package xunshan.features.proxy.aop;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.springframework.stereotype.Component;

@Component
@Aspect
public class MyAspect {
    // Utilities
    // 1. log all changing data method
    @Before("execution(* xunshan.features.proxy.aop.CustomerService.set*(..))")
    public void logBefore(JoinPoint jp) {
        System.out.println("logBefore");
    }
}
