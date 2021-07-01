package beer.cheese.codec;

import java.util.regex.Pattern;

public class PatternTest {
    public static void main(String[] args) {
        System.out.println(Pattern.matches("java [^;&|]*", "java -cp xxx.jar"));
        System.out.println(Pattern.matches("java [^;&|]*", "java -cp xxx.jar ; xxx"));
        System.out.println(Pattern.matches("java [^;&|]*", "java -cp xxx.jar & xxx"));
        System.out.println(Pattern.matches("java [^;&|]*", "java -cp xxx.jar | xxx"));
    }
}
