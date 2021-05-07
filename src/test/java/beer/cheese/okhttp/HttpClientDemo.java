package beer.cheese.okhttp;

import java.io.IOException;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import okhttp3.OkHttpClient;

public class HttpClientDemo {
    private static final Log logger = LogFactory.getLog(HttpClientDemo.class);

    private static OkHttpClient client;

    private static ObjectMapper mapper;


    @BeforeAll
    public static void init() {
        client = new OkHttpClient.Builder().build();
        mapper = new ObjectMapper();
    }

    @Test
    public void testVoidResponse() throws IOException {
        HttpUtils.get("localhost", "/api/noResponse");
    }

    @Test
    public void testFormRequest() throws IOException {
        Map form = new HashMap();
        form.put("name", "wuxc");
        form.put("gender", "male");
        form.put("age", "10");

        System.out.println(HttpUtils.postForm("http://localhost:8080/api/submitMap", Collections.singletonMap("age", "hello")));
    }

    @Test
    public void testJsonRequest() throws IOException {
        Map form = new HashMap();
        form.put("name", "wuxc");
        form.put("gender", "male");
        form.put("age", "10");

        System.out.println(HttpUtils.postJson("http://localhost:8080/api/json", form));
    }

    @Test
    public void testRowJsonString() throws IOException {
        System.out.println(HttpUtils.postJson("http://localhost:8080/api/json", "{\"hello\":\"wuxc\"}"));
    }

    @Test
    public void testRowObject() throws IOException {
        System.out.println(HttpUtils.postJson("http://localhost:8080/api/json", new Data("wuxc", "male", "12")));
    }
}

@Setter
@Getter
@ToString
@AllArgsConstructor
class Data {
    String name;

    String gender;

    String age;
}