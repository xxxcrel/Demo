package beer.cheese.springboot;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import beer.cheese.service.FlyService;

@SpringBootApplication
@ComponentScan(basePackages = "beer.cheese.service")
@RestController
@RequestMapping("/api")
public class App {
    public static void main(String[] args) {
        SpringApplication springApplication = new SpringApplication();
        ApplicationContext ctx = springApplication.run(App.class);
        System.out.println(System.getProperty("spring.profiles.include"));
        FlyService flyService = ctx.getBean(FlyService.class);
        System.out.println(flyService.getModuleName());
//        CheeseProperties properties = ctx.getBean(CheeseProperties.class);
//        System.out.println(properties.appName);
//        properties.map.entrySet().forEach((entry) -> {
//            System.out.println(entry.getKey() + ": " + entry.getValue());
//        });
    }
}
