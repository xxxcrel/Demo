package beer.cheese.codec;

import java.io.File;
import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

public class JsonDecodeTest {

    private ObjectMapper mapper;

    @BeforeEach
    public void setup() {
        mapper = new ObjectMapper();
    }

    @Test
    public void nestMapDecode() {
        File jsonFile = new File(System.getProperty("user.home") + "/nest_map.json");
        if (jsonFile.exists()) {
            Map<String, Map<String, String>> keyMap = new HashMap<>();
            try {
                keyMap = mapper.readValue(jsonFile, new TypeReference<Map<String, Map<String, String>>>() {});
            } catch (IOException e) {
                e.printStackTrace();
            }
            keyMap.keySet().forEach(System.out::println);
            keyMap.values().forEach(map -> {
                System.out.println("-----");
                map.forEach((k, v) -> {
                    System.out.println("key: [" + k + "] value: [" + v + "]");
                });
            });

        }
    }

    @Test
    public void ipSerializeTest() throws JsonProcessingException {
        IpHolder holder = new IpHolder("0:0:0:0:0:0:1");
        System.out.println(mapper.writeValueAsString(holder));
    }

    @Test
    public void testStream() {
        List<String> list = new ArrayList<>();
        System.out.println(list.stream().filter(s -> s.contains("s")).findFirst().orElse("not set"));
    }

    @Test
    public void testEnum() throws NoSuchMethodException, InvocationTargetException, IllegalAccessException {
        System.out.println(Result.valueOf("SUCCESS"));
        List<String> arr = Arrays.asList("hello", "world");
        String[] strArr = (String[])arr.toArray();
        System.out.println(Arrays.toString(strArr));
//        Method method =  Result.class.getDeclaredMethod("valueOf", String.class);
//        System.out.println(method.invoke(null, "0"));
        String s = "{\"key:\": \"你好\"}";
        Map<String, String> map = null;
        try {
            map = mapper.readValue(s, new TypeReference<Map<String, String>>() {});
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }
        map.forEach((k, v) -> System.out.println("key: " + k + "value: " + v));
    }

    @Test
    public void testDate(){
        System.out.println(new SimpleDateFormat("yyyy-MM-dd hh:mm:ss").format(new Date()));
    }

    enum Result {
        SUCCESS, FAILURE
    }

    class IpHolder {
        String ipAddr;

        public IpHolder(String ipAddr) {
            this.ipAddr = ipAddr;
        }

        public String getIpAddr() {
            return ipAddr;
        }

        public void setIpAddr(String ipAddr) {
            this.ipAddr = ipAddr;
        }
    }
}
