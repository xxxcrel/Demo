package beer.cheese.properties;

import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

@EnableConfigurationProperties(CheeseProperties.class)
@Configuration
@ComponentScan(basePackages = "beer.cheese.properties")
public class CheeseAutoConfiguration {
}
