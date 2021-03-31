package beer.cheese.springboot;

import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Configuration;

@EnableConfigurationProperties(CheeseProperties.class)
@Configuration
public class CheeseAutoConfiguration {
}
