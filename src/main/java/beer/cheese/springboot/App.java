package beer.cheese.springboot;

import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import beer.cheese.service.FlyService;
import beer.cheese.service.ServiceListener;

@SpringBootApplication
@ComponentScan(basePackages = "beer.cheese.service")
@RestController
@RequestMapping("/api")
public class App {
    public static void main(String[] args) {
//        System.setProperty("spring.profiles.include", "service");
        ApplicationContext ctx = new SpringApplicationBuilder()
                .sources(App.class)
//                .initializers(new ServiceInitializer())
//                .listeners(new ServiceListener())
                .run(args);
        FlyService flyService = ctx.getBean(FlyService.class);
        System.out.println("beer config :" + flyService.getModuleName());

        System.out.println("getProp: " + System.getProperty("spring.profiles.include"));
//        CheeseProperties properties = ctx.getBean(CheeseProperties.class);
//        System.out.println(properties.appName);
//        properties.map.entrySet().forEach((entry) -> {
//            System.out.println(entry.getKey() + ": " + entry.getValue());
//        });
    }
}
