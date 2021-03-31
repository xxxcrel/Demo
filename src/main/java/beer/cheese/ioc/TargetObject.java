package beer.cheese.ioc;

import org.springframework.stereotype.Component;

@Component
public class TargetObject {
    public void hello(){
        System.out.println("hello");
    }
}
