package beer.cheese.mockserver;

import java.util.Map;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.SecurityBuilder;
import org.springframework.security.config.annotation.web.WebSecurityConfigurer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api")
@EnableWebSecurity(debug = true)
@Configuration
@SpringBootApplication
public class MockServer extends WebSecurityConfigurerAdapter {

    private final Log logger = LogFactory.getLog(MockServer.class);

    public static void main(String[] args) {
        SpringApplication.run(MockServer.class);
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.csrf().disable().cors().disable().authorizeRequests().anyRequest().permitAll();
    }

    @GetMapping("/noResponse")
    public void noResponse(){
        logger.info("/noResponse");
    }

    @PostMapping("/submitMap")
    public Map submitMap(@RequestParam Map<String , String> form){
        logger.info(form);
        return form;
    }

    @PostMapping("/json")
    public Map json(@RequestBody Map<String, String> json){
        logger.info(json);
        return json;
    }
}
