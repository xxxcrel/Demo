package beer.cheese.custom.properties;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.ConfigDataApplicationContextInitializer;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringJUnitConfig;

@SpringJUnitConfig(value = CheeseAutoConfiguration.class, initializers = ConfigDataApplicationContextInitializer.class)
public class CheeseTest {

    @Autowired
    private CheeseProperties properties;

    @Test
    public void test(){
        System.out.println(properties.getName());
    }

    @Test
    public void processors(){
        System.out.println(Runtime.getRuntime().availableProcessors());
    }
}
