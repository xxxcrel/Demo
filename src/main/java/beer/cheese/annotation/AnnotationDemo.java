package beer.cheese.annotation;

import java.lang.annotation.Retention;
import java.util.Arrays;

import org.springframework.aop.framework.AopProxyUtils;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.SmartInitializingSingleton;
import org.springframework.beans.factory.config.BeanPostProcessor;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.core.Ordered;
import org.springframework.core.annotation.AnnotationUtils;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.scheduling.annotation.Schedules;
import org.springframework.stereotype.Component;

import lombok.Getter;
import lombok.Setter;

public class AnnotationDemo {

    public static void main(String[] args) {
        System.out.println(AnnotationUtils.isCandidateClass(Ordered.class, Setter.class));

        System.out.println(Inner.class.getEnclosingClass().getSimpleName());
    }

    public static void start(Thread thread){

    }

    public class Inner{

    }
}



@Component
@interface MyComponent{
    String value();
}

@interface Job{
    String value();
}
class HttpHandler implements BeanPostProcessor, SmartInitializingSingleton, ApplicationContextAware {

    @Override
    public void afterSingletonsInstantiated() {

    }

    ApplicationContext context;

    @Override
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
        this.context = applicationContext;
    }

    @Override
    public Object postProcessAfterInitialization(Object bean, String beanName) throws BeansException {
        Class<?> targetClass = AopProxyUtils.ultimateTargetClass(bean);
        if (AnnotationUtils.isCandidateClass(targetClass, Job.class)) {
            //do something
        }
        return bean;
    }
}