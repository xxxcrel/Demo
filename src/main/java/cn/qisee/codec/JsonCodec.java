package cn.qisee.codec;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

public class JsonCodec {
    public static void main(String[] args) {
        ObjectMapper mapper = new ObjectMapper();
        Map<String, List<String>> extKeys = new LinkedHashMap<>();
        List<String> keys = new ArrayList<>();
        File file = new File(System.getProperty("user.home") + "/lic_ext_key.json");
        if(file.exists()){
            try {
                extKeys = mapper.readValue(file, new TypeReference<Map<String, List<String>>>(){});
            } catch (IOException e) {
                e.printStackTrace();
            }
            List<String> basicKeys = Arrays.asList("basic1", "basic2");
            keys = extKeys.get("ext_keys");
            keys = new ArrayList<>();
            String dateBeginKey = keys.stream().filter(s -> s.matches("date_begin_.*")).findFirst().orElse("hello");
            System.out.println(dateBeginKey);
            String dateEndKey = keys.stream().filter(s -> s.matches("date_end_.*")).findFirst().orElse("world");
            System.out.println(dateEndKey);
            System.out.println("----------");
            keys.stream()
                    .filter(s -> !basicKeys.contains(s))
                    .filter(s -> !s.matches("date_begin_.*|date_end_.*"))
                    .forEach(System.out::println);
        }
    }
}
