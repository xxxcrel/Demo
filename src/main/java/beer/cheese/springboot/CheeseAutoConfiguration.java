package beer.cheese.springboot;

import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;

@EnableConfigurationProperties(CheeseProperties.class)
@Configuration
public class CheeseAutoConfiguration {
}
