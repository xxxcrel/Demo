package beer.cheese.custom.properties;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringJUnitConfig;

@SpringJUnitConfig(CheeseAutoConfiguration.class)
@SpringBootTest(properties = "application.properties")
public class CheeseTest {

    @Autowired
    private CheeseProperties properties;

    @Test
    public void test(){
        System.out.println(properties.getName());
    }
}
