package cn.qisee.rest;

import org.junit.jupiter.api.Test;
import org.springframework.web.client.RestTemplate;

public class RestTemplateTest {
    @Test
    public void test(){
        RestTemplate template = new RestTemplate();
        template.setMessageConverters();
    }
}
