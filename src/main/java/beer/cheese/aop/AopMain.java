package beer.cheese.aop;

import org.springframework.cglib.core.DebuggingClassWriter;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class AopMain {
    public static void main(String[] args) {
        System.setProperty(DebuggingClassWriter.DEBUG_LOCATION_PROPERTY, "/Users/wuxc/classes");
        AnnotationConfigApplicationContext ctx = new AnnotationConfigApplicationContext(MyPointCut.class, TargetObject.class);
        TargetObject targetObject = ctx.getBean(TargetObject.class);
        targetObject.hello();
    }
}
