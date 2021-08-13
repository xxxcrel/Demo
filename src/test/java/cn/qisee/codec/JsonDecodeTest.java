package cn.qisee.codec;

import java.io.File;
import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

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
        String[] strArr = (String[]) arr.toArray();
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
    public void testCompress() {
        String s = "hello\"world, 1231";
//        s.chars().forEach(System.out::print);
//        System.out.println();
//        StringBuilder sb = s.chars().mapToObj(i -> (char) ((char) i ^ 255))
//                .collect(StringBuilder::new, StringBuilder::append,
//                StringBuilder::append);
//        System.out.println(sb.toString());
        String base = "qisee" + UUID.randomUUID() + new Date().getTime();
        System.out.println("base" + base);
        System.out.println(generateType5UUID(base));
    }

    public static UUID generateType5UUID(String name) {

        try {

            byte[] bytes = name.getBytes(StandardCharsets.UTF_8);
            MessageDigest md = MessageDigest.getInstance("SHA-1");

            byte[] hash = md.digest(bytes);

            long msb = getLeastAndMostSignificantBitsVersion5(hash, 0);
            long lsb = getLeastAndMostSignificantBitsVersion5(hash, 8);
            // Set the version field
            msb &= ~(0xfL << 12);
            msb |= ((long) 5) << 12;
            // Set the variant field to 2
            lsb &= ~(0x3L << 62);
            lsb |= 2L << 62;
            return new UUID(msb, lsb);

        } catch (NoSuchAlgorithmException e) {
            throw new AssertionError(e);
        }
    }

    private static long getLeastAndMostSignificantBitsVersion5(final byte[] src, final int offset) {
        long ans = 0;
        for (int i = offset + 7; i >= offset; i -= 1) {
            ans <<= 8;
            ans |= src[i] & 0xffL;
        }
        return ans;
    }

    @Test
    public void testTryCatch(){
        try {
            try {
                int[] ints = new int[2];
                ints[2] = 3;
            }catch (Exception e){
                System.out.println("catch");
                throw e;
            }finally {
                System.out.println("finally");
            }
        }catch (Exception e){
            System.out.println("recatch");
        }

    }

    @Test
    public void testDate() {
        String regexP = ".*[:+/\\u4e00-\\u9fa5 ].*";
        System.out.println("-1234".matches("-?\\d+"));
        System.out.println("hellojfaljdf".matches(regexP));
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
