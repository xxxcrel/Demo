package beer.cheese.properties;

import org.springframework.boot.context.properties.ConfigurationPropertiesBindingPostProcessor;
import org.springframework.boot.context.properties.ConfigurationPropertiesScan;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;

@Configuration
@ConfigurationPropertiesScan
@Import(CheeseAutoConfiguration.class)
public class CheeseConfig {

    @Bean
    public ConfigurationPropertiesBindingPostProcessor postProcessor() {
        return new ConfigurationPropertiesBindingPostProcessor();
    }

}
