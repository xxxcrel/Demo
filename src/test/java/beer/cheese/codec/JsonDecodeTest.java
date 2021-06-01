package beer.cheese.codec;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.util.HashMap;
import java.util.Map;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

public class JsonDecodeTest {

    private ObjectMapper mapper;

    @BeforeEach
    public void setup(){
        mapper = new ObjectMapper();
    }
    @Test
    public void nestMapDecode(){
        File jsonFile = new File(System.getProperty("user.home") + "/nest_map.json");
        if(jsonFile.exists()){
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
}
