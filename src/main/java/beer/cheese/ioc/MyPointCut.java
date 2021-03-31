package beer.cheese.ioc;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.EnableAspectJAutoProxy;

@Aspect
@EnableAspectJAutoProxy
@Configuration
public class MyPointCut {

//    @Pointcut("execution(* com.shterm.TargetObject.*(..))")
//    public void beforePointcut(){
//    }

    @Before("execution(* com.shterm.TargetObject.*(..))")
    public void beforeAdvice(JoinPoint joinPoint){
        System.out.println("before any method");
        System.out.println(joinPoint.getSignature());
    }
}
