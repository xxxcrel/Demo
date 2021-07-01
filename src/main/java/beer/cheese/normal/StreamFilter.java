package beer.cheese.normal;

import java.util.Arrays;
import java.util.List;

public class StreamFilter {

    public static void main(String[] args) {
        List<String> list = Arrays.asList("hell", "he");
        Arrays.asList("hello", "he", "wohelo", "jfaljf")
                .stream()
                .filter(list::contains)
                .filter(s -> s.contains("he"))
                .forEach(System.out::println);
        System.out.println(E.HELLO.name());
    }

    enum E{
        HELLO
    }
}
