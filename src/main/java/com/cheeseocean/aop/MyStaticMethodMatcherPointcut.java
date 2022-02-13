package com.cheeseocean.aop;

import java.lang.reflect.Method;

import org.aopalliance.aop.Advice;
import org.springframework.aop.MethodBeforeAdvice;
import org.springframework.aop.support.StaticMethodMatcherPointcutAdvisor;
import org.springframework.stereotype.Component;

@Component
public class MyStaticMethodMatcherPointcut extends StaticMethodMatcherPointcutAdvisor {
    @Override
    public boolean matches(Method method, Class<?> targetClass) {
        if ("staticMethod".equals(method.getName())) {
            return true;
        }
        return false;
    }

    @Override
    public Advice getAdvice() {
        return (MethodBeforeAdvice) (method, args, target) -> System.out.println("before staticMethod");
    }


}
