package com.cheeseocean.springboot;


import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.ConfigDataApplicationContextInitializer;
import org.springframework.test.context.junit.jupiter.SpringJUnitConfig;

@SpringJUnitConfig(value = CheeseAutoConfiguration.class, initializers = ConfigDataApplicationContextInitializer.class)
public class CheeseTest {

    @Autowired
    private CheeseProperties properties;

    @Test
    public void testCheese() {
        System.out.println("hello world");
        System.out.println(properties.appName);
    }
}

