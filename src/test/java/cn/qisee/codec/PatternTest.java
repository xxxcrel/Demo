package cn.qisee.codec;

import java.lang.reflect.Field;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.regex.Pattern;

import org.junit.jupiter.api.Test;
import org.springframework.cglib.beans.BeanMap;
import org.springframework.util.StreamUtils;

import com.google.common.collect.Maps;

import cn.qisee.jpa.model.entity.Role;
import cn.qisee.jpa.model.entity.User;

public class PatternTest {
    public static void main(String[] args) {
        Map<String, Object> map = new LinkedHashMap<>();
        map.getClass().isAssignableFrom(LinkedHashMap.class);
        System.out.println("is Map");
        Person p = new Person();
        p.name = "wuxc";
        p.sex = false;

        Field[] fields = p.getClass().getDeclaredFields();
        for(Field f : fields) {
            Object obj = null;
            try {
                obj = f.get(p);
            } catch (IllegalAccessException e) {
                e.printStackTrace();
            }
            System.out.println(obj.getClass().getName());
        }
//        Calendar calendar = Calendar.getInstance();
//        calendar.setTime(new Date());
//        calendar.add(Calendar.DATE, 10);
//        System.out.println("current date plus 10 days is" + calendar.getTime());
//        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
//        Date date = new Date();
//        dateFormat.setTimeZone(TimeZone.getTimeZone("Asia/Shanghai"));
//        System.out.println(dateFormat.format(date));
//        System.out.println(date.getTime());
//        System.out.println(date);
//        System.out.println("----");
//        Date date1 = new Date();
//        System.out.println(date1.getTime());
//        System.out.println(Pattern.matches("java [^;&|]*", "java -cp xxx.jar"));
//        System.out.println(Pattern.matches("java [^;&|]*", "java -cp xxx.jar ; xxx"));
//        System.out.println(Pattern.matches("java [^;&|]*", "java -cp xxx.jar & xxx"));
//        System.out.println(Pattern.matches("java [^;&|]*", "java -cp xxx.jar | xxx"));
//        System.out.println(Pattern.matches("/bin/bash -c ip neigh show \\| awk '\\{print \\$1, \\$5}'[^;$|]*", "/bin/bash -c ip neigh show | awk '{print $1, $5}'"));
    }
    static class Person{
        String name;
        Boolean sex;
    }

    @Test
    public void tt(){
        Role role = new Role();
        Map<String, Object> map = new HashMap<>();
        map.put("id", 1L);
        map.put("name", "wuxc");
        map.put("desc", "ADMIN");
        BeanMap beanMap = BeanMap.create(role);
        beanMap.putAll(map);
        System.out.println(role);
    }
}
