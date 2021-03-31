package beer.cheese.ioc;

import org.springframework.cglib.core.DebuggingClassWriter;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.stereotype.Service;

public class AopMain {
    public static void main(String[] args) {
        System.setProperty(DebuggingClassWriter.DEBUG_LOCATION_PROPERTY, "/Users/wuxc/classes");
        AnnotationConfigApplicationContext ctx = new AnnotationConfigApplicationContext(MyPointCut.class, TargetObject.class);
        TargetObject targetObject = ctx.getBean(TargetObject.class);
        targetObject.hello();
    }
}
