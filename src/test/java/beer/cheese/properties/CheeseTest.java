package beer.cheese.properties;


import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.properties.ConfigurationPropertiesBindingPostProcessor;
import org.springframework.boot.context.properties.ConfigurationPropertiesScan;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import org.springframework.test.context.junit.jupiter.SpringJUnitConfig;

@SpringBootTest(properties = "application.yml")
@SpringJUnitConfig(CheeseConfig.class)
public class CheeseTest {

    @Autowired
    private CheeseProperties properties;

    @Test
    public void testCheese(){
        System.out.println(properties.appName);
    }
}

