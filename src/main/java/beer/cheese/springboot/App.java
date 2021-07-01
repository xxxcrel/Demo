package beer.cheese.springboot;

import java.util.ArrayList;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import beer.cheese.service.FlyService;
import lombok.extern.apachecommons.CommonsLog;

@SpringBootApplication
@ComponentScan(basePackages = {"beer.cheese.service", "beer.cheese.springboot"})
@RestController
@RequestMapping("/api")
@CommonsLog
@EnableScheduling
public class App extends WebSecurityConfigurerAdapter {

    @Value("${beer.cheese.key}")
    private String value;

    private static String staticValue;

    @Value("${beer.cheese.key}")
    private void setValue(String value){
        App.staticValue = value;
    }

    @Scheduled(cron = "${beer.cheese.cron}")
    public void schedule(){
        System.out.println("hello");
    }
    public static void main(String[] args) {
//        System.setProperty("spring.profiles.include", "service");
        ApplicationContext ctx = new SpringApplicationBuilder()
                .sources(App.class)
//                .initializers(new ServiceInitializer())
//                .listeners(new ServiceListener())
                .run(args);
        log.info("value is: " +  staticValue);
//        FlyService flyService = ctx.getBean(FlyService.class);
//        System.out.println("beer config :" + flyService.getModuleName());
//
//        System.out.println("getProp: " + System.getProperty("spring.profiles.include"));
//        CheeseProperties properties = ctx.getBean(CheeseProperties.class);
//        System.out.println(properties.appName);
//        properties.map.entrySet().forEach((entry) -> {
//            System.out.println(entry.getKey() + ": " + entry.getValue());
//        });
//        new ArrayList<String>(){
//            {
//               add("nothing");
//            };
//        };
    }



    @GetMapping("/login")
    public void login(HttpServletRequest request, HttpServletRequest response) {
        HttpSession session = request.getSession(true);
        session.setAttribute("username", "anonymous");
    }

    @GetMapping("/hello")
    public void hello(HttpServletRequest request) {
        log.info("Session.username:" + request.getSession().getAttribute("username"));
        for (Cookie cookie : request.getCookies()) {
            log.info("Comment: " + cookie.getComment());
            log.info("Domain: " + cookie.getDomain());
            log.info("Name: " + cookie.getName());
            log.info("MaxAge: " + cookie.getMaxAge());
            log.info("Path: " + cookie.getPath());
            log.info("Value: " + cookie.getValue());
            log.info("Version: " + cookie.getVersion());
        }
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        //TODO: just override default security config
    }

    @GetMapping("/remoteIP")
    public String getRemoteIP(HttpServletRequest request){
        log.info(request.getRemoteAddr());
        return request.getRemoteAddr();
    }
}
