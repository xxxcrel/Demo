package cn.qisee.aop;

import org.springframework.aop.target.HotSwappableTargetSource;
import org.springframework.cglib.core.DebuggingClassWriter;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class AopMain {
    public static void main(String[] args) {
        System.setProperty(DebuggingClassWriter.DEBUG_LOCATION_PROPERTY, "/Users/wuxc/classes");
        AnnotationConfigApplicationContext ctx = new AnnotationConfigApplicationContext(MyPointCut.class);
//        HotSwappableTargetSource swapper = (HotSwappableTargetSource) ctx.getBean("swapper");
        TargetObject targetObject = ctx.getBean(TargetObject.class);
        targetObject.hello();
        StaticMethod staticMethod = (StaticMethod) ctx.getBean("staticMethod");
        staticMethod.staticMethod();
        staticMethod.doSth();
        Object box = ctx.getBean(Box.class);
        ((Box)box).setSomething("hello");
        ((Lockable)box).lock();
        ((Box)box).setSomething("hello");
    }
}