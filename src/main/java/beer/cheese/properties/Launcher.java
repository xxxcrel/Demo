package beer.cheese.properties;

import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class Launcher {

    public static void main(String[] args) {
        ApplicationContext context = new AnnotationConfigApplicationContext(CheeseConfig.class);
        CheeseProperties properties = (CheeseProperties)context.getBean(CheeseProperties.class);
        System.out.println(properties.getAppName());
        CheeseConfig config = (CheeseConfig) context.getBean(CheeseConfig.class);
    }
}
