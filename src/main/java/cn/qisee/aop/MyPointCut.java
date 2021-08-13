package cn.qisee.aop;

import java.lang.reflect.Method;

import org.aopalliance.aop.Advice;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.aop.AfterReturningAdvice;
import org.springframework.aop.framework.ProxyFactoryBean;
import org.springframework.aop.support.NameMatchMethodPointcut;
import org.springframework.aop.support.NameMatchMethodPointcutAdvisor;
import org.springframework.aop.target.HotSwappableTargetSource;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.EnableAspectJAutoProxy;

@Aspect
@EnableAspectJAutoProxy
@Configuration
@ComponentScan(basePackages = "cn.qisee.aop")
public class MyPointCut {

//    @Pointcut("execution(* com.shterm.TargetObject.*(..))")
//    public void beforePointcut(){
//    }
    @Pointcut("within(* cn.qisee.aop.TragetObject.*(..))")
    public void targetAll(){

    }

    @Before("execution(* cn.qisee.aop.TargetObject.*(..))")
    public void beforeAdvice(JoinPoint joinPoint){
        System.out.println("before any method");
        System.out.println(joinPoint.getSignature());
    }

//    @Bean
//    public HotSwappableTargetSource swapper(TargetObject initialTarget){
//        return new HotSwappableTargetSource(initialTarget);
//    }
//
//    @Bean
//    public ProxyFactoryBean swappable(HotSwappableTargetSource swapper){
//        ProxyFactoryBean pfb = new ProxyFactoryBean();
//        pfb.setTargetSource(swapper);
//        return pfb;
//    }

    @Bean
    public NameMatchMethodPointcutAdvisor nameMatchMethodPointcutAdvisor(){
        Advice advice = (AfterReturningAdvice) (returnValue, method, args, target) -> System.out.println("returnValue: " + returnValue);
        NameMatchMethodPointcutAdvisor advisor = new NameMatchMethodPointcutAdvisor(advice);
        advisor.addMethodName("staticMethod");
        return advisor;
    }
}
